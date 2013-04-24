/**
 * Implementacion preferida de dataTables
 * 
 */
$(function(){
		$("#grid").dataTable({
			"sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
			aLengthMenu: [[100, 150, 200, 250, -1], [100, 150, 200, 250, "Todos"]],
          	iDisplayLength: 100,
          	"oLanguage": {
      			"oPaginate": {
        			"sFirst": "Inicio",
        			"sNext": "Siguiente",
        			"sPrevious": "P‡gina anterior",

      				},
      				"sSearch": "Filtrar:",
    			},
    			"sEmptyTable": "No data available in table",
    			"sLoadingRecords": "Please wait - loading...",
    			"sProcessing": "DataTables is currently busy",
    			"bPaginate": false,
    			"bInfo": false,
    			"sSearch": "Filtrar:"
    			//"oSearch": {"sSearch": "Filtrar"}

			});
});