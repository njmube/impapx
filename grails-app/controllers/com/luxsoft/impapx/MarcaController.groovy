package com.luxsoft.impapx

class MarcaController {
	
	def scaffold=true

    def index() {
		redirect(action:'list') 
	}
}
