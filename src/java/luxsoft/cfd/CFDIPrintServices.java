package luxsoft.cfd;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Conceptos.Concepto;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Emisor;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Receptor;
import mx.gob.sat.cfd.x2.TUbicacion;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.xmlbeans.XmlObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.luxsoft.cfd.ComprobanteFiscal;




public class CFDIPrintServices {
	
	
	
	/**
	 * Permite imprimir el comprobante fiscal tantas veces se requiera
	 * 
	 * @param venta
	 */
	public  void impripirComprobante(ComprobanteFiscal cf){
		//cf.loadComprobante();
		Comprobante cfdi=cf.getComprobante();
		;
		List<Concepto> list=Arrays.asList(cfdi.getConceptos().getConceptoArray());
		//final List<Concepto> conceptos=GlazedLists.eventList(list);
		final Map parametros=resolverParametros(cfdi);
		
		/*
		JasperPrint jasperPrint = null;
		DefaultResourceLoader loader = new DefaultResourceLoader();
		
		String path="file:z:/REPORTES_GASOC/VENTAS/ComprobanteCFDI.jasper";
		Resource res = loader.getResource(path);
		try {
			java.io.InputStream io = res.getInputStream();
			String[] columnas= {"cantidad","NoIdentificacion","descripcion","unidad","ValorUnitario","Importe"}; // 15 17a 17b 16 18 19 
			String[] etiquetas={"cantidad","NoIdentificacion","descripcion","unidad","ValorUnitario","Importe"};
			final TableFormat tf=GlazedLists.tableFormat(columnas, etiquetas);
			
			final EventTableModel tableModel=new EventTableModel(conceptos,tf);
			final JRTableModelDataSource tmDataSource=new JRTableModelDataSource(tableModel);
			jasperPrint = JasperFillManager.fillReport(io, parametros,tmDataSource);
			
			return jasperPrint;
		} catch (Exception ioe) {
			throw new RuntimeException(ExceptionUtils.getRootCauseMessage(ioe),ioe);
		}
		*/
		
	}
	
	public static CadenaOriginalBuilder cadenaBuilder=new CadenaOriginalBuilder();
	
	
	public static Map resolverParametros(Comprobante cfd){
		
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
		parametros.put("CADENA_ORIGINAL",cadenaBuilder.obtenerCadena(cfd));
		//System.out.println(cfd);
		
		return parametros;
	}
	
	
	
	
 
}


