package com.luxsoft.cfd

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;



import org.apache.commons.lang.exception.ExceptionUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.encoders.Base64;
import org.springframework.core.io.FileSystemResource;

class SelladorService {

   
	private PrivateKey privateKey;
	private Certificado certificadoDigital
	
	 Certificado getCertificadoDigital(){
		if(certificadoDigital==null){
			certificadoDigital=Certificado.get(1)
		}
		return certificadoDigital
	}
	
	String getSello(String cadenaOrignal){
		try {
			//Agregar Provider
			Security.addProvider(new BouncyCastleProvider());
			final byte[] cadena=cadenaOrignal.getBytes("UTF-8");
			Signature signature=Signature.getInstance(getCertificadoDigital().algoritmo,"BC");
			signature.initSign(getPrivateKey());
			signature.update(cadena);
			
			final byte[] signedData=signature.sign();
			final byte[] encoedeData=Base64.encode(signedData);
			return new String(encoedeData,"UTF-8");
		} catch (Exception  e) {
			e.printStackTrace();
			String msg="Error generando sello digital: "+ExceptionUtils.getRootCauseMessage(e);
			throw new RuntimeException(msg,ExceptionUtils.getCause(e));
		} 
	}
	
	PrivateKey getPrivateKey(){
		if(privateKey==null){
			final byte[] encodedKey=leerArchivoDeLlavePrivada();
			PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(encodedKey);
			try {
				final KeyFactory keyFactory=KeyFactory.getInstance("RSA","BC");
				privateKey=keyFactory.generatePrivate(keySpec);
				log.info("PrivateKey object successfully loaded...");
				
			} catch (Exception e) {
				throw new RuntimeException("Error generando la llave privada", ExceptionUtils.getRootCause(e));
			}
		}
		return privateKey;
	}
	
	/**
	 * Lee el contenido del archivo de la llave privada y regresa su contenido
	 *
	 */
	private byte[] leerArchivoDeLlavePrivada(){
		
		FileSystemResource resource = new FileSystemResource(getCertificadoDigital().getPrivateKeyPath());
		FileInputStream keyfis;
		try {
			keyfis = new FileInputStream(resource.getFile());
			byte[] encodedPrivateKey = new byte[keyfis.available()];
			keyfis.read(encodedPrivateKey);
			keyfis.close();
			return encodedPrivateKey;
		} catch (IOException e) {
			throw new RuntimeException("Error tratando de leer la llave privada: "+ExceptionUtils.getRootCauseMessage(e),ExceptionUtils.getRootCause(e));
		}
		
	}
	
	
	
	X509Certificate certificado;
	
	X509Certificate getCertificado() {
		if(certificado==null){
			try{
				java.security.Security.addProvider(new BouncyCastleProvider());
				
				FileSystemResource publicKeyResource = new FileSystemResource(getCertificadoDigital().certificadoPath);
				System.out.println(publicKeyResource.getFile());
				CertificateFactory fact= CertificateFactory.getInstance("X.509","BC");
				certificado = (X509Certificate)fact.generateCertificate(publicKeyResource.getInputStream());
				certificado.checkValidity();
				return certificado;
			}catch (Exception e) {
				String msg=ExceptionUtils.getRootCauseMessage(e);
				throw new RuntimeException("Error tratando de leer Certificado: "+msg,e);
			}
		}
		return certificado;
		
	}
	
	
	def validar(final String cadenaOriginal,final String selloDigital){
		
		try {
			
			Signature signature=Signature.getInstance(getCertificadoDigital().getAlgoritmo(),"BC");
			signature.initVerify(getCertificado());
			signature.update(cadenaOriginal.getBytes("UTF-8"));
			byte[] decodedData=Base64.decode(selloDigital);
			return signature.verify(decodedData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error validando sello digital",ExceptionUtils.getRootCause(e));
		}
	}
	
}
