	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var map = {};
  	
  	jq(function() {
  	  jq("#datumTODO").datepicker({ 
  		  //dateFormat: 'yymmdd', 
  		  dateFormat: 'ddmmy', 
  		  defaultDate: "-6m"	  
  	  });
  	  
    });


//---------------------------------
  	  //START Model dialog: "Logger"
  	  //------------------------------
  	  //Initialize <div> here
  	  jq(function() { 
  		  jq("#dialogETWcalculator").dialog({
  			  autoOpen: false,
  			  maxWidth:800,
  	          maxHeight: 800,
  	          width: 420,
  	          height: 380,
  			  modal: true
  		  });
  	  });

  	  jq(function() {
  		  jq("#imgLinkETWcalc").click(function() {
  			  presentCalculatorDialog();
  		  });
  	  });
  	  
  	  
  	  //---------------------
  	  //PRESENT PWD DIALOG
  	  //---------------------
  	  function presentCalculatorDialog(){
  		  jq('#dialogETWcalculator').dialog( "option", "title", "ETW CO2 Calculator" );
  		  //deal with buttons for this modal window
  		  jq('#dialogETWcalculator').dialog({
  			 buttons: [ 
  	            {
  				 id: "dialogSaveTU",	
  				 text: "Calculate",
  				 click: function(){
  					 		if(jq("#avd").val() != "" && jq("#opd").val() != "" && jq("#weight").val() != "" && jq("#avslk").val() != ""
								&& jq("#avspk").val() != "" && jq("#motlk").val() != "" && jq("#motpk").val() != ""){
  					 			
								jq('#formETWcalculator').attr("target","_blank");
								jq('#formETWcalculator').submit();
								
				 			}else{
								alert("Mandatory fields missing ...");
							}
  					 		
  			 			}
  			 	 },
  	  			{
  			 	 id: "dialogCancelTU",
  			 	 text: "Cancel", 
  				 click: function(){
  					 		jq( this ).dialog( "close" ); 
  				 		} 
  	 	 		 } ] 
  		  });
  		  jq('#dialogETWcalculator').dialog('open');
  	  }
  	  
/* Using a _blank window instead ...*/
function target_popup(form) {
    //window.open('', 'formpopup', 'top=600px,left=300px,height=200px,width=1200px,scrollbars=yes,status=no,location=no');
    //form.target = 'formpopup';
}
    
    
