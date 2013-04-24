if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}
var confirmed=false;
function myConfirm2(obj,message,titulo) {
	// return confirm('Confirmar ?');

	if (!confirmed) {
		$('<div></div>').html("<p>"+message+"</p>").dialog({
			resizable : false,
			show : "blind",
			hide : "explode",
			modal : true,
			title : titulo,
			buttons : {
				"Aceptar" : function() {
					$(this).dialog("close");
					confirmed = true;
					obj.click();
				},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
			}
		}).dialog("open");
	}

	return confirmed;
}
