  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  let lang = getLanguage();
	  if (lang == undefined) {
		lang = 'en-GB';
	  }

	  let UIMessage = blockUIMessage[lang];
	  if (UIMessage == undefined) {
		  UIMessage = BLOCKUI_OVERLAY_MESSAGE_DEFAULT;
	  }

	  jq.blockUI({ css: { fontSize: '22px' }, message: UIMessage});
  }

  //https://www.w3schools.com/tags/ref_language_codes.asp - https://www.w3schools.com/tags/ref_country_codes.asp
  var blockUIMessage = {
			   'en-GB' : 'Please wait...',
			   'da-DK' : 'Vent venligst...',
			   'sv-SE' : 'Vänligen vänta...',
			   'no-NO' : 'Vennligst vent...'
  }
  
  function getLanguage() {
	  let language = navigator.languages && navigator.languages[0] ||
	  navigator.language ||
	  navigator.userLanguage;
	  
	  return language;
  }
  
  jq(function() {
	  jq("#user").focus();
  });
 
  
  jq(document).ready(function() {
	  console.log("Hi"); 
	  console.log("language",getLanguage());
  });
  
  
