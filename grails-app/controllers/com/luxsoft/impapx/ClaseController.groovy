package com.luxsoft.impapx

class ClaseController {
	
	def scaffold=true

    def index() {
		redirect(action:'list') 
	}
}
