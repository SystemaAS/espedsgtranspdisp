  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  jq(document).ready(function() {
		//jq('#updCancelButton').hide();
  });
  
  //Global functions
  function g_getCurrentYearStr(){
	  return new Date().getFullYear().toString();
  }
  function g_getCurrentMonthStr(){
	  var currentMonth = new Date().getMonth() + 1;
	  var currentMonthStr = currentMonth.toString();
	  if (currentMonth < 10) { currentMonthStr = '0' + currentMonth; }
	  return currentMonthStr;
  }
  
  jq(function() {
	  jq('#ffunnr').focus(function() {
		  if(jq('#ffunnr').val()!=''){
			  refreshCustomValidity(jq('#ffunnr')[0]);
		  }
	  });
	  jq('#ffante').focus(function() {
		  if(jq('#ffante').val()!=''){
			  refreshCustomValidity(jq('#ffante')[0]);
		  }
	  });
	  jq('#ffantk').focus(function() {
		  if(jq('#ffantk').val()!=''){
			  refreshCustomValidity(jq('#ffantk')[0]);
		  }
	  });
	  jq('#ffenh').focus(function() {
		  if(jq('#ffenh').val()!=''){
			  refreshCustomValidity(jq('#ffenh')[0]);
		  }
	  });
  });
  
  /*
  jq(function() {
	  jq("#bufedt").datepicker({ 
		  onSelect: function(date) {
		  	jq("#bufedt").focus();
	      },
		  dateFormat: 'yymmdd',
		  firstDay: 1 //monday
		  /*showOn: "button",
	      buttonImage: "resources/images/calendar.gif",
	      buttonImageOnly: true,
	      buttonText: "Select date" 
		  
		  //dateFormat: 'ddmmy', 
	  });
	  jq("#bufedt").blur(function(){
		  //now check the user input alternatives
		  var str = jq("#bufedt").val();
		  if(str!=''){
			  var length = str.length;
			  if(length==2){
				  jq("#bufedt").val(g_getCurrentYearStr() + g_getCurrentMonthStr() + str);  
			  }else if (length==4){
				  var userDay = str.substring(0,2);
				  var userMonth = str.substring(2,4);
				  jq("#bufedt").val(g_getCurrentYearStr() + userMonth + userDay);
			  }
		  }
	  });
	  
  });
  */
  jq(function() {
	  jq('#newRecordButton').click(function() {
		  jq('#isModeUpdate').val("");
		  //HIDDEN
		  jq('#editLineNr').text("");
		  jq('#fflin2').val("");
		  jq('#ffklas').val("");
		  jq('#ffsedd').val("");
		  jq('#fftres').val("");
		  jq('#fffakt').val("");
		  //REST of FIELDS
		  jq('#ffunnr').val("");
		  jq('#ffembg').val("");
		  jq('#ffindx').val("");
		  jq('#ffantk').val("");
		  jq('#ffante').val("");
		  jq('#ffenh').val("");
		  
	  });
  });
  
  //Links on child windows
  jq(function() {
	  //frisokvei koder child window search
	  jq('#fskodeIdLink').click(function() {
		jq('#fskodeIdLink').attr('target','_blank');  
		window.open('transportdisp_workflow_childwindow_frisokveicodes.do?action=doFind',"frisokveiCodesWin","top=300px,left=50px,height=600px,width=650px,scrollbars=no,status=no,location=no");
	  });
	  //frisokvei giltighetskoder
	  jq('#fsverdiIdLink').click(function() {
			jq('#fsverdiIdLink').attr('target','_blank');  
			window.open('transportdisp_workflow_childwindow_frisokveicodes_giltihetslist.do?action=doFind&avd=' + jq('#avd').val() + '&opd=' + jq('#opd').val() + '&kode=' + jq('#fskode').val(),"frisokveiCodesWin","top=300px,left=100px,height=600px,width=650px,scrollbars=no,status=no,location=no");
	  });
	  //frisokvei dok.koder child window search
	  jq('#fsdokkIdLink').click(function() {
		jq('#fsdokkIdLink').attr('target','_blank');  
		window.open('transportdisp_workflow_childwindow_frisokveidoccodes.do?action=doFind',"frisokveiDocCodesWin","top=300px,left=150px,height=600px,width=650px,scrollbars=no,status=no,location=no");
	  });
	  
	  
  });
  
  /*

  jq(function() {
  	//----------------------
	//Validate supplier id
	//----------------------
	jq("#bulnr").blur(function() { 
			jq.ajax({
		  	  type: 'GET',
		  	  url: 'validateSupplier_TransportDisp.do',
		  	  data: { applicationUser : jq('#applicationUser').val(), 
		  		  	  id : jq('#bulnr').val() }, 
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  success: function(data) {
		  		var len = data.length;
		  		if(len==1){
		  			jq("#bulnr").removeClass( "isa_error" );
		  			jq("#submit").removeAttr("disabled");
					for ( var i = 0; i < len; i++) { 
						jq('#bulnr').val(data[i].levnr);
						jq('#levNavn').val(data[i].lnavn);	
					}
		  		}else{
		  			if(jq("#bulnr").val()!=''){
		  				jq('#levNavn').val("");
		  				jq("#bulnr").addClass( "isa_error" );
		  				jq("#submit").attr("disabled", true);
		  			}else{
		  				jq("#bulnr").removeClass( "isa_error" );
		  				jq("#submit").removeAttr("disabled");
		  			}
		  		}
		  		
		  	  },
		  	  error: function() {
		  	    alert('Error loading ...');
		  	    jq("#bulnr").addClass( "isa_error" );
		  	    jq("#submit").attr("disabled", true);
		  	  }
		  	}); 
	});
	
	//-------------------------
	//Validate Carrier (transp)
	//-------------------------
	/* TODO!! - Defect on SEARCH function: JOVO must implement getval=J on (CBs): http://gw.systema.no/sycgip/TJINQTNR.pgm
	jq('#butnr').blur(function() {
		jq.ajax({
	  	  type: 'GET',
	  	  url: 'searchTranspCarrier_TransportDisp.do',
	  	  data: { applicationUser : jq('#applicationUser').val(), 
	  		  	  id : jq('#butnr').val() }, 
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
	  		if(len==1){
	  			jq("#butnr").removeClass( "isa_error" );
	  			jq("#submit").removeAttr("disabled");
				for ( var i = 0; i < len; i++) { 
					jq('#traNavn').val(data[i].navn);	
				}
	  		}else{
	  			if(jq("#butnr").val()!=''){
	  				jq('#traNavn').val("");
	  				jq("#butnr").addClass( "isa_error" );
	  				jq("#submit").attr("disabled", true);
	  			}else{
	  				jq("#butnr").removeClass( "isa_error" );
	  				jq("#submit").removeAttr("disabled");
	  			}
	  		}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	    jq("#butnr").addClass( "isa_error" );
			jq("#submit").attr("disabled", true);
	  	  }
	  	}); 
		
	});
	
  });
  */
  
  //-------------------
  //Fetch specific line
  //-------------------
  function getItemData(record) {
		var lin2 = record.id;
	  	var applicationUser = jq('#applicationUser').val();
	  	//alert(htmlValue);
	  	lin2 = lin2.replace("recordUpdate_","");
	  	var requestString = "user=" + jq('#applicationUser').val() + "&avd=" + jq('#avd').val() + "&opd=" + jq('#opd').val() + "&mode=U" +
	  						"&lin=" + jq('#linNr').val() + "&lin2=" + lin2; 
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getDangerousGoodsDetailLine_TransportDisp.do',
	  	  data: { applicationUser : applicationUser, 
	  		  	  requestString : requestString }, 
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//Update / Create
				jq('#isModeUpdate').val("");jq('#isModeUpdate').val("true");
				//KEY
				jq('#editLineNr').text("");jq('#editLineNr').text(data[i].fflin2);
				jq('#fflin2').val("");jq('#fflin2').val(data[i].fflin2);
				//REST of FIELDS
				jq('#ffunnr').val("");jq('#ffunnr').val(data[i].ffunnr);
				jq('#ffembg').val("");jq('#ffembg').val(data[i].ffembg);
				jq('#ffindx').val("");jq('#ffindx').val(data[i].ffindx);
				jq('#ffantk').val("");jq('#ffantk').val(data[i].ffantk);
				jq('#ffante').val("");jq('#ffante').val(data[i].ffante);
				jq('#ffenh').val("");jq('#ffenh').val(data[i].ffenh);
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
	  	
	}

  	//---------------------------------------
	//DELETE Invoice line
	//This is done in order to present a jquery
	//Alert modal pop-up
	//----------------------------------------
	function doDeleteItemLine(element){
	  //start
		var lin2 = element.id;
		lin2 = lin2.replace("lin2_","");
		
	  //Start dialog
	  jq('<div></div>').dialog({
      modal: true,
      title: "Dialog - Slett linje: " + lin2,
      buttons: {
	        Fortsett: function() {
      		jq( this ).dialog( "close" );
	            //do delete
	            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	            window.location = "transportdisp_workflow_dangerousgoods_edit.do?action=doDelete" + "&avd=" + jq('#avd').val() + "&opd=" + jq('#opd').val() + "&linNr=" + jq('#linNr').val() + "&fflin2=" + lin2;
	            
	        },
	        Avbryt: function() {
	            jq( this ).dialog( "close" );
	        }
      },
      open: function() {
	  		  var markup = "Er du sikker på at du vil slette denne?";
	          jq(this).html(markup);
	          //make Cancel the default button
	          jq(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
	     }
	  });  //end dialog
	}
	 
  