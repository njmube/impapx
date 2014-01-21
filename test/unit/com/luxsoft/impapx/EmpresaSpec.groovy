package com.luxsoft.impapx

import static org.junit.Assert.*

import java.security.PrivateKey;

import grails.test.mixin.*
import grails.test.mixin.support.*

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.junit.*

import spock.lang.Specification;

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class EmpresaSpec extends Specification{
	
	def setup(){
		java.security.Security.addProvider(new BouncyCastleProvider())
	}
	
	void "Validar la lectura del certificado en formato X509"(){
		
		given:'Un certificado'
		File certificado=new File("web-app/cfd/00001000000202323568.cer")
		assert certificado.exists(),"No existe el ceertificado de prueba: "+certificado.path
		byte[] data=certificado.getBytes()
		assert data
		
		and:'Una empresa '
		def empresa=new Empresa(certificadoDigital:data)
		
		when:'Asignamos un certificado a la empresa'
		empresa.certificadoDigital=data
		
		then:'El certificado en formato X509Certificate es accesible'
		empresa.certificado
		println empresa.certificado.getSubjectX500Principal()
	}
	
	void "Validar lectura de la llave privada en formato PKCS8EncodedKeySpec "(){
		given:'Un archivo de llave privada'
		File pk=new File("web-app/cfd/impap2012.key")
		assert pk.exists()
		assert pk.getBytes()
		
		and:'Una empresa'
		def empresa=new Empresa(llavePrivada:pk.getBytes())
		
		when:'Se accesa la propiedad privateKey'
		PrivateKey key=empresa.privateKey
		
		then:'La llave es valida'
		key
		println key
		
	}

    
}
