package com.luxsoft.cfd

import java.awt.print.Printable;
import java.security.cert.CertificateEncodingException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlOptions
import org.apache.xmlbeans.XmlValidationError

import util.MonedaUtils;

import com.luxsoft.impapx.*;


import javax.xml.namespace.QName

import luxsoft.cfd.CFDUtils;
import luxsoft.cfd.CadenaOriginalBuilder;
import luxsoft.cfd.Conversiones;
import mx.gob.sat.cfd.x2.ComprobanteDocument;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Conceptos;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Conceptos.Concepto;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Emisor;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Emisor.RegimenFiscal;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Impuestos;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Impuestos.Traslados;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Impuestos.Traslados.Traslado;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Receptor;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.TipoDeComprobante;
import mx.gob.sat.cfd.x2.TInformacionAduanera;
import mx.gob.sat.cfd.x2.TUbicacion;
import mx.gob.sat.cfd.x2.TUbicacionFiscal;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

class ComprobanteFiscalService {
	
	def selladorService
	CadenaOriginalBuilder cadenaBuilder=new CadenaOriginalBuilder();

	
    def generarComprobanteFiscalDigital(long ventaId) {
		
		def venta=Venta.findById(ventaId,[fetch:[partidas:'select']])
		System.out.println("generando comprobante fiscal para la venta "+ventaId);
		if(venta.cfd){
			throw new VentaException(message:'Venta ya facturada',venta:venta)
		}
		def empresa=Empresa.list().get(0)
		def cliente=venta.cliente 
		
		
		
		Calendar c=Calendar.getInstance();
		//c.setTime(venta.lastUpdated);										//CAMBIO ESPECIAL QUITAR
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		XmlDateTime xmlDateTime = XmlDateTime.Factory.newInstance();
		xmlDateTime.setStringValue(df.format(c.getTime()));
		Calendar fecha=xmlDateTime.getCalendarValue()
		
		
		def version="2.2"
		def formaDePago="PAGO EN UNA SOLA EXHIBICION"
		def metodoDePago=venta.formaDePago
		def numeroDeCuenta=cliente.cuentaDePago
		if(!numeroDeCuenta || (numeroDeCuenta=='0000'))
			metodoDePago="NO IDENTIFICADO"
		def lugar="Mexico"
		
		
		//Inicializar
		ComprobanteDocument document=ComprobanteDocument.Factory.newInstance()
		Comprobante cfd=document.addNewComprobante()
		cfd.setVersion(version)
		cfd.setFecha(fecha);
		cfd.setTipoDeComprobante(TipoDeComprobante.INGRESO);
		
		
		cfd.setFormaDePago(formaDePago);
		cfd.setMetodoDePago(metodoDePago);
		if(numeroDeCuenta)
			cfd.setNumCtaPago(numeroDeCuenta);
		cfd.setLugarExpedicion(lugar);
		
		//Emisor y receptor
		Emisor emisor=cfd.addNewEmisor();
		emisor.setNombre(empresa.nombre);
		emisor.setRfc(empresa.rfc);
		TUbicacionFiscal domicilioFiscal=emisor.addNewDomicilioFiscal();
		Conversiones.getTUbicacionFiscal(
					empresa.direccion
					, domicilioFiscal
					);
		RegimenFiscal rf=emisor.addNewRegimenFiscal();
		rf.setRegimen(empresa.regimen);
		
		// Receptor
		Receptor receptor=cfd.addNewReceptor();
		receptor.setNombre(cliente.nombre);
		receptor.setRfc(cliente.rfc);
		Conversiones.getTUbicacion(cliente.direccion,receptor.addNewDomicilio());
		
		Receptor rec=cfd.getReceptor();
		rec.setNombre(cliente.nombre);
		
		generarConceptos(cfd,venta)
		
		// Totales
		cfd.setSubTotal(venta.importe);
		cfd.setDescuento(venta.descuentos);
		cfd.setTotal(venta.total);
		
		
		Impuestos impuestos=cfd.addNewImpuestos();
		String rfc=cfd.getReceptor().getRfc();
		
		//Facturacion a clientes extranjero
		if(rfc=="XEXX010101000"){
			cfd.setSubTotal(venta.importe);
			cfd.setTotal(venta.subtotal);
		}else if(rfc=="XAXX010101000" || StringUtils.isBlank(rfc)){
			cfd.setSubTotal(venta.importe*(1+MonedaUtils.IVA));
			cfd.setDescuento(venta.descuentos*(1+MonedaUtils.IVA));
			cfd.setTotal(venta.total);
		}else{
			impuestos.setTotalImpuestosTrasladados(venta.impuestos);
			Traslados traslados=impuestos.addNewTraslados();
			Traslado traslado=traslados.addNewTraslado();
			traslado.setImpuesto(Traslado.Impuesto.IVA);
			traslado.setImporte(venta.impuestos);
			traslado.setTasa(MonedaUtils.IVA*100);
		}
		depuracionFinal(document);
		
		//Asignacion de serie y folio
		def serie="IMFACCRE"
		if(venta.tipo=='NOTA_DE_CARGO'){
			serie="IMNOTCAR"
		}

		FolioFiscal folio=FolioFiscal.findBySerie(serie)			
		cfd.setSerie(serie);
		cfd.setFolio(folio.next().toString()); 
		cfd.setAnoAprobacion(folio.getAnoAprobacion());
		cfd.setNoAprobacion(new BigInteger(folio.getNoAprobacion().toString()));
		folio=folio.save()
		//println 'folio: '+folio.folio
		
		registrarSelloDigital(document);
		
		validarDocumento(document);
		
		String fileName="${cfd.getSerie()}_${cfd.getFolio()}"
		File xml=salvar(fileName,document);
		
		Concepto conceptoRow=cfd.getConceptos().getConceptoArray(0)
		TInformacionAduanera aduana
		if(conceptoRow && conceptoRow.getInformacionAduaneraArray()){
			conceptoRow.informacionAduaneraArray
			println 'Concepto: '+conceptoRow+ ' Informacion aduanera: '+conceptoRow
			aduana=conceptoRow.getInformacionAduaneraArray(0)
			//println 'Aduana:'+aduana  
		}
		
		ComprobanteFiscal cf=new ComprobanteFiscal(
			tipo:venta.tipo=='VENTA'?"FACTURA":'NOTA_DE_CARGO',
			origen:venta.id,
			numeroDeCertificado:cfd.getNoCertificado(),
			fecha:cfd.getFecha().getTime(),
			folio:cfd.getFolio(),
			serie:cfd.getSerie(),
			numeroDeAprobacion:cfd.getNoAprobacion(),
			rfc:cfd.getSerie(),
			tipoCfd:'I',
			estado:'1',
			xmlPath:xml.toURI().toURL().toString(),
			anoAprobacion:cfd.getAnoAprobacion(),
			impuesto:impuestos.getTotalImpuestosTrasladados(),
			total:cfd.getTotal(),
			aduana:aduana?.aduana,
			pedimento:aduana?.numero,
			fechaPedimento:aduana?.fecha?.getTime()
			).save()
		
				
		venta.cfd=cf
		
		
		def res=['venta':venta,'cfd':cfd]
    }
	
	def generarConceptos(Comprobante cfd, Venta venta){
		Conceptos conceptos=cfd.addNewConceptos();
		
		if(venta.tipo=='NOTA_DE_CARGO'){
			Concepto c=conceptos.addNewConcepto();
			c.setCantidad(1.00);
			c.setUnidad("NO APLICA");
			c.setNoIdentificacion('CARGO');
			c.setDescripcion(venta.comentario);
			
			c.setValorUnitario(venta.importe);
			c.setImporte(venta.importe);
			
			return
		}
		
		for(VentaDet det:venta.getPartidas()){
			Concepto c=conceptos.addNewConcepto();
			c.setCantidad(det.getCantidadEnUnidad());
			c.setUnidad(det.producto.unidad.nombre);
			c.setNoIdentificacion(det.producto.clave);
			
			String desc = det.producto.descripcion;
			if(StringUtils.isNotBlank(det.getComentario()))
				desc = (new StringBuilder(String.valueOf(desc))).append(StringUtils.stripToEmpty(det.comentario)).toString();
			desc = StringUtils.abbreviate(desc, 250);
			c.setDescripcion(desc);
			
			c.setValorUnitario(det.precio);
			c.setImporte(det.importe);
			
			println 'Pedimento aduanal: '+det.embarque.pedimento+ ' EmbarqueDet: '+det.id 
			
			def pedimento=det.embarque.pedimento
			
			if(pedimento){
				
				TInformacionAduanera aduana = c.addNewInformacionAduanera();
				aduana.setAduana(det.embarque.embarque.aduana.nombre);
				aduana.setNumero(pedimento.pedimento);
				
				Calendar cal=Calendar.getInstance();
				cal.setTime(pedimento.fecha);
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				XmlDateTime xmlDateTime = XmlDateTime.Factory.newInstance();
				xmlDateTime.setStringValue(df.format(cal.getTime()));
				//aduana.setFecha(cal);
				aduana.setFecha(xmlDateTime.getCalendarValue());
			}			
		}
	}
	
	def depuracionFinal(ComprobanteDocument document){
		//Configuracion final
		XmlCursor cursor=document.newCursor();
		if(cursor.toFirstChild()){
			cursor.setAttributeText(new QName("http://www.w3.org/2001/XMLSchema-instance","schemaLocation","xsi")
											, "http://www.sat.gob.mx/cfd/2 http://www.sat.gob.mx/sitio_internet/cfd/2/cfdv22.xsd");
		}
	}
	
	
	
	def String registrarSelloDigital(ComprobanteDocument document){
		
		String cadena=cadenaBuilder.obtenerCadena(document)
		String sello= selladorService.getSello(cadena);
		document.getComprobante().setSello(sello);
		try {
			byte[] encodedCert = Base64.encode(selladorService.getCertificado().getEncoded());
			document.getComprobante().setCertificado(new String(encodedCert));
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		}
		document.getComprobante().setNoCertificado(selladorService.certificadoDigital.numeroDeCertificado);
		return sello;
		
	}
	
	def validarDocumento(ComprobanteDocument document) {
		List<XmlValidationError> errores=CFDUtils.validar(document);
		if(errores.size()>0){
			StringBuffer buff=new StringBuffer();
			for(XmlValidationError e:errores){
				log.info(e.getMessage());
				buff.append(e.getMessage()+"\n");
			}
			throw new RuntimeException("Datos para generar el comprobante fiscal (CFD) incorrectos "+buff.toString());
		}
	}
	
	public String getDocumentXMLFileName(Comprobante cfd,Object source){
		Venta venta=(Venta)source;
		String folio=cfd.getFolio();
		folio=StringUtils.leftPad(folio, 7, '0');
		String pattern="{0}{1}{2}";
		String name=MessageFormat.format(pattern
				,venta.getSucursal().getId()
				,venta.getOrigen().name()
				,folio
				);
		return name;
	}
	
	def salvar(String xmlName,ComprobanteDocument docto) {
		
		try {
			FolioFiscal folio=FolioFiscal.findBySerie(docto.comprobante.serie)
			Resource r=new FileSystemResource(folio.directorioAlmacenar);
			File destino=new File(r.getFile(),xmlName+".xml");
			
			
			XmlOptions options = new XmlOptions();
			options.setCharacterEncoding("UTF-8");
			options.put( XmlOptions.SAVE_INNER );
			options.put( XmlOptions.SAVE_PRETTY_PRINT );
			options.put( XmlOptions.SAVE_AGGRESSIVE_NAMESPACES );
			options.put( XmlOptions.SAVE_USE_DEFAULT_NAMESPACE );
			options.put(XmlOptions.SAVE_NAMESPACES_FIRST);
			Map suggestedPrefix=new HashMap();
			suggestedPrefix.put("", "");
			docto.save(destino,options);
			return destino
		} catch (IOException e) {
			throw new RuntimeException("Error salvando CFD causa: "+ExceptionUtils.getRootCauseMessage(e),e);
		}
	}

}
