package com.luxsoft.cfd

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
import com.luxsoft.impapx.cxc.CXCNota;
import com.luxsoft.impapx.cxc.CXCNotaDet;


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

class ComprobanteFiscalNotaService {
	
	def selladorService
	//CadenaOriginalBuilder cadenaBuilder=new CadenaOriginalBuilder();
	def cadenaOriginalBuilder

	
    def generarComprobanteFiscalDigital(long notaId) {
		
		def nota=CXCNota.findById(notaId,[fetch:[partidas:'select']])
		if(nota.cfd){
			throw new RuntimeException(message:'Nota con CFD ya generado')
		}
		def empresa=Empresa.list().get(0)
		def cliente=nota.cliente 
		
		
		Calendar c=Calendar.getInstance();
		//c.setTime(venta.lastUpdated);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		XmlDateTime xmlDateTime = XmlDateTime.Factory.newInstance();
		xmlDateTime.setStringValue(df.format(c.getTime()));
		Calendar fecha=xmlDateTime.getCalendarValue()
		
		
		def version="2.2"
		def formaDePago="PAGO EN UNA SOLA EXHIBICION"
		def metodoDePago='NO IDENTIFICADO'
		def numeroDeCuenta=cliente.cuentaDePago
		if(!numeroDeCuenta || (numeroDeCuenta=='0000'))
			metodoDePago="NO IDENTIFICADO"
		def lugar="Mexico"
		
		//Inicializar
		ComprobanteDocument document=ComprobanteDocument.Factory.newInstance()
		Comprobante cfd=document.addNewComprobante()
		cfd.setVersion(version)
		cfd.setFecha(fecha);
		cfd.setTipoDeComprobante(TipoDeComprobante.EGRESO);
		
		
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
		
		generarConceptos(cfd,nota)
		
		// Totales
		cfd.setSubTotal(nota.importe);
		//cfd.setDescuento(nota.descuentos);
		cfd.setTotal(nota.total);
		
		
		Impuestos impuestos=cfd.addNewImpuestos();
		String rfc=cfd.getReceptor().getRfc();
		
		//Facturacion a clientes extranjero
		if(rfc=="XEXX010101000"){
			cfd.setSubTotal(nota.importe);
			cfd.setTotal(nota.total);
		}else if(rfc=="XAXX010101000" || StringUtils.isBlank(rfc)){
			cfd.setSubTotal(nota.importe*(1+MonedaUtils.IVA));
			cfd.setDescuento(nota.descuentos*(1+MonedaUtils.IVA));
			cfd.setTotal(nota.total);
		}else{
			impuestos.setTotalImpuestosTrasladados(nota.impuesto);
			Traslados traslados=impuestos.addNewTraslados();
			Traslado traslado=traslados.addNewTraslado();
			traslado.setImpuesto(Traslado.Impuesto.IVA);
			traslado.setImporte(nota.impuesto);
			traslado.setTasa(MonedaUtils.IVA*100);
		}
		depuracionFinal(document);
		
		//Asignacion de serie y folio
		def serie="IMNOTCRE"
		FolioFiscal folio=FolioFiscal.findBySerie(serie)
		cfd.setSerie(serie);
		cfd.setFolio(folio.next().toString());
		cfd.setAnoAprobacion(folio.getAnoAprobacion());
		cfd.setNoAprobacion(new BigInteger(folio.getNoAprobacion().toString()));
		folio=folio.save()
		println 'folio: '+folio.folio
		
		registrarSelloDigital(document);
		
		validarDocumento(document);
		
		String fileName="${cfd.getSerie()}_${cfd.getFolio()}"
		File xml=salvar(fileName,document);
		
		ComprobanteFiscal cf=new ComprobanteFiscal(
			tipo:"NOTA_DE_CREDITO",
			origen:nota.id,
			numeroDeCertificado:cfd.getNoCertificado(),
			
			folio:cfd.getFolio(),
			xmlPath:xml.toURI().toURL().toString(),
			fecha:cfd.getFecha().getTime(),			
			serie:cfd.getSerie(),
			numeroDeAprobacion:cfd.getNoAprobacion(),
			rfc:cfd.getSerie(),
			tipoCfd:'I',
			estado:'1',
			anoAprobacion:cfd.getAnoAprobacion(),
			impuesto:impuestos.getTotalImpuestosTrasladados(),
			total:cfd.getTotal()
			).save(failOnError:true)
		
		nota.cfd=cf
		nota.save(failOnError:true)
		
		def res=['nota':nota,'cfd':cfd]
    }
	
	def generarConceptos(Comprobante cfd, CXCNota nota){
		Conceptos conceptos=cfd.addNewConceptos();
		
		for(CXCNotaDet det:nota.getPartidas()){
			Concepto c=conceptos.addNewConcepto();
			c.setCantidad(det.getCantidad());
			c.setUnidad(det.unidad);
			c.setNoIdentificacion(det.numeroDeIdentificacion);
			
			String desc = det.descripcion;
			c.setDescripcion(desc);
			c.setValorUnitario(det.valorUnitario);
			c.setImporte(det.importe);			
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
		
		String cadena=cadenaOriginalBuilder.obtenerCadena(document)
		
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
