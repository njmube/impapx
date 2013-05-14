package com.luxsoft.cfd


import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import luxsoft.cfd.CFDUtils;
import luxsoft.cfd.ImporteALetra;
import mx.gob.sat.cfd.x2.TUbicacion;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Emisor;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Receptor;

class PrintService {
	
	def cadenaOriginalBuilder

    public  Map resolverParametros(Comprobante cfd){
		
		Map<String, Object> parametros = new HashMap<String, Object>();
	
		
		BigDecimal total=cfd.getTotal();
		parametros.put("IMP_CON_LETRA", 	ImporteALetra.aLetra(total));
		parametros.put("TIPO_CFD", cfd.getTipoDeComprobante().toString());
		parametros.put("NUM_CTA_PAGO", cfd.getNumCtaPago());
		parametros.put("CERTIFICADO", cfd.getNoCertificado());
		parametros.put("SELLO_DIGITAL", cfd.getSello());
		parametros.put("YEARNUMAPROB", cfd.getAnoAprobacion());
		parametros.put("NO_APROBACION", cfd.getNoAprobacion().toString());
		Receptor receptor=cfd.getReceptor();
		parametros.put("RECEPTOR_NOMBRE", 	receptor.getNombre());					
		parametros.put("RECEPTOR_RFC", 		receptor.getRfc());						
		parametros.put("RECEPTOR_DIRECCION", 		CFDUtils.getDireccionEnFormatoEstandar(receptor.getDomicilio()));	
		
		Emisor emisor=cfd.getEmisor();
		parametros.put("EMISOR_NOMBRE", 	emisor.getNombre());					// 1
		parametros.put("EMISOR_RFC", 		emisor.getRfc());						// 2
		parametros.put("EMISOR_DIRECCION", CFDUtils.getDireccionEnFormatoEstandar(emisor.getDomicilioFiscal()));						// 3
		
		if (emisor.getExpedidoEn() != null)
		{
			TUbicacion expedido=emisor.getExpedidoEn();	
			//System.out.println(CFDUtils.getDireccionEnFormatoEstandar(expedido));
			parametros.put("EXPEDIDO_DIRECCION", CFDUtils.getDireccionEnFormatoEstandar(expedido));							
		}
		else
			parametros.put("EXPEDIDO_DIRECCION", CFDUtils.getDireccionEnFormatoEstandar(emisor.getDomicilioFiscal()));
		String regimen=emisor.getRegimenFiscalArray()[0].getRegimen();
		parametros.put("REGIMEN", regimen);
		parametros.put("LUGAR_EXPEDICION", cfd.getLugarExpedicion());
		parametros.put("FORMA_PAGO", cfd.getCondicionesDePago() );
		
		//parametros.put("FECHA", cfd.getFecha().getTime());
		parametros.put("FOLIO", cfd.getFolio());
		parametros.put("SERIE", cfd.getSerie());
		parametros.put("CONDICIONES_PAGO",cfd.getFormaDePago());
		parametros.put("METODO_PAGO", cfd.getMetodoDePago());
		NumberFormat nf=NumberFormat.getInstance();
		parametros.put("IMPORTE_BRUTO", nf.format(cfd.getSubTotal().doubleValue()));
		if(cfd.getDescuento()!=null)
			parametros.put("DESCUENTOS", nf.format(cfd.getDescuento().doubleValue()));
		parametros.put("IVA", nf.format(cfd.getImpuestos().getTotalImpuestosTrasladados().doubleValue()));
		parametros.put("TOTAL", nf.format(cfd.getTotal().doubleValue()));	
		parametros.put("PINT_IVA", nf.format(cfd.getImpuestos().getTraslados().getTrasladoArray(0).getTasa()));
		parametros.put("CADENA_ORIGINAL",cadenaOriginalBuilder.obtenerCadena(cfd));
		//System.out.println(cfd);
		
		return parametros;
	}
}
