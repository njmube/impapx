$(function()
{
     $(".datepicker").datepicker({
    	 dateFormat: 'dd/mm/yy',
         showOtherMonths: true,
         selectOtherMonths: true,
         showOn:'focus',
         showAnim:'fold',
         minDate:'01/12/2009',
         maxDate:'31/12/2015',
         navigationAsDateFormat:false,
         showButtonPanel: true,
         changeMonth:false,
         changeYear:false,
         closeText:'Cerrar'
         
      });
      
      $(".datepicker-btn").click(function(){
    	  //console.log('click button');
    	  $(".datepicker").datepicker("show");
      });
          		
});