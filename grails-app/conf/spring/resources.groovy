import org.springframework.web.servlet.i18n.FixedLocaleResolver;

// Place your Spring DSL code here
beans = {
	customPropertyEditorRegistar(util.CustomPropertyEditorRegistar){
		dateEditor={util.CustomDateEditor e->
			formats=['dd/MM/yyyy','dd/MM/yy']
			allowEmpty=true
		}
	}
	/*
	localeResolver(org.springframework.web.servlet.i18n.SessionLocaleResolver){
		defaultLocale=new Locale("es","mx")
		java.util.Locale.setDefault(defaultLocale)
	}*/
	localeResolver(FixedLocaleResolver,Locale.US){
		defaultLocale=new Locale("es","mx")
		Locale.setDefault(defaultLocale)
	}
}
