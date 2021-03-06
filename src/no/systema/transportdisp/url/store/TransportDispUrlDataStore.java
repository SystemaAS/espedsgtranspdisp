/**
 * 
 */
package no.systema.transportdisp.url.store;
import no.systema.main.model.UrlDataStoreAnnotationForField;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Mar 31, 2015
 * 
 * 
 */
public final class TransportDispUrlDataStore {
	
	//----------------------------
	//[1] FETCH WORKFLOW LIST
	//----------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowHeaderController - transportdisp_workflow_getTrip.do, etc ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_LIST_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR01.pgm";
	//http://gw.systema.no/sycgip/TJETUR01.pgm?user=JOVO
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder.do, etc ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_AVD_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_AVD_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE16R.pgm";
	//http://gw.systema.no/sycgip/TJGE16R.pgm?user=OSCAR&ie=A 
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController/@TransportDispWorkflowControllerChildWindow - searchBilnr_TransportDisp.do, transportdisp_workflow_childwindow_bilnr.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_BILNR_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_BILNR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQBILN.pgm";
	//http://gw.systema.no/sycgip/TJINQBILN.pgm?user=JOVO&sokbnr=SC 
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_bilcode.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_BILCODE_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_BILCODE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQBILK.pgm";	
	//http://gw.systema.no/sycgip/TJINQBILK.pgm?user=JOVO&soknvn=A
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - searchTranspCarrier_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_CARRIER_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_CARRIER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQTNR.pgm";	
	//http://gw.systema.no/sycgip/TJINQTNR.pgm?user=JOVO&soknvn=A 
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - searchDriver_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_DRIVER_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_DRIVER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQDRIV.pgm";	
	//http://gw.systema.no/sycgip/TJINQDRIV.pgm?user=JOVO&soksjn=A
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - searchPostNumber_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_POSTAL_CODES_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_POSTAL_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQSTED.pgm";
	//(FRA)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=FRALK&VARKOD=FRA&SOKLK=NO&WSKUNPA=A (A, P eller blank) 
	//(TIL)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=TILLK&VARKOD=TIL&SOKLK=NO& 
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_customer.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQKUND.pgm";
	//http://gw.systema.no/sycgip/TJINQKUND.pgm?user=JOVO&sokknr=1 
	//flera parametrar är: soknvn, kunpnsted, wsvarnv, maxv
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - searchCustomer_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_DELIVERY_ADDRESS_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_DELIVERY_ADDRESS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQVADR.pgm";
	//http://gw.systema.no/sycgip/TJINQVADR.pgm?user=JOVO&wkundnr=7031&wvadrnr=1 
	//http://gw.systema.no/sycgip/TJINQVADR.pgm?user=JOVO&wkundnr=7031&wvadrna=A (all addresses)
	//...if not empty this will override the customer address (fetched with TJINQKUND.pgm...)
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_loadunloadplaces.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_LOAD_UNLOAD_PLACES_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_LOAD_UNLOAD_PLACES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQSDL.pgm";
	//http://gw.systema.no/sycgip/TJINQSDL.pgm?user=JOVO(return all)
	//http://gw.systema.no/sycgip/TJINQSDL.pgm?user=JOVO&soknvn=T... etc
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_uploadFile.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_UPLOAD_FILE_VALIDATION_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_UPLOAD_FILE_VALIDATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR07A.pgm";	
	//http://gw.systema.no/sycgip/TJETUR07A.pgm?user=OSCAR&wsdokn=tarzan.jpg
	//{ "user": "OSCAR", "wsdokn": "tarzan.jpg","valids": "Y", "tmpdir": "/pdf/tmp/", "errMsg": "", "chksuffix": [] } 
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_uploadFile.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_UPLOAD_FILE_AFTER_VALIDATION_APPROVAL_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_UPLOAD_FILE_AFTER_VALIDATION_APPROVAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR07B.pgm";	
	//http://gw.systema.no/sycgip/TJETUR07B.pgm?user=JOVO&wstur=75000002&wsdokn=/pdf/tmp/ukkulele.jpg&wsalias=trumpet.jpg 
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_dangerousgoods.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_DANGEROUS_GOODS_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_DANGEROUS_GOODS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQUNNR.pgm";
	//Note: gir alle poster (max 100)  fra og med unnr 1950 ved &fullinfo  ulik J returneres kun unnr /emb.gruppe/ index og kort tekst (max 50 lang) 
	//[1]http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1950=&embg=&indx=&getval=&fullinfo=J
	//Note: Ved &getval=J sender en gjerne inn også embg + index (om dette er ulikt blank).. 
	//[2]http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1950=&embg=&indx=G&getval=J&fullinfo=J
	//Note: Exakt match
	//[3]http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1950=&embg=&indx=G&getval=J&fullinfo=J
	//[3.1]
	//J=Krever FULL match (båd unnr / embg / indx) :
	//http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1202=&embg=III&indx=&matchOnly=J
	//[3.2]
	//E=Krever match på unnr / embg:
	//http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1202=&embg=III&indx=&matchOnly=E
	//[3.3]
	//U=Krever kun match på  unnr :
	//http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1202=&embg=&indx=&matchOnly=U
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_packingcodes.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_PACKING_CODES_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_PACKING_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQFORP.pgm";
	//http://gw.systema.no/sycgip/TJINQFORP.pgm?user=JOVO&kode=ABCD&Getval=J&fullInfo=J
	//http://gw.systema.no/sycgip/TJINQFORP.pgm?user=JOVO&kode=A (sök alla fom A)
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_tollstedcodes.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_TOLLSTED_CODES_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_TOLLSTED_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQTSTE.pgm";
	//http://gw.systema.no/sycgip/TJINQTSTE.pgm?user=JOVO&kode=0503&tekst=&Getval=J&fullInfo=J
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_gebyrcode.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_GEBYR_CODES_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_GEBYR_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQGEB.pgm";
	//http://gw.systema.no/sycgip/TJINQGEB.pgm?user=JOVO&kode=&tekst=&fullinfo=J
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_supplier.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_SUPPLIER_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_SUPPLIER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQLEV.pgm";
	//http://gw.systema.no/sycgip/TJINQLEV.pgm?user=JOVO&kode=5000&tekst=&Getval=N&fullInfo=J
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_frisokveicodes.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_FRISOKVEI_CODES_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_FRISOKVEI_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE30R.pgm";
	//http://gw.systema.no/sycgip/tjge30r.pgm?user=JOVO
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_frisokveidoccodes.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_FRISOKVEI_DOCCODES_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_FRISOKVEI_DOCCODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE32R.pgm";
	//http://gw.systema.no/sycgip/tjge32r.pgm?user=JOVO&kftyp=FSDOKK
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_trackandtrace_edit.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_TTRACE_CODES_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_TTRACE_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQTTA.pgm";
	//http://gw.systema.no/sycgip/TJINQTTA.pgm?user=JOVO&sokkod=A ... om sokkod er blank får du alle
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - sendSMS_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/tjop11hs.pgm";
	//http://gw.systema.no/sycgip/tjop11hs.pgm?user=JOVO&avd=75&opd=108&type=&smsnr=48052470
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - sendSMS_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_GENERAL_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_GENERAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/tjop11ht.pgm";
	//http://gw.systema.no/sycgip/tjop11ht.pgm?user=JOVO&avd=75&opd=108&type=&smsnr=48052470&merknad1=...
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - sendSMSFromTur_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_FROM_TUR_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_FROM_TUR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/tjfa55s.pgm";
	//http://gw.systema.no/sycgip/tjfa55s.pgm?user=JOVO&tur=80000060&smsnr=48052470&sprak=EN
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - sendEmailFromTur_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_EMAIL_FROM_TUR_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_EMAIL_FROM_TUR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/tjfa55m.pgm";
	//http://gw.systema.no/sycgip/tjfa55m.pgm?user=JOVO&tur=75000020&merk=Dette_er_en_merknad&mail=janottar@systema.no&sprak=EN
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - sendEmail_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_EMAIL_URL")
	static public String TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_EMAIL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/tjop11h.pgm";
	//http://gw.systema.no/sycgip/tjop11h.pgm?user=OSCAR&avd=75&opd=108&merk=Dette_er_en_merknad&mail=oscar@systema.no&sprak=EN
	
	
		
	//---------------------------------------------------
	//[1] GENERAL CODES - for country (AS400 from TVINN) 
	//---------------------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_country.do ", description=" --> TRANSPORT_DISP_CODES_URL")
	static public String TRANSPORT_DISP_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG005R.pgm"; 
	//http://gw.systema.no/sycgip/TNOG005R.pgm?user=OSCAR&typ=2 //country list
	
	//---------------------------------------------------
	//[1.1] GENERAL FUNCTIONS eg.(signature, other...) 
	//---------------------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder.do ", description=" --> TRANSPORT_DISP_GENERAL_SIGN_URL")
	static public String TRANSPORT_DISP_GENERAL_SIGN_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE24R.pgm"; 
	//http://gw.systema.no/sycgip/TJGE24R.pgm?user=JOVO	
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController/many more - transportdisp_mainorder.do, many more... ", description=" --> TRANSPORT_DISP_GENERAL_OPPDRAGSTYPE_URL")
	static public String TRANSPORT_DISP_GENERAL_OPPDRAGSTYPE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQOTY.pgm";
	//http://gw.systema.no/sycgip/TJINQOTY.pgm?user=JOVO&opdtyp=&beskr=&getval=&fullinfo=J
	//Note: getval=J:perfect match, fullinfo=J:all fields are returned
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder.do ", description=" --> TRANSPORT_DISP_GENERAL_FRANKATUR_INCOTERMS_URL")
	static public String TRANSPORT_DISP_GENERAL_FRANKATUR_INCOTERMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQFRA.pgm";
	//http://gw.systema.no/sycgip/TJINQFRA.pgm?user=JOVO&franka=&beskr=&getval=&fullinfo=J
	
	//static public String TRANSPORT_DISP_GENERAL_TRANSPORTTYPES_JAVABASED_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTS4R.do";
	//http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTS4R.do?user=OSCAR
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_trackandtrace_edit.do ", description=" --> TRANSPORT_DISP_GENERAL_TRACK_AND_TRACE_URL")
	static public String TRANSPORT_DISP_GENERAL_TRACK_AND_TRACE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE002.pgm";
	
	//http://gw.systema.no/sycgip/TJGE002.pgm?user=JOVO&avd=75&opd=19
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_childwindow_trackandtrace_edit.do ", description=" --> TRANSPORT_DISP_GENERAL_TRACK_AND_TRACE_EDIT_URL")
	static public String TRANSPORT_DISP_GENERAL_TRACK_AND_TRACE_EDIT_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE002A.pgm";
	//http://gw.systema.no/sycgip/TJGE002A.pgm?user=JOVO&ttavd=75&ttopd=19...etc
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderListController - transportdisp_workflow_childwindow_trackandtrace_edit.do ", description=" --> TRANSPORT_DISP_GENERAL_AVD_GROUPS_URL")
	static public String TRANSPORT_DISP_GENERAL_AVD_GROUPS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQAGR.pgm";
	//http://gw.systema.no/sycgip/TJINQAGR.pgm?user=JOVO
	
	
	//--------------------------------------------
	//[1.2] FETCH Specific TRIP from WORKFLOW LIST
	//--------------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowControllerChildWindow - transportdisp_workflow_getTrip_cw.do ", description=" --> TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_URL")
	static public String TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR02.pgm";
	//http://gw.systema.no/sycgip/TJETUR02.pgm?user=JOVO&tuavd=75&tupro=75000002 (via Ajax)
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - getTripHeadingArchiveDocs_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_ARCHIVED_DOCS_URL")
	static public String TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_ARCHIVED_DOCS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR02DO.pgm";
	//http://gw.systema.no/sycgip/TJETUR02DO.pgm?user=JOVO&wstur=75000015 (via Ajax)
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowFerjeoverfarterController - transportdisp_workflow_ferjeoverfarter.do ", description=" --> TRANSPORT_DISP_BASE_FETCH_SPECIFIC_SHIPPING_TRIP_LIST_URL")
	static public String TRANSPORT_DISP_BASE_FETCH_SPECIFIC_SHIPPING_TRIP_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR11L.pgm";
	//http://gw.systema.no/sycgip/TJETUR11L.pgm?user=SYSTEMA&feavd=33&fetur=33010116 (via Ajax)
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowFerjeoverfarterController - transportdisp_workflow_ferjeoverfarter_departures_search.do ", description=" --> TRANSPORT_DISP_BASE_FETCH_SPECIFIC_SHIPPING_DEPARTURES_LIST_URL")
	static public String TRANSPORT_DISP_BASE_FETCH_SPECIFIC_SHIPPING_DEPARTURES_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR11A.pgm";
	//http://gw.systema.no/sycgip/TJETUR11A.pgm?user=JOVO
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowFerjeoverfarterController - transportdisp_workflow_ferjeoverfarter_edit.do ", description=" --> TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_SHIPPING_TRIPS_URL")
	static public String TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_SHIPPING_TRIPS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR11U.pgm";
	//gw.systema.no/sycgip/TJETUR11U.pgm?user=JOVO&feavd=1&fetur=501906&fefrom=TRELL&feto=SASSN&mode=A&wsfajn=J&fedat2=20181220&fetime=0830&feleng=12&fefirm=3150&fedate=19970101&fecurr=SEK&fepri1=1805&fepri2=2105	
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowFerjeoverfarterController - transportdisp_workflow_ferjeoverfarter_edit.do ", description=" --> TRANSPORT_DISP_BASE_ClOSE_SPECIFIC_TRIP_URL")
	static public String TRANSPORT_DISP_BASE_ClOSE_SPECIFIC_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR03ST.pgm";
	//Close:-->http://gw.systema.no/sycgip/TJETUR03ST.pgm?user=OSCAR&tuavd=75&tupro=75000015&tust=A 
	//Open:-->http://gw.systema.no/sycgip/TJETUR03ST.pgm?user=OSCAR&tuavd=75&tupro=75000015 
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowTripListController - transportdisp_workflow_renderGodsOrLastlist.do ", description=" --> TRANSPORT_DISP_BASE_PRINT_SPECIFIC_TRIP_GODSLISTA_URL")
	static public String TRANSPORT_DISP_BASE_PRINT_SPECIFIC_TRIP_GODSLISTA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR06GL.pgm";
	//http://gw.systema.no/sycgip/TJETUR06GL.pgm?user=OSCAR&wstur=75000015 
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowTripListController - transportdisp_workflow_renderGodsOrLastlist.do ", description=" --> TRANSPORT_DISP_BASE_PRINT_SPECIFIC_TRIP_LASTLISTA_URL")
	static public String TRANSPORT_DISP_BASE_PRINT_SPECIFIC_TRIP_LASTLISTA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR06LL.pgm";	
	//http://gw.systema.no/sycgip/TJETUR06LL.pgm?user=OSCAR&wstur=75000015 
	
	//----------------------------------------------------------
	//[2] UPDATE/CREATE/DELETE Specific TRIP from WORKFLOW LIST
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	//----------------------------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowHeaderController - transportdisp_workflow_edit.do ", description=" --> TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_TRIP_URL")
	static public String TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR03.pgm";
	//http://gw.systema.no/sycgip/TJETUR03.pgm?user=JOVO&tuavd=1&tupro=1&mode=U + alla parametrar &TU***=xxx 
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowHeaderController - transportdisp_workflow_edit.do ", description=" --> TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_TRIP_MESSAGE_NOTE_URL")
	static public String TRANSPORT_DISP_BASE_UPDATE_SPECIFIC_TRIP_MESSAGE_NOTE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR10.pgm";
	//http://gw.systema.no/sycgip/TJETUR10.pgm?user=JOVO&wsavd=75&wstur=75000015&frttxt01=Tarzan i djungelskogen&frttxt02=Jane i bastkjol&frttxt03=aiaiaiaia ! 
	//--->frttxt01 - frttxt12  alla max 70 långa 
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowHeaderController - transportdisp_workflow_edit.do ", description=" --> TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_MESSAGE_NOTE_URL")
	static public String TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_MESSAGE_NOTE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR08.pgm";
	//http://gw.systema.no/sycgip/TJETUR08.pgm?user=JOVO&wsavd=75&wstur=75000002

	//------------------------------------------------------
	//[3] FETCH LISTs of ORDERS-ON-TRIP and ORDERS-NOT-TRIP
	//------------------------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderListController - transportdisp_mainorderlist.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_LIST_ORDERS_ON_TRIP_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_LIST_ORDERS_ON_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR05LT.pgm";
	//http://gw.systema.no/sycgip/TJETUR05LT.pgm?user=JOVO&wstur=75000001
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderListController - transportdisp_mainorderlist.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_LIST_ORDERS_NOT_ON_TRIP_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_LIST_ORDERS_NOT_ON_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR05LL.pgm";
	//http://gw.systema.no/sycgip/TJETUR05LL.pgm?user=JOVO&wssavd=75
	//Note: Det finns fler parametrar: wssdf=Fra sted; wssdt=Til sted 
	//wssavd= ALL   IN     OUT        IMP           EXP            DOM     
	//d.v.s antingen en konkret avdelning med siffra, eller bokstævskoder
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderListHistoryController - transportdisp_mainorderlist_history.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_LIST_ORDERS_NOT_ON_TRIP_HISTORY_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_LIST_ORDERS_NOT_ON_TRIP_HISTORY_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR05LH.pgm";
	//http://gw.systema.no/sycgip/TJETUR05LL.pgm?user=JOVO&wssavd=75
	//Note: Det finns fler parametrar: wssdf=Fra sted; wssdt=Til sted 
	//wssavd= ALL   IN     OUT        IMP           EXP            DOM     
	//d.v.s antingen en konkret avdelning med siffra, eller bokstævskoder
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderListController - transportdisp_mainorderlist_renderFraktbrev.do ", description=" --> TRANSPORT_DISP_BASE_PRINT_SPECIFIC_ORDER_FRAKTBREV_URL")
	static public String TRANSPORT_DISP_BASE_PRINT_SPECIFIC_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPDFBR.pgm";
	//renders PDF...
	
	
	//-----------------------------------------
	//[4] ADD or REMOVE ORDER(S) from a Trip
	//-----------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderListController - transportdisp_mainorderlist_add_remove_order.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_ADD_DELETE_ORDER_FROM_TRIP_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_ADD_DELETE_ORDER_FROM_TRIP_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR05AD.pgm";
	//http://gw.systema.no/sycgip/TJETUR05AD.pgm?user=OSCAR?wmode=A?wstur=75000001?wsavd=75?wsopd=1 
	//http://gw.systema.no/sycgip/TJETUR05AD.pgm?user=OSCAR?wmode=D?wstur=75000001?wsavd=75?wsopd=1 
	
	//-----------------------------------------
	//[5] UPDATE position in uppdraget
	//-----------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - updatePositionsMainOrdersLists_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_POSITION_ON_UPPDRAGET_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_POSITION_ON_UPPDRAGET_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR05PO.pgm";
	//ADD----->http://gw.systema.no/sycgip/TJETUR05PO.pgm?user=OSCAR?wsavd=75?wsopd=6?wspos=3 
	//REMOVE-->http://gw.systema.no/sycgip/TJETUR05PO.pgm?user=OSCAR?wsavd=75?wsopd=6?
		
	
	//----------------------------------------------------------
	//[2] FETCH/UPDATE/CREATE/DELETE Specific ORDER from ORDER LIST
	// It might or might not have a trip cross reference
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	//----------------------------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD5L.pgm";
	//http://gw.systema.no/sycgip/TJEOPD5L.pgm?user=JOVO&heavd=1&heopd=52904
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_OPPDTYPE_TIME_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_OPPDTYPE_TIME_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD5P.pgm";
	//http://gw.systema.no/sycgip/TJEOPD5P.pgm?user=JOVO&avd=75
	
	//[2.1] Message Note management (Consignee, Carrier, Internal)
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_OPPDTYPE_TIME_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_MESSAGE_NOTE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE11R.pgm";
	//http://gw.systema.no/sycgip/TJGE11R.pgm?user=OSCAR&avd=75&opd=11&kod=R (R=Receiver, G=Carrier, b=Blank)
	
	//[2.2] Fraktbrev section (order lines)
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_LIST_MAIN_ORDER_FRAKTBREV_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_LIST_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE21R.pgm"; 	
	//http://gw.systema.no/sycgip/TJGE21R.pgm?user=JOVO&avd=75&opd=11&fbn=1
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - fetchFraktbrevLine_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_LINE_MAIN_ORDER_FRAKTBREV_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE22R.pgm"; 	
	//http://gw.systema.no/sycgip/TJGE22R.pgm?user=JOVO&avd=75&opd=11&fbn=1&lin=1
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder_update.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23R.pgm";
	//http://gw.systema.no/sycgip/TJGE23R.pgm?user=JOVO&avd=75&opd=11&fbn=1&lin=2&mode=A&fvant=11&fvvkt=15
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - validateCurrentOrderDetailLine_TransportDispOrig.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23RV.pgm";
	//http://gw.systema.no/sycgip/TJGE23RV.pgm?user=JOVO&avd=75&opd=19&fmmrk1=&fvant=1&fvpakn=&fvvt=TEST&fvvkt=&fvvol=&fvlm=&fvlm2=&fvlen=&fvbrd=&fvhoy=&ffunnr=1234&ffemb=&ffantk=1&ffante=1&ffenh=KGM
	
	@UrlDataStoreAnnotationForField (name="@TransportDispAjaxHandlerController - validateCurrentOrderDetailLine_TransportDisp.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_2_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_2_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23RV2.pgm";
	//http://gw.systema.no/sycgip/TJGE23RV2.pgm?user=JOVO&avd=75&opdtyp=OX&fmmrk1=&fvant=2&fvpakn=&fvvt=TEST&fvvkt=1000&fvlen=220&fvbrd=220&fvhoy=120&fvvol=&fvlm=&fvlm2=&ffunr=1234&ffemb=&ffantk=1&ffante=1&ffenh=KGM
	
	//[2.2.1] Uploaded documents on order...(fetch)
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_UPLOADED_DOCS_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_UPLOADED_DOCS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE12R.pgm";
	//http://gw.systema.no/sycgip/TJGE12R.pgm?User=JOVO&AVD=75&OPD=68
	
	//[2.3] Order header save and validation (including all lines)
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder_update.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD6L.pgm";
	//http://gw.systema.no/sycgip/TJEOPD6L.pgm?user=JOVO&avd=1&opd=999999&mode=U&hest=A&heur=H&hedtop=20150513...........................................o.s.v
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderListController - transportdisp_mainorderlist_permanently_delete_order.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_PERMANENTLY_DELETE_MAIN_ORDER_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_PERMANENTLY_DELETE_MAIN_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD6LD.pgm";
	//http://gw.systema.no/sycgip/TJEOPD6LD.pgm?user=JOVO&avd=75&opd=72
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderListController - transportdisp_mainorderlist_copy_order.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_COPY_ORDER_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_COPY_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD6LC.pgm";
	//http://gw.systema.no/sycgip/TJEOPD6LC.pgm?user=JOVO&avd=75&opd=70&newavd=75&newhesg=JOV
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderListController - transportdisp_mainorderlist_move_order.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_MOVE_ORDER_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_MOVE_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD6LM.pgm";
	//http://gw.systema.no/sycgip/TJEOPD6LM.pgm?user=JOVO&avd=75&opd=70&newavd=1
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowTripListController - transportdisp_workflow_copyRoundTrip.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_COPY_TUR_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_COPY_TUR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR02.pgm";
	//http://gw.systema.no/sycgip/TJETUR02.pgm?user=JOVO&TUAVD=75&TUPRO=75000019&kopiRund=J
	
	//NOTE: nästan alla fält i HEADF + [WSKCONT WSKTLF WSKMAIL WSSCONT WSSTLF WSSMAIL]
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder_update.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_MAIN_ORDER_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_MAIN_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPD6LV.pgm";
	//http://gw.systema.no/sycgip/TJEOPD6LV.pgm?user=JOVO&AVD=80&OPD=1384&HEOT=OX&HENT=1234567&HELM=27,55
	
	//----------------------------------------------------------------
	//[2] FETCH/UPDATE/CREATE/DELETE Specific Invoice - Invoice lines
	// It might or might not have a trip cross reference
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	//----------------------------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderInvoiceController - transportdisp_mainorder_invoice.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_INVOICE_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE25R.pgm";
	//Lista -->http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=type=A
	//Linje -->http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=55&type=A
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderInvoiceController - transportdisp_mainorder_invoice_edit.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_INVOICE_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE26R.pgm";
	//Update -->http://gw.systema.no/sycgip/TJGE26R.pgm?user=JOVO&avd=80&opd=201523&lin=55&mode=(A)(U)(D)
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderInvoiceController - transportdisp_mainorder_invoice.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_STATUS_READYMARK_MAIN_ORDER_INVOICE_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_STATUS_READYMARK_MAIN_ORDER_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE26RF.pgm";
	//http://gw.systema.no/sycgip/tjge26rf.pgm?user=JOVO&avd=75&opd=19&Mode=
	//Mode=G = Get  =  kun sjekk av status 
	//http://gw.systema.no/sycgip/tjge26rf.pgm?user=JOVO&avd=75&opd=19&Mode=G
	
	//----------------------------------------------------------------
	//[2] FETCH/UPDATE/CREATE/DELETE Specific Budget - Budget lines
	// It might or might not have a trip cross reference
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	//----------------------------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowBudgetController - transportdisp_workflow_budget.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_BUDGET_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_BUDGET_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE27RG.pgm";
	//http://gw.systema.no/sycgip/TJGE27RG.pgm?user=JOVO&avd=75&opd=&tur=75000038&bnr=1319143&type=A
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowBudgetController - transportdisp_workflow_budget.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_BUDGET_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_BUDGET_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE27RU.pgm";
	//http://gw.systema.no/sycgip/tjge27ru.pgm?user=JOVO&bnr=1318923&mode=U&bupCc=20&bupAr=15&bupMn
	
	
	//----------------------------------------------------------------
	//[3] FETCH/UPDATE/CREATE/DELETE Frie søkeveier
	// It might or might not have a trip cross reference
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	// mode=I (inquiry)
	//----------------------------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_FRISOKVEI_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_FRISOKVEI_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE28R.pgm";
	//http://gw.systema.no/sycgip/TJGE28R.pgm?user=JOVO&avd=75&opd=155651
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowFrisokveiController - transportdisp_workflow_frisokvei_edit.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_FRISOKVEI_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_FRISOKVEI_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE29R.pgm";
	//http://gw.systema.no/sycgip/tjge29R.pgm?user=JOVO&avd=75&opd=155651&mode=A&fskode=IFB&fssok=test&fsdokk=...	
	
	@UrlDataStoreAnnotationForField (name="@TransportDispWorkflowFrisokveiController - transportdisp_workflow_frisokvei_edit.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_FRISOKVEI_VALID_LIST_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_FRISOKVEI_VALID_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE28RG.pgm";
	//http://gw.systema.no/sycgip/tjge28RG.pgm?user=JOVO&Avd=75&opd=108&kode=AVD
	
	//----------------------------------------------------------------------------------------------------------
	// Post Update service call (in order to use some last-resource pgm)
	// Usually used when we are required to do something in an update situation BEFORE the FETCH event happens
	// The return value(s) could be anything. A string, a field description, json-fields. Then a specific implementation
	// will be required ...
	//----------------------------------------------------------------------------------------------------------
	@UrlDataStoreAnnotationForField (name="@TransportDispMainOrderController - transportdisp_mainorder_update.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_POST_UPDATE_Z")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_POST_UPDATE_Z = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJEOPDZ.pgm";
	
	//PRINT routine FraktBrev
	//when avd & opd !=null and tur==null, then the order is printed
	//when tur !=null and avd&opd == null, then the trip prints ALL children orders...
	//http://gw.systema.no/sycgip/TJFA12d.pgm?user=JOVO&avd=80&opd=0201571&tur=
	@UrlDataStoreAnnotationForField (name="@TransportDispPrintController - transpdisp_mainorder_printout.do ", description=" --> TRANSPORT_DISP_BASE_PRINT_OUT_FRAKTBREV")
	static public String TRANSPORT_DISP_BASE_PRINT_OUT_FRAKTBREV = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJFA12d.pgm";
	
	//----------------------------------------------------------------
	//PRINT FETCH/UPDATE/CREATE/DELETE Fellesutskrift general
	//----------------------------------------------------------------
	//http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=OSCAR&wsavd=1&wssg=OT ... etc
	@UrlDataStoreAnnotationForField (name="@TransportDispPrintController - transportdisp_mainorderlist_fellesutskrift.do ", description=" --> TRANSPORT_DISP_EXECUTE_FELLESUTSKRIFT_URL")
	static public String TRANSPORT_DISP_EXECUTE_FELLESUTSKRIFT_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSYFAPR1.pgm";
	
	
	//Dangerous goods
	@UrlDataStoreAnnotationForField (name="@TransportDispMainDangerousGoodsController - transportdisp_workflow_dangerousgoods.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_DANGEROUSGOODS_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_DANGEROUSGOODS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE21RF.pgm";
	//http://gw.systema.no/sycgip/tjge21RF.pgm?user=JOVO&avd=75&opd=113&lin=2
	
	@UrlDataStoreAnnotationForField (name="@TransportDispMainDangerousGoodsController - transportdisp_mainorder_update.do ", description=" --> TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_DANGEROUSGOODS_URL")
	static public String TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_DANGEROUSGOODS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23RF.pgm";
	//http://gw.systema.no/sycgip/tjge23RF.pgm?user=JOVO&mode=U&avd=75&opd=113&lin=2&lin2=4&ffunnr=1049&ffembg=&ffindx=&ffklas=1A&ffsedd=2.1&fftres=(%20B/D%20)&fffakt=3&ffantk=1&ffante=15,000&ffenh=KG&ffpoen=45

	//mode=G (get) or mode=U (update)
	static public String TRANSPORT_DISP_BASE_ORDER_MERKMOTT_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJMERKGU.pgm";
	//https://gw.systema.no:65209/sycgip/tjmerkgu.pgm?user=JOVO&avd=75&opd=131&mode=G&wssgm=&wsDTMO=&wsKLMO=&wsTLE=&wsTLL=&wsTLKU=&wsDTG=&wsGNN=

}
