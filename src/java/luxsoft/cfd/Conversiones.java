package luxsoft.cfd;

import org.apache.commons.lang.StringUtils;

import com.luxsoft.impapx.Direccion;

import mx.gob.sat.cfd.x2.TUbicacion;
import mx.gob.sat.cfd.x2.TUbicacionFiscal;

public class Conversiones {
	
	public static TUbicacionFiscal getTUbicacionFiscal(final Direccion direccion,final TUbicacionFiscal domicilio){
		domicilio.setCalle(direccion.getCalle());
		domicilio.setCodigoPostal(direccion.getCodigoPostal());
		domicilio.setColonia(direccion.getColonia());
		domicilio.setEstado(direccion.getEstado());
		domicilio.setMunicipio(direccion.getMunicipio());
		domicilio.setNoExterior(direccion.getNumeroExterior());
		domicilio.setPais(direccion.getPais());
		return domicilio;
	}
	
	public static TUbicacion getTUbicacion(final Direccion direccion,TUbicacion domicilio){
		domicilio.setCalle(StringUtils.defaultString(direccion.getCalle()));
		domicilio.setCodigoPostal(StringUtils.defaultString(direccion.getCodigoPostal()));
		domicilio.setColonia(StringUtils.defaultString(direccion.getColonia()));
		domicilio.setEstado(StringUtils.defaultIfEmpty(direccion.getEstado(),"."));
		domicilio.setMunicipio(StringUtils.defaultString(direccion.getMunicipio()));
		domicilio.setNoExterior(StringUtils.defaultIfEmpty(direccion.getNumeroExterior(),"."));
		domicilio.setNoInterior(StringUtils.defaultIfEmpty(direccion.getNumeroInterior(),"."));
		domicilio.setPais(direccion.getPais());
		return domicilio;
	}

}
