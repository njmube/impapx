package com.luxsoft.impapx.contabilidad

import com.luxsoft.impapx.Diot
import groovy.sql.Sql

class DiotService {
	
	def dataSource

    def generarDiot(Date fecha) {
		
		Sql sql=new Sql(dataSource)
		String query="""
			SELECT '85' as tipo,c.id as ctaid,pd.id,z.tipo as tipoPol,z.folio,z.fecha,c.clave,c.descripcion as concepto,pd.descripcion,round(pd.debe,0) as debe,replace(replace(p.nombre,'.',''),',','') as nombre,p.rfc,p.nacional,p.pais_De_Origen as pais,p.nacionalidad
			FROM poliza_det pd
			join poliza z on(z.id=pd.poliza_id)
			join cuenta_contable c on(c.id=pd.cuenta_id) 
			join cuenta_por_pagar f on(f.id=pd.origen)
			join proveedor p on(p.id=f.proveedor_id)
			where date(pd.fecha) BETWEEN '@FECHA_INI' and '@FECHA_FIN' and pd.debe<>0  and c.id in(81) 
			UNION
			SELECT '03' as tipo,c.id as ctaid,pd.id,z.tipo as tipoPol,z.folio,z.fecha,c.clave,c.descripcion as concepto,pd.descripcion,round(pd.debe,0) as debe,replace(replace(p.nombre,'.',''),',','') as nombre,p.rfc,p.nacional,p.pais_De_Origen as pais,p.nacionalidad
			FROM poliza_det pd
			join poliza z on(z.id=pd.poliza_id)
			join cuenta_contable c on(c.id=pd.cuenta_id) 
			join cuenta_por_pagar f on(f.id=pd.origen)
			join proveedor p on(p.id=f.proveedor_id)
			where date(pd.fecha) BETWEEN '@FECHA_INI' and '@FECHA_FIN' and pd.debe<>0  and c.id=188
			UNION
			SELECT '85' as tipo,c.id as ctaid,pd.id,z.tipo as tipoPol,z.folio,z.fecha,c.clave,c.descripcion as concepto,pd.descripcion,round(pd.debe,0) as debe,replace(replace(p.nombre,'.',''),',','') as nombre,p.rfc,p.nacional,p.pais_De_Origen as pais,p.nacionalidad
			FROM poliza_det pd
			join poliza z on(z.id=pd.poliza_id)
			join cuenta_contable c on(c.id=pd.cuenta_id) 
			join cuenta_de_gastos f on(f.id=pd.origen)  -- se movio su entidad (cuenta_por_pagar)
			join proveedor p on(p.id=f.proveedor_id)
			where date(pd.fecha) BETWEEN '@FECHA_INI' and '@FECHA_FIN' and pd.debe<>0  and c.id=189
			UNION
			SELECT '85' as tipo,c.id as ctaid,pd.id,z.tipo as tipoPol,z.folio,z.fecha,c.clave,c.descripcion as concepto,pd.descripcion,round(pd.debe,0) as debe,replace(replace(p.nombre,'.',''),',','') as nombre,p.rfc,p.nacional,p.pais_De_Origen as pais,p.nacionalidad
			FROM poliza_det pd
			join poliza z on(z.id=pd.poliza_id)
			join cuenta_contable c on(c.id=pd.cuenta_id) 
			join pedimento f on(f.id=pd.origen)
			join embarque_det ed on(ed.pedimento_id=f.id)
			join embarque e on(e.id=ed.embarque_id)
			join proveedor p on(p.id=e.proveedor_id)
			where date(pd.fecha) BETWEEN '@FECHA_INI' and '@FECHA_FIN' and pd.debe<>0  and c.id=152
			UNION
			SELECT '85' as tipo,c.id as ctaid,pd.id,z.tipo as tipoPol,z.folio,z.fecha,c.clave,c.descripcion as concepto,pd.descripcion,round(pd.debe,0) as debe,replace(replace(p.nombre,'.',''),',','') as nombre,p.rfc,p.nacional,p.pais_De_Origen as pais,p.nacionalidad
			FROM poliza_det pd
			join poliza z on(z.id=pd.poliza_id)
			join cuenta_contable c on(c.id=pd.cuenta_id) 
			join pedimento f on(f.id=pd.origen)
			join embarque_det ed on(ed.pedimento_id=f.id)
			join embarque e on(e.id=ed.embarque_id)
			join proveedor p on(p.id=e.proveedor_id)
			where date(pd.fecha) BETWEEN '@FECHA_INI' and '@FECHA_FIN' and pd.debe<>0  and c.id=154
			UNION
			SELECT '85' as tipo,c.id as ctaid,pd.id,z.tipo as tipoPol,z.folio,z.fecha,c.clave,c.descripcion as concepto,pd.descripcion,round(pd.debe,0) as debe
			,(CASE WHEN pd.descripcion not like 'Comision %' THEN replace(replace(p.nombre,'.',''),',','') WHEN pd.descripcion like 'Comision %UBS%' THEN 'UBS AG' ELSE 'BANCO NACIONAL DE MEXICO' END) as nombre
			,p.rfc,p.nacional,p.pais_De_Origen as pais,p.nacionalidad
			FROM poliza_det pd
			join poliza z on(z.id=pd.poliza_id)
			join cuenta_contable c on(c.id=pd.cuenta_id) 
			join cuenta_por_pagar f on(f.id=pd.origen)
			join proveedor p on(p.id=f.proveedor_id)
			where date(pd.fecha) BETWEEN '@FECHA_INI' and '@FECHA_FIN' and pd.debe<>0  and ((c.padre_id=34 and c.id NOT IN(188,189,190,191,171,174,183,185,186,180,172,169)) or (c.id=189 and Z.tipo='EGRESO'))
			UNION
			SELECT '85' as tipo,c.id as ctaid,pd.id,z.tipo as tipoPol,z.folio,z.fecha,c.clave,c.descripcion as concepto,pd.descripcion,round((pd.haber/0.16),0) as debe,replace(replace(p.nombre,'.',''),',','') as nombre,p.rfc,p.nacional,p.pais_De_Origen as pais,p.nacionalidad
			FROM poliza_det pd
			join poliza z on(z.id=pd.poliza_id)
			join cuenta_contable c on(c.id=pd.cuenta_id) 
			join cuenta_por_pagar f on(f.id=pd.origen)
			join proveedor p on(p.id=f.proveedor_id)
			where date(pd.fecha) BETWEEN '@FECHA_INI' and '@FECHA_FIN' and pd.HABER<>0  and c.id  IN(65)
			"""
		query=query.replaceAll("@FECHA_INI", fecha.inicioDeMes().format("yyyy/MM/dd"))
		query=query.replaceAll("@FECHA_FIN", fecha.finDeMes().format("yyyy/MM/dd"))
		//println query
		def rows=sql.rows(query)
		rows.sort{it.nombre}
		
		Map map=rows.groupBy([
			{it.nombre}
		])
		
		def diots=map.collect {
			def base=it.value.sum(0){rr-> rr.debe}
			def ob=it.value.find {rr-> rr.nombre=it.key}
			def rfc=''
			
			def nacional=it.value.find {rr-> rr.nacional}
			def tipo=it.value.find {rr-> rr.tipo}?.tipo
			def pais=it.value.find {rr-> rr.pais}?.pais
			def nacionalidad=it.value.find {rr-> rr.nacionalidad}?.nacionalidad
			if(ob){
				rfc=ob.rfc
			}
			new Diot(proveedor:it.key,base:base,rfc:rfc,nacional:nacional,tipo:tipo,pais:pais,nacionalidad:nacionalidad)
		}
		
		//def genericos=diots.findAll{it.base<50.000}
		def gdiots=[]
		
		def dg=new Diot(proveedor:'PROVEEDOR GLOBAL',tipo:'85',base:0,rfc:'',excento:0)
		diots.each{
			if(it.base>50000.00)
				gdiots.add(it)
			else{
				if(it.proveedor.startsWith("UBS AG"))
					dg.excento=dg.excento+it.base
				else
					dg.base=dg.base+it.base
			}
		}
		if(dg.base>0)
			gdiots.add(dg)
		gdiots.sort{it.base}
		return [rows:rows,gdiots:gdiots]

    }
}
