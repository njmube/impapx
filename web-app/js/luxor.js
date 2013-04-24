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

//forceNumeric() plug-in implementation
jQuery.fn.forceNumeric = function () {

    return this.each(function () {
        $(this).keydown(function (e) {
            var key = e.which || e.keyCode;

            if (!e.shiftKey && !e.altKey && !e.ctrlKey &&
            // numbers   
                key >= 48 && key <= 57 ||
            // Numeric keypad
                key >= 96 && key <= 105 ||
            // comma, period and minus, . on keypad
               key == 190 || key == 188 || key == 109 || key == 110 ||
            // Backspace and Tab and Enter
               key == 8 || key == 9 || key == 13 ||
            // Home and End
               key == 35 || key == 36 ||
            // left and right arrows
               key == 37 || key == 39 ||
            // Del and Ins
               key == 46 || key == 45)
                return true;

            return false;
        });
    });
}