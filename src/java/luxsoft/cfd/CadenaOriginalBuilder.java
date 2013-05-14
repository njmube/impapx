package luxsoft.cfd;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import mx.gob.sat.cfd.x2.ComprobanteDocument;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.Assert;




public class CadenaOriginalBuilder implements InitializingBean{
	
	 
	
	public String obtenerCadena(ComprobanteDocument comprobante) throws RuntimeException{		
		
		String cadenaOriginal = "";
        try
        {
            String fileTemp= "~" + new Date().getTime();
            File file = new File(getTempDir(),fileTemp);            
            OutputStream out = new FileOutputStream(file);
           
            DOMSource source=new DOMSource(comprobante.getDomNode());
            getTransofrmer().transform(source, new StreamResult(out));
            
            InputStream in = new FileInputStream(new File(getTempDir(),fileTemp));
            InputStreamReader streamReader = new InputStreamReader(in,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            
            cadenaOriginal = bufferedReader.readLine();

            streamReader.close();
            in.close();
            out.close();
            
            File eliminarFileTemp = new File(getTempDir(),fileTemp);
            eliminarFileTemp.delete();
            
            if(cadenaOriginal.equals("|||")) cadenaOriginal = null;
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
            throw new RuntimeException(
            		"Se presento un error al intentar generar la cadena original\n " +
            		"" + ExceptionUtils.getRootCauseMessage(ex),ex);
        }
        return cadenaOriginal;
	}

	public String obtenerCadena(Comprobante comprobante) {		
		
		try {
			//StringWriter out=new StringWriter();
			final ByteArrayOutputStream out=new ByteArrayOutputStream();
			final OutputStreamWriter writer=new OutputStreamWriter(out, "UTF-8");
			//System.out.println(comprobante);
			Result res=new StreamResult(writer);
			DOMSource source=new DOMSource(comprobante.getDomNode());
			getTransofrmer().transform(source, res);
			String cadena=out.toString();
			//System.out.println("Transformacion: "+cadena);
			return cadena;
		} catch (Exception e) {
			e.printStackTrace();			
			throw new RuntimeException("Error leyendo XSLT para cadena original "+ExceptionUtils.getRootCauseMessage(e)
					,ExceptionUtils.getRootCause(e));
			
		}
	}
	
	private Transformer transformer;
	
	private String xsltPath;
	
	public Transformer getTransofrmer(){
		if(transformer==null){
			TransformerFactory transformerFactory=TransformerFactory.newInstance();			
			//String xslPath=System.getProperty("cfd.xslt.path","Z:\\CFD\\xslt\\v2.2\\cadenaoriginal_2_2.xslt");
			//String xslPath=System.getProperty("cfd.xslt.path");
			FileSystemResource xsltResource=new FileSystemResource(getXsltPath());
			StreamSource xslt;
			try {
				xslt = new StreamSource(xsltResource.getInputStream());
				transformer=transformerFactory.newTransformer(xslt);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			
		}
		return transformer;
	}
	
	
	
	public String transformation(String _factura, String _xslt, String _id)throws Exception
    {
        String cadenaOriginal = "";
        try
        {
            String fileTemp;
            
            if(_id == null){
                fileTemp = "~" + new Date().getTime();
            }else{
                fileTemp = "~" + _id;
            }
            
            File file = new File("C:\\",fileTemp);
            
            OutputStream out = new FileOutputStream(file);
            
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(new StreamSource(_xslt));
            
            transformer.transform(new StreamSource(new FileInputStream(_factura)), new StreamResult(out));
            
            InputStream in = new FileInputStream(new File("C:\\",fileTemp));
            InputStreamReader streamReader = new InputStreamReader(in,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            
            cadenaOriginal = bufferedReader.readLine();

            streamReader.close();
            in.close();
            out.close();
          
            if(cadenaOriginal.equals("|||")) cadenaOriginal = null;
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
            throw new Exception("Se presento un error al intentar leer el archivo temporal para extraer la cadena original: " + ex.getMessage());
        }
        return cadenaOriginal;
    }

	
	private File tempDir;
	
	public File getTempDir(){
		if(tempDir==null){
			String path=System.getProperty("cfd.temp.dir","C:\\sw3\\cfd\\cadenas");
			tempDir=new File(path);
			tempDir.mkdirs();
		}
		if(!tempDir.exists()){
			tempDir.mkdirs();
		}
		return tempDir;
		
	}

	public String getXsltPath() {
		return xsltPath;
	}

	public void setXsltPath(String xsltPath) {
		this.xsltPath = xsltPath;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.hasLength(xsltPath,"Se debe indicar la ruta de los archivos XSLT para la cadena original");
		
	}
	

}
