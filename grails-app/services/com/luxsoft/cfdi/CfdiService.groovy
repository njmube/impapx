package com.luxsoft.cfdi

import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlValidationError;

import mx.gob.sat.cfd.x3.ComprobanteDocument;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;

import org.bouncycastle.util.encoders.Base64;

import com.luxsoft.impapx.Empresa;
import com.luxsoft.impapx.Venta;
import com.luxsoft.impapx.cxc.CXCNota;

class CfdiService {

	def grailsApplication
	
	def cfdiSellador
	
	def cfdiTimbrador
	
    def Cfdi generarCfdi(def source) {
		
		def empresa=Empresa.last()
		assert empresa,"Debe existir la empresa"
		
		def serie=null
		if(source instanceof Venta)
			serie='FAC'
		if(source instanceof CXCNota)
			serie='CRE'
			
		def cfdiFolio=CfdiFolio.findByEmisor(empresa.clave)
		assert cfdiFolio," Debe registrar folio de $source.empresa.clave para la serie FAC"
		def folio=CfdiFolio.findByEmisorAndSerie(empresa.clave,serie).next()
		
		def cfdi=CfdiConverters.toCfdi(source,empresa)
		cfdi.serie='FAC'
		cfdi.folio=folio
		
		def ComprobanteDocument document=CfdiConverters.toComprobante(source, empresa)
		Comprobante comprobante=document.getComprobante()
		comprobante.serie=serie
		comprobante.folio=folio
		
		//comprobante.setSello(cfdiSellador.sellar(CFDIUtils.leerLlavePrivada(empresa),document))
		comprobante.setSello(cfdiSellador.sellar(empresa.privateKey,document))
		byte[] encodedCert=Base64.encode(empresa.getCertificado().getEncoded())
		comprobante.setCertificado(new String(encodedCert))
		
		XmlOptions options = new XmlOptions()
		options.setCharacterEncoding("UTF-8")
		options.put( XmlOptions.SAVE_INNER )
		options.put( XmlOptions.SAVE_PRETTY_PRINT )
		options.put( XmlOptions.SAVE_AGGRESSIVE_NAMESPACES )
		options.put( XmlOptions.SAVE_USE_DEFAULT_NAMESPACE )
		options.put(XmlOptions.SAVE_NAMESPACES_FIRST)
		ByteArrayOutputStream os=new ByteArrayOutputStream()
		document.save(os, options)
		
		cfdi.setXml(os.toByteArray())
		cfdi.setXmlName("$cfdi.rfc-$cfdi.serie-$cfdi.folio"+".xml")
		
		validarDocumento(document)		
		cfdi.save(failOnError:true)
		cfdi=cfdiTimbrador.timbrar(cfdi,"PAP830101CR3", "yqjvqfofb")
		return cfdi
    }
	
	void validarDocumento(ComprobanteDocument document) {
		List<XmlValidationError> errores=findErrors(document);
		if(errores.size()>0){
			StringBuffer buff=new StringBuffer();
			for(XmlValidationError e:errores){
				buff.append(e.getMessage()+"\n");
			}
			throw new CfdiException(message:"Datos para generar el comprobante fiscal (CFD) incorrectos "+buff.toString());
		}
	}
	
	List findErrors(final XmlObject node){
		final XmlOptions options=new XmlOptions();
		final List errors=new ArrayList();
		options.setErrorListener(errors);
		node.validate(options);
		return errors;
		
	}
}
