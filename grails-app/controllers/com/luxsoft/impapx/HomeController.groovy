package com.luxsoft.impapx


import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class HomeController {

	def index() {
		if(!isLoggedIn()){
			redirect (controller:'login',params: [empresa: 'SIN EMPRESA REGISTRADA'])
		}
		/*
		println 'Home controller'
		def config = SpringSecurityUtils.securityConfig

		if (isNotLoggedIn()) {
			redirect uri: config.successHandler.defaultTargetUrl
			return
		}

		String view = 'index'
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		println 'POST URL:'+postUrl
		render view: view, model: [postUrl: postUrl,
					rememberMeParameter: config.rememberMe.parameter]*/
					
	}

	def info(){

	}
}
