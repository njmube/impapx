modules = {
    application {
        resource url:'js/application.js'
    }
	
	scaffolding {
		dependsOn 'bootstrap'
		resource url: 'css/scaffolding.css'
	}
	luxor{
		dependsOn 'bootstrap'
		resource url: 'css/luxor.css'
		resource url: 'js/luxor.js'
		resource url: 'js/datepicker.js'
	}
	datepicker{
		dependsOn 'jquery'
		resource url: 'js/datepicker.js'
	}
	luxorForms{
		dependsOn 'jquery'
		resource url: 'js/jquery.maskedinput-1.3.js'
		resource url: 'js/jquery.formatCurrency-1.4.0.js'
		resource url: 'js/jquery.formatCurrency.es-MX.js'
		resource url: 'js/autoNumeric-1.7.5.js'
		
	}
	dataTables{
		dependsOn 'jquery'
		resource url: 'js/jquery.dataTables.js'
		resource url: 'js/DT_bootstrap.js'
		resource url: 'css/data_table.css'
		resource url: 'js/dataTables.fnReloadAjax.js'
		resource url: 'js/jquery.dataTables.columnFilter.js'
		resource url: 'js/jquery.dataTables.fixedHeader.min.js'
	}
	
	luxorTableUtils{
		dependsOn 'jquery'
		resource url:'js/luxorTableUtils.js'
	}
	
	luxorSimpleTable{
		dependsOn 'dataTables'
		resource url: 'js/luxorSimpleTable.js'
	}
	autoNumeric{
		dependsOn 'jquery' 
		resource url: 'js/autoNumeric-1.7.5.js'
	}
	
	luxorPrint{
		dependsOn 'jquery'
		resource url: 'js/jquery.printElement.js'
	}
}

