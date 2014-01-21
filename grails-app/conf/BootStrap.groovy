import com.luxsoft.impapx.sec.*
import org.bouncycastle.jce.provider.BouncyCastleProvider
import util.Rounding;

class BootStrap {
	
	
	
    def init = { servletContext ->
		
		// incorporar el metodos inicioDeMes y finDeMes a la clase Date
		Date.metaClass.inicioDeMes{ ->
			def d1=delegate.clone()
			d1.putAt(Calendar.DATE,1)
			return d1.clearTime()
		
		}
		
		Date.metaClass.finDeMes{ ->
			Calendar c2=delegate.clone().toCalendar()
			c2.putAt(Calendar.DATE,c2.getActualMaximum(Calendar.DATE))
			return c2.getTime().clearTime()
		}
		
		Date.metaClass.text{ ->
			return delegate.format('dd/MM/yyyy')
		}
		
		Date.metaClass.toMonth{ ->
			return delegate.getAt(Calendar.MONTH)+1
		}
		Date.metaClass.toYear{
			return delegate.getAt(Calendar.YEAR)
		}
		Date.metaClass.asPeriodoText{
			return delegate.format('MMMM - yyyy')
		}
		
		
		def admin=User.findByUsername('admin')
		if(!admin){
			admin=new User(username:"admin"
				,password:"admin"
				,enabled:true).save(flush:true);
		}
		def anonimo=User.findByUsername('user')
		if(!anonimo){
			anonimo=new User(username:"user",password:"user",enabled:true).save(flush:true)
		}
		
		def role1=Role.findOrSaveWhere(authority:'ROLE_ADMIN')
		def role2=Role.findOrSaveWhere(authority:'ROLE_USER')
		def role3=Role.findOrSaveWhere(authority:'ROLE_TESORERIA')
		def role4=Role.findOrSaveWhere(authority:'ROLE_COMPRAS')
		def role5=Role.findOrSaveWhere(authority:'ROLE_CONTABILIDAD')
		
		if(!admin.authorities.contains(role1))
			UserRole.create(admin, role1, true)
		if(!admin.authorities.contains(role3))
			UserRole.create(admin, role3, true)
		
		if(!anonimo.authorities.contains(role2))
			UserRole.create(anonimo, role2, true)
			
		//Usuario de tesoreria
		
		def tesUser1=User.findByUsername('varevalo')
		if(!tesUser1){
			tesUser1=new User(username:"varevalo",password:"varevalo",enabled:true).save(flush:true)
		}
		
		if(!tesUser1.authorities.contains(role2))
			UserRole.create(tesUser1, role2, true)
		if(!tesUser1.authorities.contains(role3))
			UserRole.create(tesUser1, role3, true)
			
		//Compras
		def compras1=User.findByUsername('asanchez')
		if(!compras1){
			compras1=new User(username:"asanchez",password:"asanchez",enabled:true).save(flush:true)
		}	
		if(!compras1.authorities.contains(role2))
			UserRole.create(compras1, role2, true)
		if(!compras1.authorities.contains(role4))
			UserRole.create(compras1, role4, true)
		
		def compras2=User.findByUsername('faudelo')
		if(!compras2){
			compras2=new User(username:"faudelo",password:"faudelo",enabled:true).save(flush:true)
		}
		if(!compras2.authorities.contains(role2))
			UserRole.create(compras2, role2, true)
		if(!compras2.authorities.contains(role4))
			UserRole.create(compras2, role4, true)
		
		def conta1=User.findByUsername('mbolanos')
		if(!conta1){
			conta1=new User(username:"mbolanos",password:"ebola",enabled:true).save(flush:true)
		}
		if(!conta1.authorities.contains(role5))
			UserRole.create(conta1, role5, true)
			
		def conta2=User.findByUsername('restrada')
		if(!conta2){
			conta2=new User(username:"restrada",password:"re3515",enabled:true).save(flush:true)
		}
		if(!conta2.authorities.contains(role5))
			UserRole.create(conta2, role5, true)
			
		def conta3=User.findByUsername('jmorales')
		if(!conta3){
			conta3=new User(username:"jmorales",password:"mo4515",enabled:true).save(flush:true)
		}
		if(!conta3.authorities.contains(role5))
			UserRole.create(conta3, role5, true)
		java.security.Security.addProvider(new BouncyCastleProvider())
		
    }
	
    def destroy = {
    }
}
