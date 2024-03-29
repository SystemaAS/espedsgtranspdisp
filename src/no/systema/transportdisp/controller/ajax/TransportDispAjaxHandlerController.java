/**
 * 
 */
package no.systema.transportdisp.controller.ajax;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import no.systema.transportdisp.model.dto.PrintFormObjectDto;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderContainer;


import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripShipContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripShipRecord;

import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispCustomerContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispCustomerRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispSupplierContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispSupplierRecord;


import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilNrContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilNrRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispDriverContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispDriverRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispFileUploadValidationContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispTranspCarrierContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispTranspCarrierRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispCustomerDeliveryAddressContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispCustomerDeliveryAddressRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.frisokvei.JsonTransportDispWorkflowSpecificOrderFrisokveiContainer;

import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispSendSmsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispPackingCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispPackingCodesRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispTollstedCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispTollstedCodesRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer;
import no.systema.transportdisp.model.workflow.order.OrderLineValidationObject;

import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;

import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowBudgetService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;

import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.RpgReturnResponseHandler;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.main.util.StringManager;

import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;

/**
 * This Ajax handler is the class handling ajax request in Tranport Disp. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Apr 1, 2015
 * 
 */

@RestController

public class TransportDispAjaxHandlerController {
	private static final Logger logger = LoggerFactory.getLogger(TransportDispAjaxHandlerController.class.getName());
	//private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr;
	private StringManager strMgr = new StringManager();
	
	final String  TYPE_GODSL= "G";
	final String  TYPE_LASTL= "L";
	final String  TYPE_TURKONVOLUTT= "TK";
	
	/**
	 * Gets the Specific Trip-heading
	 * 
	 * @param applicationUser
	 * @param avdNr
	 * @param tripNr
	 * @return
	 */
	@RequestMapping(value = "getTripHeading_TransportDisp.do", method = RequestMethod.GET)
     public @ResponseBody List<JsonTransportDispWorkflowSpecificTripRecord> getTripHeading
	  						(@RequestParam String applicationUser, @RequestParam String avdNr, 
	  						 @RequestParam String tripNr) {
		logger.warn("Inside: getTripHeading");
		this.controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr(this.urlCgiProxyService, this.transportDispWorkflowSpecificTripService);
		List<JsonTransportDispWorkflowSpecificTripRecord> result = new ArrayList<JsonTransportDispWorkflowSpecificTripRecord>();
		
	 	try{
	 		JsonTransportDispWorkflowSpecificTripContainer container = this.controllerAjaxCommonFunctionsMgr.fetchTripHeading(applicationUser, avdNr, tripNr);
			if(container!=null){
				Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> messageNote = new ArrayList<JsonTransportDispWorkflowSpecificTripMessageNoteRecord>();
				Collection<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> archiveDocsList = new ArrayList<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord>();
				Collection<JsonTransportDispWorkflowSpecificTripShipRecord> shippingTripList = new ArrayList<JsonTransportDispWorkflowSpecificTripShipRecord>();
				for(JsonTransportDispWorkflowSpecificTripRecord  record : container.getGetonetrip()){
					logger.warn("####TUPRO-field:" + record.getTupro());
					//Now fetch the Message Note and fill the parent record with it
					messageNote = this.controllerAjaxCommonFunctionsMgr.fetchMessageNote(applicationUser, avdNr, tripNr);
					record.setFreetextlist(messageNote);
					//Now fetch the Archived Documents and fill the parent record with it
					archiveDocsList = this.controllerAjaxCommonFunctionsMgr.fetchTripHeadingArchiveDocs(applicationUser, tripNr);
					record.setGetdoctrip(archiveDocsList);
					//Now fetch the Shipping list and fill the parent record with it
					shippingTripList = this.controllerAjaxCommonFunctionsMgr.fetchTripHeadingShippingTripList(applicationUser, avdNr, tripNr);
					record.setShippingTripList(shippingTripList);
					
					//set final complete record
					result.add(record);
				 }
			}
	 	}catch(Exception e){
	 		e.printStackTrace();
	 	}
	 	
	 	return result;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param tripNr
	 * @return
	 */
	
	@RequestMapping(value = "getTripHeadingArchiveDocs_TransportDisp.do", method = RequestMethod.GET)
    public @ResponseBody List<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> getTripHeadingArchiveDocs
	  						(@RequestParam String applicationUser, @RequestParam String tripNr) {
		 logger.info("Inside: getTripHeadingArchiveDocs");
		 List<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord> result = new ArrayList<JsonTransportDispWorkflowSpecificTripArchivedDocsRecord>();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_FETCH_SPECIFIC_TRIP_ARCHIVED_DOCS_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&wstur=" + tripNr;
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
			 	try{
			 		JsonTransportDispWorkflowSpecificTripArchivedDocsContainer container = this.transportDispWorkflowSpecificTripService.getArchivedDocsContainer(jsonPayload);
					if(container!=null){
						 for(JsonTransportDispWorkflowSpecificTripArchivedDocsRecord record : container.getGetdoctrip()){
							 logger.info("####Link:" + record.getDoclnk());
							 result.add(record);
						 }
					}
			 	}catch(Exception e){
			 		e.printStackTrace();
			 	}
			 }
		 return result;
	}
	
	/**
	 * Creates a new line in the specific Order
	 * 
	 * @param applicationUser
	 * @param requestString
	 * @return
	 */
	@RequestMapping(value = "addNewOrderDetailLine_TransportDisp.do", method = RequestMethod.GET)
    public @ResponseBody Set<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> addNewOrderDetailLine
	  						(@RequestParam String applicationUser, @RequestParam String requestString){
		 logger.info("Inside: addNewOrderDetailLine");
		 RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		 
		 Set<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> result = new HashSet<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>();
		 //logger.info(requestString);
		 if(requestString!=null && !"".equals(requestString)){
		 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL;
			 //add URL-parameters
			 String urlRequestParams = requestString;
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 JsonTransportDispWorkflowSpecificOrderFraktbrevRecord placeHolderObj = new JsonTransportDispWorkflowSpecificOrderFraktbrevRecord();	
			 //Debug --> 
			 logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
			 //we must evaluate a return RPG code in order to know if the Update was OK or not
			 if(rpgReturnPayload!=null){
				 rpgReturnResponseHandler.setErrorMessage(null);
				 rpgReturnResponseHandler.evaluateRpgResponseOnEditSpecificOrder(rpgReturnPayload);
				 if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
					 rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
					 //TODO -->this.setFatalErrorAddRemoveOrders(model, rpgReturnResponseHandler, recordToValidate);
					 logger.info(rpgReturnResponseHandler.getErrorMessage());
					 placeHolderObj.setFvlinr("-1");
				 }else{
					 placeHolderObj.setFvlinr("1");
				 }
			 }
			 result.add(placeHolderObj);
		 }
		 return result;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param requestString
	 * @return
	 */
	@RequestMapping(value = "fetchFraktbrevLine_TransportDisp.do", method = RequestMethod.GET)
    public @ResponseBody Set<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> fetchFraktbrevLine
	  						(@RequestParam String applicationUser, @RequestParam String requestString){
		 logger.info("Inside: fetchFraktbrevLine");
		 RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		 
		 Set<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord> result = new HashSet<JsonTransportDispWorkflowSpecificOrderFraktbrevRecord>();
		 //logger.info(requestString);
		 if(requestString!=null && !"".equals(requestString)){
		 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_LINE_MAIN_ORDER_FRAKTBREV_URL;
			 //add URL-parameters
			 String urlRequestParams = "user=" + applicationUser + requestString;
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 JsonTransportDispWorkflowSpecificOrderFraktbrevRecord placeHolderObj = new JsonTransportDispWorkflowSpecificOrderFraktbrevRecord();
			 JsonTransportDispWorkflowSpecificOrderFraktbrevContainer container = this.transportDispWorkflowSpecificOrderService.getFraktbrevContainer(jsonPayload);	
			 //Debug --> 
			 logger.info(jsonPayload);
			 //we must evaluate a return RPG code in order to know if the Update was OK or not
			 if(container!=null){
				 for(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord rec : container.getAwblineGet()){
					 placeHolderObj = rec;
				 }
				 logger.info("Linjenr: " + placeHolderObj.getFvlinr());
			 }
			 result.add(placeHolderObj);
		 }
		 return result;
	}
	
	
	/**
	 * Creates a new line in the specific Order
	 * 
	 * @param applicationUser
	 * @param requestString
	 * @return
	 */
	@RequestMapping(value = "validateCurrentOrderDetailLine_TransportDispOrig.do", method = RequestMethod.GET)
    public @ResponseBody Set<OrderLineValidationObject> validateCurrentOrderDetailLineOrig
	  						(@RequestParam String applicationUser, @RequestParam String requestString, @RequestParam String lineNr ){
		 logger.info("Inside: validateCurrentOrderDetailLine");
		 RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		 
		 Set<OrderLineValidationObject> result = new HashSet<OrderLineValidationObject>();
		 //logger.info(requestString);
		 if(requestString!=null && !"".equals(requestString)){
		 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_URL;
			 //add URL-parameters
			 String urlRequestParams = requestString;
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 //JsonTransportDispWorkflowSpecificOrderFraktbrevRecord placeHolderObj = new JsonTransportDispWorkflowSpecificOrderFraktbrevRecord();	
			 OrderLineValidationObject orderLineValidationObject = new OrderLineValidationObject();
			 orderLineValidationObject.setLinenr(lineNr);
			 //Debug --> 
			 logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
			 //we must evaluate a return RPG code in order to know if the Update was OK or not
			 if(rpgReturnPayload!=null){
				 rpgReturnResponseHandler.setErrorMessage(null);	
				 rpgReturnResponseHandler.evaluateRpgResponseOnValidateSpecificOrderLine(rpgReturnPayload);
				 if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
					 rpgReturnResponseHandler.setErrorMessage(rpgReturnResponseHandler.getErrorMessage());
					 //TODO -->this.setFatalErrorAddRemoveOrders(model, rpgReturnResponseHandler, recordToValidate);
					 logger.info(rpgReturnResponseHandler.getErrorMessage());
					 orderLineValidationObject.setErrMsg(rpgReturnResponseHandler.getErrorMessage());
				 }
			 }
			 result.add(orderLineValidationObject);
		 }
		 return result;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param requestString
	 * @param lineNr
	 * @return
	 */
	@RequestMapping(value = "validateCurrentOrderDetailLine_TransportDisp.do", method = RequestMethod.GET)
    public @ResponseBody Set<OrderLineValidationObject> validateCurrentOrderDetailLine
	  						(@RequestParam String applicationUser, @RequestParam String requestString, @RequestParam String lineNr ){
		 logger.info("Inside: validateCurrentOrderDetailLine");
		 Set<OrderLineValidationObject> result = new HashSet<OrderLineValidationObject>();
		 OrderLineValidationObject orderLineValidationObject = new OrderLineValidationObject();
		 //logger.info(requestString);
		 if(requestString!=null && !"".equals(requestString)){
		 	 String BASE_URL = null;
		 	 BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_2_URL;
		 	 
		 	 
			 //add URL-parameters
			 String urlRequestParams = requestString;
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 if(jsonPayload!=null){
				 JsonTransportDispWorkflowSpecificOrderFraktbrevContainer container = this.transportDispWorkflowSpecificOrderService.getFraktbrevContainer(jsonPayload);
				 if(container!=null){
					 for(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord record : container.getAwblineValidate()){
						 orderLineValidationObject.setLinenr(lineNr);
						 orderLineValidationObject.setFvlm(record.getFvlm());
						 orderLineValidationObject.setFvlm2(record.getFvlm2());
						 logger.info(orderLineValidationObject.getFvlm());
						 logger.info(orderLineValidationObject.getFvlm2());
						 
						 
					 }	
					 logger.info("errMsg:" + container.getErrMsg());
					 //hand over
					 orderLineValidationObject.setErrMsg(container.getErrMsg());
					 orderLineValidationObject.setInfoMsg(container.getInfoMsg());
					 
				 }
			 }
			 result.add(orderLineValidationObject);
		 }
		 return result;
	}
	
	
	@RequestMapping(value = "updateMainTurListCloseOpenTrip_TransportDisp.do", method = RequestMethod.GET)
    public @ResponseBody Set<JsonTransportDispWorkflowSpecificTripRecord> updateMainTurList
	  						(@RequestParam String applicationUser, @RequestParam String requestString){
		 logger.info("Inside: updateMainTurListCloseOpenTrip_TransportDisp");
		 RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		 
		 Set<JsonTransportDispWorkflowSpecificTripRecord> result = new HashSet<JsonTransportDispWorkflowSpecificTripRecord>();
		 //logger.info(requestString);
		 if(requestString!=null && !"".equals(requestString)){
			 String [] requestRecord = requestString.split("@");
			 List<String> requestParams = Arrays.asList(requestRecord);
			 for (String record : requestParams){
				 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_ClOSE_SPECIFIC_TRIP_URL;
				 //add URL-parameters
				 StringBuffer urlRequestParams = new StringBuffer();
				 urlRequestParams.append(record);
				 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				 logger.info("URL: " + BASE_URL);
				 logger.info("URL PARAMS: " + urlRequestParams);
				 
				 String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				 
				 //Debug --> 
				 logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
				 //we must evaluate a return RPG code in order to know if the Update was OK or not
				 if(rpgReturnPayload!=null){
					 rpgReturnResponseHandler.evaluateRpgResponseOnTripUpdate(rpgReturnPayload);
					 if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
						 rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
						 logger.info(rpgReturnResponseHandler.getErrorMessage());
					 }
				 }
				 
				 //Now break the record in order to fill the return object for further handling on GUI (jQuery)
				 String[] tmp = record.split("&");
				 List<String> fields = Arrays.asList(tmp);
				 JsonTransportDispWorkflowSpecificTripRecord trip = new JsonTransportDispWorkflowSpecificTripRecord();
				 
				 String redirectAvd = "";
				 for (String field: fields){
					 if(field.contains("tuavd")){
						 //logger.info("FIELD:" + field);	
						 redirectAvd = field.replace("tuavd=", "");
						 //logger.info("REDIRECT AVD:" + redirectAvd);
						 trip.setTuavd(redirectAvd);
					 }
					 
				 }
				//error handling
				 if(strMgr.isNotNull(rpgReturnResponseHandler.getErrorMessage()) ){
					 trip.setErrMsg(rpgReturnResponseHandler.getErrorMessage());
					 //we must break the loop otherwise it creates confusion for the ajax call on jQuery ...
					 result = new HashSet<JsonTransportDispWorkflowSpecificTripRecord>();
					 result.add(trip);
					 break;
				 }else{
					 result.add(trip);
				 }
			 }	 
		 }
		 return result;
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param rawString
	 * @return
	 */
	@RequestMapping(value = "updateMainOrdersLists_TransportDisp.do", method = RequestMethod.GET)
    public @ResponseBody Set<JsonTransportDispWorkflowSpecificTripRecord> updateMainOrdersLists
	  						(@RequestParam String applicationUser, @RequestParam String requestString){
		 logger.info("Inside: updateMainOrdersLists");
		 RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		 
		 Set<JsonTransportDispWorkflowSpecificTripRecord> result = new HashSet<JsonTransportDispWorkflowSpecificTripRecord>();
		 logger.info(requestString);
		 if(requestString!=null && !"".equals(requestString)){
			 String [] requestRecord = requestString.split("@");
			 List<String> requestParams = Arrays.asList(requestRecord);
			 for (String record : requestParams){
				 
				 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_ADD_DELETE_ORDER_FROM_TRIP_URL;
				 //add URL-parameters
				 StringBuffer urlRequestParams = new StringBuffer();
				 //urlRequestParams.append("user=" + applicationUser);
				 //record of type: user=OSCAR&wmode=D&wstur=75000001&wsavd=75&wsopd=6
				 urlRequestParams.append(record);
				 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				 logger.info("URL: " + BASE_URL);
				 logger.info("URL PARAMS: " + urlRequestParams);
				 String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				 
				 //Debug --> 
				 logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
				 //we must evaluate a return RPG code in order to know if the Update was OK or not
				 if(rpgReturnPayload!=null){
					 rpgReturnResponseHandler.evaluateRpgResponseOnAddRemoveOrder(rpgReturnPayload);
					 if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
						 //TODO -->this.setFatalErrorAddRemoveOrders(model, rpgReturnResponseHandler, recordToValidate);
						 String idPrefix = "AVD/OPD:" + rpgReturnResponseHandler.getHeavd() + "/" + rpgReturnResponseHandler.getHeopd() + " - " ;
						 rpgReturnResponseHandler.setErrorMessage(idPrefix + rpgReturnResponseHandler.getErrorMessage());
						 //logger.info(rpgReturnResponseHandler.getErrorMessage());
						 
					 }
				 }
				 //Now break the record in order to fill the return object for further handling on GUI (jQuery)
				 String[] tmp = record.split("&");
				 List<String> fields = Arrays.asList(tmp);
				 JsonTransportDispWorkflowSpecificTripRecord trip = new JsonTransportDispWorkflowSpecificTripRecord();
				 
				 String wsopd = "";
				 for (String field: fields){
					 if(field.contains("wsavd")){
						 trip.setTuavd(field.replace("wsavd=", ""));
					 }else if (field.contains("wstur")){
						 trip.setTupro(field.replace("wstur=", ""));					 
					 }else if (field.contains("wsopd")){
						 wsopd = field.replace("wsopd=", "");					 
					 }
					 
				 }
				//error handling
				 if(strMgr.isNotNull(rpgReturnResponseHandler.getErrorMessage()) ){
					 trip.setErrMsg(rpgReturnResponseHandler.getErrorMessage());
					 //we must break the loop otherwise it creates confusion for the ajax call on jQuery ...
					 result = new HashSet<JsonTransportDispWorkflowSpecificTripRecord>();
					 result.add(trip);
					 break;
				 }else{
					 result.add(trip);
				 }
			 }	 
		 }
		 return result;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param requestString
	 * @return
	 */
	@RequestMapping(value = "updatePositionsMainOrdersLists_TransportDisp.do", method = RequestMethod.GET)
    public @ResponseBody Set<JsonTransportDispWorkflowSpecificTripRecord> updatePositionsMainOrdersLists
	  						(@RequestParam String applicationUser, @RequestParam String requestString){
		 logger.info("Inside: updatePositionsMainOrdersLists");
		 RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		 
		 Set<JsonTransportDispWorkflowSpecificTripRecord> result = new HashSet();
		 logger.info(requestString);
		 String [] requestRecord = requestString.split("@");
		 List<String> requestParams = Arrays.asList(requestRecord);
		 for (String record : requestParams){
			 
			 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_POSITION_ON_UPPDRAGET_URL;
			 //add URL-parameters
			 StringBuffer urlRequestParams = new StringBuffer();
			 //urlRequestParams.append("user=" + applicationUser);
			 //record of type: user=OSCAR&wsavd=75&wsopd=6&wspos=3 
			 urlRequestParams.append(record);
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			 
			 //Debug --> 
			 logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
			 //we must evaluate a return RPG code in order to know if the Update was OK or not
			 if(rpgReturnPayload!=null){
				 rpgReturnResponseHandler.evaluateRpgResponseOnAddRemoveOrder(rpgReturnPayload);
				 if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
					 rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
					 //TODO -->this.setFatalErrorAddRemoveOrders(model, rpgReturnResponseHandler, recordToValidate);
					 logger.info(rpgReturnResponseHandler.getErrorMessage());
				 }
			 }
			 //Now break the record in order to fill the return object for further handling on GUI (jQuery)
			 String[] tmp = record.split("&");
			 List<String> fields = Arrays.asList(tmp);
			 JsonTransportDispWorkflowSpecificTripRecord trip = new JsonTransportDispWorkflowSpecificTripRecord();
			 for (String field: fields){
				 if(field.contains("wsavd")){
					 trip.setTuavd(field.replace("wsavd=", ""));
				 }
			 }
			 result.add(trip);
		 }	 
		 return result;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param customerName
	 * @param customerNumber
	 * @return
	 */
	  @RequestMapping(value = "searchCustomer_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTransportDispCustomerRecord> searchCustomer(@RequestParam String applicationUser, @RequestParam String customerName, @RequestParam String customerNumber) {
		  logger.info("Inside searchCustomer");
		  List result = new ArrayList();
		  JsonTransportDispCustomerDeliveryAddressRecord deliveryAddressRecord = getDeliveryAddress(applicationUser, customerNumber);
		  JsonTransportDispCustomerRecord targetRecord = null;
		  //check if this customer has an existent Delivery address. In that case use it!
		  if(deliveryAddressRecord!=null){
			  targetRecord = new JsonTransportDispCustomerRecord();
			  //Hand over the delivery address fields to the customer fields
			  targetRecord.setAuxnavn(deliveryAddressRecord.getVadrna());
			  targetRecord.setAdr1(deliveryAddressRecord.getVadrn1());
			  targetRecord.setAdr2(deliveryAddressRecord.getVadrn2());
			  targetRecord.setAdresse(deliveryAddressRecord.getVadrn3());
			  targetRecord.setAuxtlf(deliveryAddressRecord.getVatlf());
			  targetRecord.setAuxfax(deliveryAddressRecord.getVafax());
			  targetRecord.setAuxmail(deliveryAddressRecord.getVamail());
			  
		  }
		  //search in the customer register (deep search)
		  String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCustomer(applicationUser, customerName, customerNumber);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    		  if(jsonPayload!=null){
	    		JsonTransportDispCustomerContainer container = this.transportDispChildWindowService.getCustomerContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonTransportDispCustomerRecord  record : container.getInqcustomer()){
	    				if(record.getKundnr().equals(customerNumber)){
	    					//logger.info("CUSTOMER via AJAX: " + record.getNavn() + " NUMBER:" + record.getKundnr());
	    					if(targetRecord!=null){
	    						targetRecord.setKundnr(record.getKundnr());
	    						//Set the real customer name & land
	    						targetRecord.setNavn(record.getNavn());
	    						targetRecord.setLand(record.getLand());
	    						targetRecord.setFakknr(record.getFakknr());
	    						//DEBUG
	    						logger.info("TJINQKUND.pgm:");
	    						logger.info("navn:" + targetRecord.getNavn());
	    						logger.info("auxnavn:" + targetRecord.getAuxnavn());
	    						logger.info("fakknr:" + targetRecord.getFakknr());
	    						//logger.info(targetRecord.getAdr1());
	    						//logger.info(targetRecord.getLand());
	    						result.add(targetRecord);
	    					}else{
	    						result.add(record);
	    					}
	    					
	    				}
	    			}
	    		}
	    	  }
	    	  return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param customerNumber
	   * @return
	   */
	  private JsonTransportDispCustomerDeliveryAddressRecord getDeliveryAddress(String applicationUser, String customerNumber){
		  JsonTransportDispCustomerDeliveryAddressRecord retval = null;
		//prepare the access CGI with RPG back-end
		  String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_DELIVERY_ADDRESS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&wkundnr=" + customerNumber + "&wvadrnr=1" ;
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
    		  if(jsonPayload!=null){
    			  JsonTransportDispCustomerDeliveryAddressContainer container = this.transportDispWorkflowSpecificOrderService.getDeliveryAddressContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonTransportDispCustomerDeliveryAddressRecord  record : container.getInqdeladdr()){
	    				if(record!=null & record.getVadrnr()!=null){
	    					retval = record;
	    					logger.info("AA");
	    					logger.info("PICK_UP or DELIVERY-ADDRESS");
	    					logger.info("Vadrna:" + record.getVadrna());
	    					logger.info("vadrn1:" + record.getVadrn1());
	    					logger.info("vadrn2:" + record.getVadrn2());
	    					logger.info("vadrn3:" + record.getVadrn3());
	    					
	    				}
	    			}
	    		}
	    	  }
    		  return retval;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param id
	   * @return
	   */
	  @RequestMapping(value = "searchBilnr_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTransportDispBilNrRecord> searchBilnr(@RequestParam String applicationUser, @RequestParam String id) {
		  logger.info("Inside searchBilnr");
		  List result = new ArrayList();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_BILNR_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&sokbnr=" + id;
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    		  if(jsonPayload!=null){
    			  JsonTransportDispBilNrContainer container = this.transportDispChildWindowService.getBilNrContainer(jsonPayload);
	    		if(container!=null){
	    			List<JsonTransportDispBilNrRecord> list = new ArrayList();
	    			for(JsonTransportDispBilNrRecord  record : container.getBilnrlist()){
	    				list.add(record);
	    			}
	    			result = list;
	    		}
	    	  }
	    	  return result;
	  }

	  /**
	   * 
	   * @param applicationUser
	   * @param id
	   * @return
	   */
	  @RequestMapping(value = "searchDriver_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTransportDispDriverRecord> searchDriver(@RequestParam String applicationUser, @RequestParam String driverId) {
		  logger.info("Inside searchDriver");
		  List result = new ArrayList();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_DRIVER_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&soksja=" + driverId;
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  if(jsonPayload!=null){
    		JsonTransportDispDriverContainer container = this.transportDispChildWindowService.getDriverContainer(jsonPayload);
    		if(container!=null){
    			List<JsonTransportDispDriverRecord> list = new ArrayList();
    			for(JsonTransportDispDriverRecord  record : container.getSjoflist()){
    				list.add(record);
    				
    			}
    			result = list;
    		}
    	  }
    	  return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param id
	   * @return
	   */
	  @RequestMapping(value = "searchTranspCarrier_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTransportDispTranspCarrierRecord> searchTranspCarrier(@RequestParam String applicationUser, @RequestParam String id) {
		  logger.info("Inside searchTranspCarrier");
		  List result = new ArrayList();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_CARRIER_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&soktnr=" + id + "&getval=J";
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  if(jsonPayload!=null){
			  JsonTransportDispTranspCarrierContainer container = this.transportDispChildWindowService.getTranspCarrierContainer(jsonPayload);
    		if(container!=null){
    			List<JsonTransportDispTranspCarrierRecord> list = new ArrayList();
    			for(JsonTransportDispTranspCarrierRecord  record : container.getTranslist()){
    				list.add(record);
    			}
    			result = list;
    		}
    	  }
    	  return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param id
	   * @param countryCode
	   * @return
	   */
	  @RequestMapping(value = "searchPostNumber_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonPostalCodesRecord> searchPostNumber(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String countryCode) {
		  this.controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr (this.urlCgiProxyService, this.transportDispChildWindowService);
		  JsonPostalCodesRecord record = new JsonPostalCodesRecord();
		  record.setSt2kod(id);
		  record.setSt2lk(countryCode);
		  boolean exactMatch = true;
		  Collection result = this.controllerAjaxCommonFunctionsMgr.fetchPostalCodes(applicationUser, record, exactMatch );
		  return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param id
	   * @param countryCode
	   * @return
	   */
	  @RequestMapping(value = "searchDangerousGoods_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonTransportDispDangerousGoodsRecord> searchDangerousGoods(@RequestParam String applicationUser, 
																	@RequestParam String unnr, @RequestParam String embg , @RequestParam String indx) {
		  	Collection<JsonTransportDispDangerousGoodsRecord> result = new ArrayList<JsonTransportDispDangerousGoodsRecord>();

		  	//this.controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr (this.urlCgiProxyService, this.transportDispChildWindowService);
		  	JsonTransportDispDangerousGoodsContainer record = new JsonTransportDispDangerousGoodsContainer();
		  	record.setUnnr(unnr);
		  	if(!"?".equals(embg)){record.setEmbg(embg);}
		  	if(!"?".equals(indx)){record.setIndx(indx);}
		  
		  	logger.info("Inside searchDangerousGoods...");
		  
	  		//prepare the access CGI with RPG back-end
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_DANGEROUS_GOODS_URL;
			//adjust from jquery/jsp
			if("?".equals(record.getEmbg())){ record.setEmbg(""); }
			if("?".equals(record.getIndx())){ record.setIndx(""); }
			
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersDangerousGoods(applicationUser, record);
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
			logger.debug(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			
			if(jsonPayload!=null){
				JsonTransportDispDangerousGoodsContainer container = this.transportDispChildWindowService.getDangerousGoodsContainer(jsonPayload);
				if(container!=null){
					result = container.getUnNumbers();
				}else{
					logger.info("**************** CONTAINER = NULL");
				}
			}
			//logger.info("**************** List Size:" + result.size());
			return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param kode
	   * @return
	   */
	  @RequestMapping(value = "searchPackingCodes_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonTransportDispPackingCodesRecord> searchPackingCodes(@RequestParam String applicationUser,@RequestParam String kode) {
		  	Collection<JsonTransportDispPackingCodesRecord> result = new ArrayList<JsonTransportDispPackingCodesRecord>();
		  	logger.info("Inside searchPackingCodes...");
		  
	  		//prepare the access CGI with RPG back-end
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_PACKING_CODES_URL;
			
			String urlRequestParamsKeys = "user=" + applicationUser + "&kode=" + kode + "&fullinfo=J&getval=J";
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
			logger.info(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			
			if(jsonPayload!=null){
				JsonTransportDispPackingCodesContainer container = this.transportDispChildWindowService.getPackingCodesContainer(jsonPayload);
				if(container!=null){
					result = container.getForpaknKoder();
				}else{
					logger.info("**************** CONTAINER = NULL");
				}
			}
			//logger.info("**************** List Size:" + result.size());
			return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param kode
	   * @return
	   */
	  @RequestMapping(value = "sendSMS_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonTransportDispSendSmsContainer> sendSMS(@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd, 
			  							@RequestParam String smsnr, @RequestParam String smslang, @RequestParam String smsType, @RequestParam String smsFreeText1, 
			  							@RequestParam String smsFreeText2, @RequestParam String smsUrlLink  ) {
		  	Collection<JsonTransportDispSendSmsContainer> result = new ArrayList<JsonTransportDispSendSmsContainer>();
		  	logger.info("Inside sendSMS...");
		  	//
		  	logger.info("smsType:" + smsType);
		  	logger.info("smsFreeText1:" + smsFreeText1);
		  	logger.info("smsFreeText2:" + smsFreeText2);
		  	logger.info("smsUrlLink:" + smsUrlLink);
		  	
		  	//http://gw.systema.no/sycgip/tjop11hs.pgm?user=JOVO&avd=75&opd=108&type=&smsnr=48052470
		  	//prepare default
		  	String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_URL;
			String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd + "&opd=" + opd + "&smsnr=" + smsnr + "&sprak=" + smslang;
			if(strMgr.isNotNull(smsType) && smsType.equals("general")){
				BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_GENERAL_URL;
				StringBuffer tmpParams = new StringBuffer();
				tmpParams.append("&merknad1=" + smsFreeText1);
				tmpParams.append("&merknad2=" + smsFreeText2);
				tmpParams.append("&lenke=" + smsUrlLink);
				urlRequestParamsKeys = urlRequestParamsKeys + tmpParams.toString();
			}
			
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
			logger.info(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp"); 
			
			if(jsonPayload!=null){
				JsonTransportDispSendSmsContainer container = this.transportDispChildWindowService.getSendSmsContainer(jsonPayload);
				if(container!=null){
					result.add(container);
				}else{
					String errMsg = "CONTAINER = NULL in Ajax: sendSMS_TransportDisp.do";
					logger.info(errMsg);
					container = new JsonTransportDispSendSmsContainer();
					container.setErrMsg(errMsg);
				}
			}
			
			return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param tur
	   * @param smsnr
	   * @param smslang
	   * @return
	   */
	  @RequestMapping(value = "sendSMSFromTur_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonTransportDispSendSmsContainer> sendSMSFromTur(@RequestParam String applicationUser, @RequestParam String tur, 
			  						@RequestParam String smsnr, @RequestParam String smslang ) {
		  	Collection<JsonTransportDispSendSmsContainer> result = new ArrayList<JsonTransportDispSendSmsContainer>();
		  	logger.info("Inside sendSMSFromTur...");
		  	
		  	
		  	//http://gw.systema.no/sycgip/tjfa55s.pgm?user=JOVO&tur=80000060&smsnr=48052470&sprak=EN
		  	//prepare the access CGI with RPG back-end
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_SMS_FROM_TUR_URL;
			String urlRequestParamsKeys = "user=" + applicationUser + "&tur=" + tur + "&smsnr=" + smsnr + "&sprak=" + smslang;
			
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
			logger.info(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp"); 
			
			if(jsonPayload!=null){
				JsonTransportDispSendSmsContainer container = this.transportDispChildWindowService.getSendSmsContainer(jsonPayload);
				if(container!=null){
					result.add(container);
				}else{
					String errMsg = "CONTAINER = NULL in Ajax: sendSMSFromTur_TransportDisp.do";
					logger.info(errMsg);
					container = new JsonTransportDispSendSmsContainer();
					container.setErrMsg(errMsg);
				}
			}
			
			return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param tur
	   * @param merk
	   * @param email
	   * @param lang
	   * @return
	   */
	  @RequestMapping(value = "sendEmailFromTur_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonTransportDispSendSmsContainer> sendEmailFromTur(@RequestParam String applicationUser, @RequestParam String tur, 
			  					@RequestParam String email, @RequestParam String text, @RequestParam String emailLang ) {
		  	Collection<JsonTransportDispSendSmsContainer> result = new ArrayList<JsonTransportDispSendSmsContainer>();
		  	logger.info("Inside sendEmailFromTur...");
		  	
		  	//http://gw.systema.no/sycgip/tjfa55m.pgm?user=JOVO&tur=75000020&merk=Dette_er_en_merknad&mail=janottar@systema.no&sprak=EN
		  	String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_EMAIL_FROM_TUR_URL;
			String urlRequestParamsKeys = "user=" + applicationUser + "&tur=" + tur + "&merk=" + text + "&mail=" + email + "&sprak=" + emailLang;
			
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
			logger.info(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp"); 
			
			if(jsonPayload!=null){
				JsonTransportDispSendSmsContainer container = this.transportDispChildWindowService.getSendSmsContainer(jsonPayload);
				if(container!=null){
					result.add(container);
				}else{
					String errMsg = "CONTAINER = NULL in Ajax: sendEmailFromTur_TransportDisp.do";
					logger.info(errMsg);
					container = new JsonTransportDispSendSmsContainer();
					container.setErrMsg(errMsg);
				}
			}
			
			return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @param email
	   * @param text
	   * @param emailLang
	   * @return
	   */
	  @RequestMapping(value = "sendEmail_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonTransportDispSendSmsContainer> sendEmail(@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd, 
			  					@RequestParam String email,@RequestParam String text, @RequestParam String emailLang ) {
		  	Collection<JsonTransportDispSendSmsContainer> result = new ArrayList<JsonTransportDispSendSmsContainer>();
		  	logger.info("Inside sendEmail...");
		  	
		  	//http://gw.systema.no/sycgip/tjop11h.pgm?user=OSCAR&avd=75&opd=108&merk=Dette_er_en_merknad&mail=oscar@systema.no&sprak=EN
		  	String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_SEND_EMAIL_URL;
			String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd + "&opd=" + opd + "&merk=" + text + "&mail=" + email + "&sprak=" + emailLang;
			
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
			logger.info(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp"); 
			
			if(jsonPayload!=null){
				JsonTransportDispSendSmsContainer container = this.transportDispChildWindowService.getSendSmsContainer(jsonPayload);
				if(container!=null){
					result.add(container);
				}else{
					String errMsg = "CONTAINER = NULL in Ajax: sendEmailFromTur_TransportDisp.do";
					logger.info(errMsg);
					container = new JsonTransportDispSendSmsContainer();
					container.setErrMsg(errMsg);
				}
			}
			
			return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param kode
	   * @return
	   */
	  @RequestMapping(value = "searchTollstedCodes_TransportDisp.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonTransportDispTollstedCodesRecord> searchTollstedCodes(@RequestParam String applicationUser, @RequestParam String kode) {
		  	Collection<JsonTransportDispTollstedCodesRecord> result = new ArrayList<JsonTransportDispTollstedCodesRecord>();
		  	logger.info("Inside searchPackingCodes...");
		  
	  		//prepare the access CGI with RPG back-end
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_TOLLSTED_CODES_URL;
			
			String urlRequestParamsKeys = "user=" + applicationUser + "&kode=" + kode + "&fullinfo=J&getval=J";
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
			logger.info(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			
			if(jsonPayload!=null){
				JsonTransportDispTollstedCodesContainer container = this.transportDispChildWindowService.getTollstedCodesContainer(jsonPayload);
				if(container!=null){
					result = container.getTollstedsKoder();
				}else{
					logger.info("**************** CONTAINER = NULL");
				}
			}
			//logger.info("**************** List Size:" + result.size());
			return result;
	  }
	  
	  /**
	   * 
	   * @param recordToValidate
	   * @param appUser
	   * @param mode
	   * @return
	   */
	  private String getRequestUrlKeyParameters(JsonTransportDispWorkflowSpecificOrderRecord recordToValidate, String applicationUser, String mode){
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			
			if(TransportDispConstants.MODE_UPDATE.equalsIgnoreCase(mode)){
				urlRequestParamsKeys.append("user=" + applicationUser);
				urlRequestParamsKeys.append("&avd=" + recordToValidate.getHeavd());
				urlRequestParamsKeys.append("&opd=" + recordToValidate.getHeopd());
				urlRequestParamsKeys.append("&mode=" + TransportDispConstants.MODE_UPDATE);
				
				
			}else if(TransportDispConstants.MODE_ADD.equalsIgnoreCase(mode)){
				urlRequestParamsKeys.append("user=" + applicationUser);
				urlRequestParamsKeys.append("&avd=" + recordToValidate.getHeavd());
				urlRequestParamsKeys.append("&mode=" + TransportDispConstants.MODE_ADD);
				
				
			}else if(TransportDispConstants.MODE_DELETE.equalsIgnoreCase(mode)){
				urlRequestParamsKeys.append("user=" + applicationUser);
				urlRequestParamsKeys.append("&avd=" + recordToValidate.getHeavd());
				urlRequestParamsKeys.append("&opd=" + recordToValidate.getHeopd());
				urlRequestParamsKeys.append("&fbn=1");
				urlRequestParamsKeys.append("&lin=" + recordToValidate.getOrderLineToDelete());
				urlRequestParamsKeys.append("&mode=" + TransportDispConstants.MODE_DELETE);
				
			}
			
			return urlRequestParamsKeys.toString();
		}
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param searchFilter
	   * @return
	   */
	  private String getRequestUrlKeyParametersDangerousGoods(String applicationUser, JsonTransportDispDangerousGoodsContainer searchFilter){
		  	String CODE_UNNR = "U";
		  	String CODE_EMBG = "E";
		  	String CODE_INDX = "J";
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + applicationUser);
			String matchOnlyCode = CODE_UNNR; //Deafault
			
			if(searchFilter.getUnnr()!=null && !"".equals(searchFilter.getUnnr())){
				urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "unnr=" + searchFilter.getUnnr());
			}
			//user=JOVO&unnr=1950=&embg=&indx=&getval=&fullinfo=J
			
			if(searchFilter.getEmbg()!=null && !"".equals(searchFilter.getEmbg())){
				//searching for perfect match (otherwise it will return from an unnr-number and forward...)
				matchOnlyCode = CODE_EMBG;
				urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "embg=" + searchFilter.getEmbg());
			}
			if(searchFilter.getIndx()!=null && !"".equals(searchFilter.getIndx())){
				//searching for perfect match (otherwise it will return from an unnr-number and forward...)
				matchOnlyCode = CODE_INDX;
				urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "indx=" + searchFilter.getIndx());
			}
			
			urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "matchOnly=" + matchOnlyCode); 
			//urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=J"); //always the max. nr of columns (as default)
			
			return urlRequestParamsKeys.toString();
		}
	  
	  /**
		 * 
		 * @param applicationUser
		 * @param requestString
		 * @return
		 */
		@RequestMapping(value = "validateSpecificOpenOrder_TransportDisp.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTransportDispWorkflowSpecificOrderContainer> validateSpecificOpenOrder
		  						(@RequestParam String applicationUser, @RequestParam String requestString){
			 logger.info("Inside: validateSpecificOpenOrder");
			 List<JsonTransportDispWorkflowSpecificOrderContainer> result = new ArrayList<JsonTransportDispWorkflowSpecificOrderContainer>();
			 //logger.info(requestString);
			 if(requestString!=null && !"".equals(requestString)){
			 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_URL;
			 	 
				 //add URL-parameters
				 String urlRequestParams = "user=" + applicationUser + requestString;
				 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				 logger.info("URL: " + BASE_URL);
				 logger.info("URL PARAMS: " + urlRequestParams);
				 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				 
				 if(jsonPayload!=null){
					JsonTransportDispWorkflowSpecificOrderContainer container = this.transportDispWorkflowSpecificOrderService.getContainer(jsonPayload);
					logger.info("A");
		    		if(container!=null){
		    			logger.info("B ->errMsg:" + container.getErrMsg());
		    			logger.info("B ->wsavd:" + container.getWsavd());
		    			
		    			result.add(container);
		    		}
		    	  }
			 }
			 return result;
		}
	  
	  /**
	   * Gets a specific invoice line
	   * 
	   * @param applicationUser
	   * @param requestString
	   * @return
	   */
		@RequestMapping(value = "getOrderInvoiceDetailLine_TransportDisp.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> getOrderInvoiceDetailLine
		  						(@RequestParam String applicationUser, @RequestParam String requestString){
			 logger.info("Inside: getOrderInvoiceDetailLine");
			 List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> result = new ArrayList<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>();
			 //logger.info(requestString);
			 if(requestString!=null && !"".equals(requestString)){
			 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_INVOICE_URL;
			 	 //http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
			 	 
				 //add URL-parameters
				 String urlRequestParams = requestString;
				 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				 logger.info("URL: " + BASE_URL);
				 logger.info("URL PARAMS: " + urlRequestParams);
				 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				 
				 if(jsonPayload!=null){
					 JsonTransportDispWorkflowSpecificOrderInvoiceContainer container = this.transportDispWorkflowSpecificOrderService.getOrderInvoiceContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> list = new ArrayList();
		    			for(JsonTransportDispWorkflowSpecificOrderInvoiceRecord  record : container.getInvoiceLines()){
		    				//logger.info("fask:" + record.getFask());
		    				list.add(record);
		    			}
		    			result = list;
		    		}
		    	  }
			 }
			 return result;
		}
		
		/**
		 * 
		 * @param applicationUser
		 * @param id
		 * @return
		 */
		@RequestMapping(value = "validateSupplierInvoiceDetailLine_TransportDisp.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTransportDispSupplierRecord> validateSupplierInvoiceDetailLine
		  						(@RequestParam String applicationUser, @RequestParam String id){
			 logger.info("Inside: validateSupplierInvoiceDetailLine");
			 List<JsonTransportDispSupplierRecord> result = new ArrayList<JsonTransportDispSupplierRecord>();
			 //logger.info(id);
			 
		 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_SUPPLIER_URL;
		 	 //http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
		 	 
			 //add URL-parameters
			 String urlRequestParams = "user=" + applicationUser + "&kode=" + id + "&getval=J";
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 if(jsonPayload!=null){
				JsonTransportDispSupplierContainer container = this.transportDispChildWindowService.getSupplierContainer(jsonPayload);
	    		if(container!=null){
	    			List<JsonTransportDispSupplierRecord> list = new ArrayList();
	    			for(JsonTransportDispSupplierRecord  record : container.getLeverandorer()){
	    				//logger.info("supplier:" + record.getLevnr() + " " + record.getLnavn());
	    				list.add(record);
	    			}
	    			result = list;
	    		}
	    	  }
			
			 return result;
		}
		/**
		 * General validation of supplier
		 * 
		 * @param applicationUser
		 * @param id
		 * @return
		 */
		@RequestMapping(value = "validateSupplier_TransportDisp.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTransportDispSupplierRecord> validateSupplier
		  						(@RequestParam String applicationUser, @RequestParam String id){
			 logger.info("Inside: validateSupplier");
			 List<JsonTransportDispSupplierRecord> result = new ArrayList<JsonTransportDispSupplierRecord>();
			 //logger.info(id);
			 
		 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_SUPPLIER_URL;
		 	 //http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
		 	 
			 //add URL-parameters
			 String urlRequestParams = "user=" + applicationUser + "&kode=" + id + "&getval=J";
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 if(jsonPayload!=null){
				JsonTransportDispSupplierContainer container = this.transportDispChildWindowService.getSupplierContainer(jsonPayload);
	    		if(container!=null){
	    			List<JsonTransportDispSupplierRecord> list = new ArrayList();
	    			for(JsonTransportDispSupplierRecord  record : container.getLeverandorer()){
	    				//logger.info("supplier:" + record.getLevnr() + " " + record.getLnavn());
	    				list.add(record);
	    			}
	    			result = list;
	    		}
	    	  }
			
			 return result;
		}
		
		/**
		 * 
		 * @param applicationUser
		 * @param id
		 * @return
		 */
		@RequestMapping(value = "validateCustomerInvoiceDetailLine_TransportDisp.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTransportDispCustomerRecord> validateCustomerDetailLine
		  						(@RequestParam String applicationUser, @RequestParam String id){
			 logger.info("Inside: validateCustomerInvoiceDetailLine");
			 List<JsonTransportDispCustomerRecord> result = new ArrayList<JsonTransportDispCustomerRecord>();
			 //logger.info(id);
			 
		 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_URL;
		 	 //http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
		 	 
			 //add URL-parametersƒ
			 String urlRequestParams = "user=" + applicationUser + "&sokknr=" + id + "&getval=J";
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 if(jsonPayload!=null){
				 JsonTransportDispCustomerContainer container = this.transportDispChildWindowService.getCustomerContainer(jsonPayload);
	    		if(container!=null){
	    			List<JsonTransportDispCustomerRecord> list = new ArrayList();
	    			for(JsonTransportDispCustomerRecord  record : container.getInqcustomer()){
	    				logger.info("customer aktkod:" + record.getAktkod());
	    				list.add(record);
	    			}
	    			result = list;
	    		}
	    	  }
			
			 return result;
		}
		/**
		 * 
		 * @param applicationUser
		 * @param avd
		 * @param opd
		 * @return
		 */
		@RequestMapping(value = "updateReadyMarkInvoice.do", method = RequestMethod.GET)
	 	public @ResponseBody List<JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer> doReadyMarkInvoice (@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd){
			logger.info("Inside: validateCustomerInvoiceDetailLine");
			List<JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer> result = new ArrayList<JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer>();
			
			logger.info(" Ready mark start process... ");
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_STATUS_READYMARK_MAIN_ORDER_INVOICE_URL;
	    	//add URL-parameters
			StringBuffer urlRequestParams = new StringBuffer();
			urlRequestParams.append("user=" + applicationUser); 
			urlRequestParams.append("&avd=" + avd);
			urlRequestParams.append("&opd=" + opd);
			urlRequestParams.append("&mode=");
			
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParams);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			//Debug -->
			logger.info(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		
			if(jsonPayload!=null){
				JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer container = this.transportDispWorkflowSpecificOrderService.getOrderInvoiceReadyMarkContainer(jsonPayload);
				result.add(container);
			}
			return result;
		}
		
		/**
		 * 
		 * @param request
		 * @return
		 */
		@RequestMapping(value="uploadFileFromOrder.do", method = RequestMethod.POST)
	    public @ResponseBody String uploadFileFromOrder(MultipartHttpServletRequest request) {
			final String ERROR_TAG = "[ERROR] ";
			
			logger.info("Inside: uploadFileFromOrder");
			Iterator<String> itr = request.getFileNames();
		    MultipartFile file = null;
		    try {
		        file = request.getFile(itr.next()); //Get the file.
		    } catch (NoSuchElementException e) {
		    	logger.info(ERROR_TAG + e.toString());
		    }
		    String applicationUser = request.getParameter("applicationUserUpload");
		    String avd = request.getParameter("wsavd");
		    String opd = request.getParameter("wsopd");
		    String type = request.getParameter("wstype");
		    String fileNameNew = request.getParameter("fileNameNew");
		    //timestamps (when applicable)
		    String userDate = request.getParameter("userDate");
		    String userTime = request.getParameter("userTime");
		    //logger.info("userDate:" + userDate);
		    //logger.info("userTime:" + userTime);
		    
		    
		    if (file!=null && !file.isEmpty()) {
		    	//default
        		String fileNameRaw = file.getOriginalFilename();
		    	//extra round in order to catch non-ASCII chars
        		try{
		    		fileNameRaw = new String(file.getOriginalFilename().getBytes("iso-8859-1"), "UTF-8");
		    	}catch(Exception e){
		    		logger.info(e.toString());
		    		e.printStackTrace();
		    	}
        		//proceed
        		if(fileNameNew!=null && !"".equals(fileNameNew)){ fileNameRaw = fileNameNew; }
        		logger.info("FILE NAME (raw):" + fileNameRaw);
        		//remove all invalid file name characters: ASCII: 1-31 + " *, :, <, >, ?, \, /, | "
        		String fileName = strMgr.replaceInvalidCharsForFilename(fileNameRaw);
        		logger.info("FILE NAME (clean):" + fileName);
        		
                //validate file
                JsonTransportDispFileUploadValidationContainer uploadValidationContainer = this.validateFileUpload(fileName, applicationUser);
                //if valid
                if(uploadValidationContainer!=null && "".equals(uploadValidationContainer.getErrMsg())){
	                // TEST String rootPath = System.getProperty("catalina.home");
                		String rootPath	= uploadValidationContainer.getTmpdir();
                	    File dir = new File(rootPath);
                	    
		        	    try {
			                byte[] bytes = file.getBytes();
			                // Create the file on server
			                File serverFile = new File(dir.getAbsolutePath() + File.separator +  fileName);
			                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			                stream.write(bytes);
			                stream.close();
			                logger.info("Server File Location=" + serverFile.getAbsolutePath());
			                //catch parameters
			                uploadValidationContainer.setWsavd(avd);
	        	    		uploadValidationContainer.setWsopd(opd);
	        	    		uploadValidationContainer.setWstype(type);
	        	    		//this will check if either the wstur or wsavd/wsopd will save the upload
	        	    		uploadValidationContainer = this.saveFileUpload(uploadValidationContainer, fileName, applicationUser, userDate, userTime);
			                if(uploadValidationContainer!=null && uploadValidationContainer.getErrMsg()==""){
		                		String suffixMsg = "";
		                		if(uploadValidationContainer.getWstur()!=null && !"".equals(uploadValidationContainer.getWstur())){
		                			suffixMsg = "  -->Tur:" + "["+ uploadValidationContainer.getWstur() + "]";
		                		}else{
		                			suffixMsg = "  -->Avd/Opd:" + "["+ uploadValidationContainer.getWsavd() + "/" + uploadValidationContainer.getWsopd() + "]";
		                		}
		                		return "[OK] You successfully uploaded file:" + fileName +  suffixMsg;
			                }else{
		                		return ERROR_TAG + "You failed to upload [on MOVE] =" + fileName;
			                }
		        	    } catch (Exception e) {
		            		//run time upload error
		            		String absoluteFileName = rootPath + File.separator + fileName;
		            		return ERROR_TAG + "You failed to upload to:" + fileName + " runtime error:" + e.getMessage();
		        	    }

                }else{
		        		if(uploadValidationContainer!=null){
		        			logger.info(uploadValidationContainer.getErrMsg());
		        			//Back-end error message output upon validation
		        			return ERROR_TAG + uploadValidationContainer.getErrMsg();
		        		}else{
		        			return ERROR_TAG + "NULL on upload file validation Object??";
		        		}
	        	}
	        } else {
	        	logger.info("FILE NAME empty!");
	        	return ERROR_TAG + "You failed to upload " + fileNameNew + " because the file was empty.";
	        }
		    
		}
		/**
		 * 
		 * @param request
		 * @return
		 */
		@RequestMapping(value="uploadFileFromTrip.do", method = RequestMethod.POST)
	    public @ResponseBody String uploadFileFromTrip(MultipartHttpServletRequest request) {
			final String ERROR_TAG = "[ERROR] ";
			logger.info("Inside: uploadFileFromTrip");
			Iterator<String> itr = request.getFileNames();
		    MultipartFile file = null;
		    try {
		        file = request.getFile(itr.next()); //Get the file.
		    } catch (NoSuchElementException e) {
		    	logger.info(ERROR_TAG + e.toString());
		    }
		    //file upload parameters to catch
		    String applicationUser = "";String tur = "";
		    String type = "";String fileNameNew = "";
		    //get the list of parameters
		    Enumeration requestEnum=request.getParameterNames();
		    while(requestEnum.hasMoreElements()){
		    	Object obj=requestEnum.nextElement();
				String param=(String)obj;
				if(param.startsWith("applicationUserUpload")){ applicationUser=request.getParameter(param); }
				else if (param.startsWith("wstur")){ tur=request.getParameter(param); }
				else if (param.startsWith("wstype")){ type=request.getParameter(param); }
				else if (param.startsWith("fileNameNew")){ fileNameNew=request.getParameter(param); }
			}
		    
		    if (file!=null && !file.isEmpty()) {
        		String fileNameRaw = file.getOriginalFilename();
        		//extra round in order to catch non-ASCII chars
        		try{
		    		fileNameRaw = new String(file.getOriginalFilename().getBytes("iso-8859-1"), "UTF-8");
		    	}catch(Exception e){
		    		logger.info(e.toString());
		    		e.printStackTrace();
		    	}
        		//proceed
        		logger.info("FILE NAME:" + fileNameRaw);
        		if(fileNameNew!=null && !"".equals(fileNameNew)){ fileNameRaw = fileNameNew; }
        		logger.info("FILE NAME (raw):" + fileNameRaw);
        		//remove all invalid file name characters: ASCII: 1-31 + " *, :, <, >, ?, \, /, | "
        		String fileName = strMgr.replaceInvalidCharsForFilename(fileNameRaw);
        		logger.info("FILE NAME (clean):" + fileName);
        		
                //validate file
                JsonTransportDispFileUploadValidationContainer uploadValidationContainer = this.validateFileUpload(fileName, applicationUser);
                //if valid
                if(uploadValidationContainer!=null && "".equals(uploadValidationContainer.getErrMsg())){
	                // TEST String rootPath = System.getProperty("catalina.home");
                		String rootPath	= uploadValidationContainer.getTmpdir();
                	    File dir = new File(rootPath);
                	    
		        	    try {
			                byte[] bytes = file.getBytes();
			                // Create the file on server
			                File serverFile = new File(dir.getAbsolutePath() + File.separator +  fileName);
			                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			                stream.write(bytes);
			                stream.close();
			                logger.info("Server File Location=" + serverFile.getAbsolutePath());
			                //catch parameters
			                uploadValidationContainer.setWstur(tur);
	        	    		uploadValidationContainer.setWstype(type);
	        	    		
	        	    		String userDate = null; String userTime = null;//dummies (could be real as in the uploadFileFromOrder-method) 
	        	    		
	        	    		uploadValidationContainer = this.saveFileUpload(uploadValidationContainer, fileName, applicationUser, userDate, userTime);
			                if(uploadValidationContainer!=null && uploadValidationContainer.getErrMsg()==""){
			                		String suffixMsg = "";
			                		if(uploadValidationContainer.getWstur()!=null && !"".equals(uploadValidationContainer.getWstur())){
			                			suffixMsg = "  -->Tur:" + "["+ uploadValidationContainer.getWstur() + "]";
			                		}else{
			                			suffixMsg = "  -->Avd/Opd:" + "["+ uploadValidationContainer.getWsavd() + "/" + uploadValidationContainer.getWsopd() + "]";
			                		}
			                		return "[OK] You successfully uploaded file:" + fileName +  suffixMsg;
			                }else{
			                		return ERROR_TAG + "You failed to upload [on MOVE] =" + fileName;
			                }
		        	    } catch (Exception e) {
		            		//run time upload error
		            		String absoluteFileName = rootPath + File.separator + fileName;
		            		return ERROR_TAG + "You failed to upload to:" + fileName + " runtime error:" + e.getMessage();
		        	    }

                }else{
		        		if(uploadValidationContainer!=null){
		        			logger.info(uploadValidationContainer.getErrMsg());
		        			//Back-end error message output upon validation
		        			return ERROR_TAG + uploadValidationContainer.getErrMsg();
		        		}else{
		        			return ERROR_TAG + "NULL on upload file validation Object??";
		        		}
	        	}
	        } else {
	        	logger.info( ERROR_TAG + "FILE NAME empty!");
	        	return ERROR_TAG + "You failed to upload " + fileNameNew + " because the file was empty.";
	        }
		}
		
		
	    /**
	     * 
	     * @param fileName
	     * @param appUser
	     * @return
	     */
		private JsonTransportDispFileUploadValidationContainer validateFileUpload(String fileName, String applicationUser){
			JsonTransportDispFileUploadValidationContainer uploadValidationContainer = null;
			//prepare the access CGI with RPG back-end
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_UPLOAD_FILE_VALIDATION_URL;
			String urlRequestParamsKeys = "user=" + applicationUser + "&wsdokn=" + fileName;
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			logger.info(jsonPayload);
			//Debug -->
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			if(jsonPayload!=null){
				uploadValidationContainer = this.transportDispChildWindowService.getFileUploadValidationContainer(jsonPayload);
				logger.info(uploadValidationContainer.getErrMsg());
			}
			return uploadValidationContainer;
		}
		/**
		 * 
		 * @param uploadValidationContainer
		 * @param fileName
		 * @param applicationUser
		 * @param userDate
		 * @param userTime
		 * @return
		 */
		private JsonTransportDispFileUploadValidationContainer saveFileUpload(JsonTransportDispFileUploadValidationContainer uploadValidationContainer, String fileName, String applicationUser, String userDate, String userTime){
			//prepare the access CGI with RPG back-end
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_UPLOAD_FILE_AFTER_VALIDATION_APPROVAL_URL;
			String absoluteFileName = uploadValidationContainer.getTmpdir() + fileName;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + applicationUser);
			//Either TUR or AVD/OPD (order level)... Depending on the caller (Tur-level OR order-level)
			if(uploadValidationContainer.getWstur()!=null && !"".equals(uploadValidationContainer.getWstur())){
				urlRequestParamsKeys.append("&wstur=" + uploadValidationContainer.getWstur());
			}else{
				if(uploadValidationContainer.getWsavd()!=null && !"".equals(uploadValidationContainer.getWsavd())){
					urlRequestParamsKeys.append("&wsavd=" + uploadValidationContainer.getWsavd());
				}
				if(uploadValidationContainer.getWsopd()!=null && !"".equals(uploadValidationContainer.getWsopd())){
					urlRequestParamsKeys.append("&wsopd=" + uploadValidationContainer.getWsopd());
				}
			}
			urlRequestParamsKeys.append("&wstype=" + uploadValidationContainer.getWstype());
			urlRequestParamsKeys.append("&wsdokn=" + absoluteFileName);
			//Timestamp (if applicable)
			if( (userDate!=null && !"".equals(userDate)) && (userTime!=null && !"".equals(userTime))){
				urlRequestParamsKeys.append("&wsdate=" + userDate + "&wstime=" + userTime);
			}
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			//Debug -->
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			if(jsonPayload!=null){
				uploadValidationContainer = this.transportDispChildWindowService.getFileUploadValidationContainer(jsonPayload);
				logger.info(uploadValidationContainer.getErrMsg());
			}
			return uploadValidationContainer; //return
		}
		
		/**
		   * Gets a specific invoice line
		   * 
		   * @param applicationUser
		   * @param requestString
		   * @return
		   */
			@RequestMapping(value = "getBudgetDetailLine_TransportDisp.do", method = RequestMethod.GET)
		    public @ResponseBody List<JsonTransportDispWorkflowSpecificBudgetRecord> getBudgetDetailLine
			  						(@RequestParam String applicationUser, @RequestParam String requestString){
				 logger.info("Inside: getBudgetDetailLine");
				 List<JsonTransportDispWorkflowSpecificBudgetRecord> result = new ArrayList<JsonTransportDispWorkflowSpecificBudgetRecord>();
				 //logger.info(requestString);
				 if(requestString!=null && !"".equals(requestString)){
				 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_BUDGET_URL;
				 	 
					 //add URL-parameters
					 String urlRequestParams = requestString;
					 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					 logger.info("URL: " + BASE_URL);
					 logger.info("URL PARAMS: " + urlRequestParams);
					 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
					 
					 if(jsonPayload!=null){
						 JsonTransportDispWorkflowSpecificBudgetContainer container = this.transportDispWorkflowBudgetService.getContainer(jsonPayload);
			    		if(container!=null){
			    			List<JsonTransportDispWorkflowSpecificBudgetRecord> list = new ArrayList();
			    			for(JsonTransportDispWorkflowSpecificBudgetRecord  record : container.getBudgetLines()){
			    				logger.info(record.getBubnr());
			    				list.add(record);
			    			}
			    			result = list;
			    		}
			    	  }
				 }
				 return result;
			}	
			/**
			 * 
			 * @param applicationUser
			 * @param requestString
			 * @return
			 */
			@RequestMapping(value = "getFrisokveiDetailLine_TransportDisp.do", method = RequestMethod.GET)
		    public @ResponseBody List<JsonTransportDispWorkflowSpecificOrderFrisokveiContainer> getFrisokveiDetailLine
			  						(@RequestParam String applicationUser, @RequestParam String requestString){
				 logger.info("Inside: getFrisokveiDetailLine");
				 List<JsonTransportDispWorkflowSpecificOrderFrisokveiContainer> result = new ArrayList<JsonTransportDispWorkflowSpecificOrderFrisokveiContainer>();
				 //logger.info(requestString);
				 if(requestString!=null && !"".equals(requestString)){
				 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_MAIN_ORDER_FRISOKVEI_URL;
				 	 
					 //add URL-parameters
					 String urlRequestParams = requestString;
					 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					 logger.info("URL: " + BASE_URL);
					 logger.info("URL PARAMS: " + urlRequestParams);
					 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
					 
					 if(jsonPayload!=null){
						 JsonTransportDispWorkflowSpecificOrderFrisokveiContainer container = this.transportDispWorkflowSpecificOrderService.getOrderFrisokveiContainer(jsonPayload);
			    		if(container!=null){
			    			result.add(container);
			    		}
			    	  }
				 }
				 return result;
			}
			
			/**
			 * 
			 * @param applicationUser
			 * @param requestString
			 * @return
			 */
			@RequestMapping(value = "getDangerousGoodsDetailLine_TransportDisp.do", method = RequestMethod.GET)
		    public @ResponseBody List<JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord> getDangerousGoodsDetailLine
			  						(@RequestParam String applicationUser, @RequestParam String requestString){
				 logger.info("Inside: getDangerousGoodsDetailLine");
				 List<JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord> result = new ArrayList<JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord>();
				 //logger.info(requestString);
				 if(requestString!=null && !"".equals(requestString)){
				 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_DANGEROUSGOODS_URL;
				 	 
					 //add URL-parameters
					 String urlRequestParams = requestString;
					 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					 logger.info("URL: " + BASE_URL);
					 logger.info("URL PARAMS: " + urlRequestParams);
					 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
					 
					 if(jsonPayload!=null){
						 JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer container = this.transportDispWorkflowSpecificOrderService.getOrderDangerousGoodsContainer(jsonPayload);
			    		if(container!=null){
			    			for(JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord rec: container.getAdrlinelist()){
			    				result.add(rec);
			    			}
			    		}
			    	  }
				 }
				 return result;
			}
		
			/**
			 * 
			 * @param applicationUser
			 * @param requestString
			 * @return
			 */
			@RequestMapping(value = "addTripToOrder_TransportDisp.do", method = RequestMethod.GET)
		    public @ResponseBody List<JsonTransportDispWorkflowSpecificOrderRecord> addTripToOrder
			  						(@RequestParam String applicationUser, @RequestParam String requestString){
				 logger.info("Inside: addTripToOrder");
				 RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
				 
				 List<JsonTransportDispWorkflowSpecificOrderRecord> result = new ArrayList<JsonTransportDispWorkflowSpecificOrderRecord>();
				 //logger.info(requestString);
				 if(requestString!=null && !"".equals(requestString)){

					 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_ADD_DELETE_ORDER_FROM_TRIP_URL;
				 	 
					 //add URL-parameters
					 String urlRequestParams = "user="+ applicationUser + requestString;
					 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					 logger.info("URL: " + BASE_URL);
					 logger.info("URL PARAMS: " + urlRequestParams);
					 String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
					 
					 //Debug --> 
					 logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
					 //we must evaluate a return RPG code in order to know if the Update was OK or not
					 rpgReturnResponseHandler.evaluateRpgResponseOnAddRemoveOrder(rpgReturnPayload);
					 if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
			    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());

					 }else{
			    		//we return a phantom record only for signaling att the update went ok. Otherwise = not OK
			    		JsonTransportDispWorkflowSpecificOrderRecord record = new JsonTransportDispWorkflowSpecificOrderRecord();
			    		record.setHeopd("phantomOK");
			    		result.add(record);
					 }
				 }
				 return result;
			}
			
			
			/**
			 * 
			 * @param applicationUser
			 * @param rawString
			 * @return
			 * 
			 */
			@RequestMapping(value = "updateTripListCloseOpenTrip_TransportDisp.do", method = RequestMethod.GET)
		    public @ResponseBody Set<JsonTransportDispWorkflowSpecificTripRecord> updateTripListCloseOpenTrip
			  						(@RequestParam String applicationUser, @RequestParam String requestString){
				 logger.info("Inside: updateTripListCloseOpenTrip");
				 RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
				 
				 Set<JsonTransportDispWorkflowSpecificTripRecord> result = new HashSet<JsonTransportDispWorkflowSpecificTripRecord>();
				 
				 logger.info(requestString);
				 if(requestString!=null && !"".equals(requestString)){
					 String [] requestRecord = requestString.split("@");
					 List<String> requestParams = Arrays.asList(requestRecord);
					 for (String record : requestParams){
						 
						 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_ClOSE_SPECIFIC_TRIP_URL;
						 //add URL-parameters
						 StringBuffer urlRequestParams = new StringBuffer();
						 //urlRequestParams.append("user=" + applicationUser);
						 //record of type: user=OSCAR&wmode=D&wstur=75000001&wsavd=75&wsopd=6
						 urlRequestParams.append(record);
						 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
						 logger.info("URL: " + BASE_URL);
						 logger.info("URL PARAMS: " + urlRequestParams);
						 String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
						 
						 //Debug --> 
						 logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
						 //we must evaluate a return RPG code in order to know if the Update was OK or not
						 if(rpgReturnPayload!=null){
							 rpgReturnResponseHandler.evaluateRpgResponseOnTripUpdate(rpgReturnPayload);
							 if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
								 rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
								 logger.info(rpgReturnResponseHandler.getErrorMessage());
							 }
						 }
						 
						/*
						 //Now break the record in order to fill the return object for further handling on GUI (jQuery)
						 String[] tmp = record.split("&");
						 List<String> fields = Arrays.asList(tmp);
						 JsonTransportDispWorkflowSpecificTripRecord trip = new JsonTransportDispWorkflowSpecificTripRecord();
						 for (String field: fields){
							 if(field.contains("wsavd")){
								 trip.setTuavd(field.replace("wsavd=", ""));
							 }else if (field.contains("wstur")){
								 trip.setTupro(field.replace("wstur=", ""));					 
							 }
						 }
						 result.add(trip);
						 */
					 }
				 }
				 
				 return result;
			}
		/**
		 * Main gate in order to print to the system printer directly ...
		 * @param request
		 * @param bindingResult
		 * @return
		 */
		@RequestMapping(path="/printDocuments_TransportDisp.do", method = RequestMethod.POST)
		public Collection<String> printDocuments(@ModelAttribute PrintFormObjectDto dto, BindingResult bindingResult ){
			Collection list = new ArrayList();
			logger.info("Inside: printDocuments");
			
			logger.info("appUser:" + dto.getApplicationUser());
			logger.info("sign:" + dto.getSign());
			logger.info("avd:" + dto.getAvd());
			logger.info("opd:" + dto.getOpd());
			logger.info("tur:" + dto.getTur());
			//
			//logger.info("fbType:" + dto.getFbType());
			//logger.info("cmrType:" + dto.getCmrType());
			//logger.info("ffType:" + dto.getFfType());
			//logger.info("aordType:" + dto.getAordType());
			//logger.info("aordDocumentType:" + dto.getAordDocumentType());
			
			//Print fraktbrev
			if(strMgr.isNotNull(dto.getFbType()) && "fb".equals(dto.getFbType())) {list = this.printFraktbrev(dto); }
			//Print CMR
			if(strMgr.isNotNull(dto.getCmrType()) && "cmr".equals(dto.getCmrType())){ list = this.printCmrFraktbrev(dto); }
		    //Print FFakturor
			if(strMgr.isNotNull(dto.getFfType()) && "ff".equals(dto.getFfType())){ list = this.printFFakt(dto); }
			//Print Arbeidsordre
			if(strMgr.isNotNull(dto.getAordType()) && "aord".equals(dto.getAordType())){ list = this.printArbeidsOrdre(dto); }
			
			return list;
			  
		}
		
		/**
		 * 
		 * @param dto
		 * @param bindingResult
		 * @return
		 */
		@RequestMapping(path="/printDocumentsTrip_TransportDisp.do", method = RequestMethod.POST)
		public Collection<String> printDocumentsTrip(@ModelAttribute PrintFormObjectDto dto, BindingResult bindingResult ){
			Collection list = new ArrayList();
			
			logger.info("Inside: printDocumentsTrip");
			
			logger.info("appUser:" + dto.getApplicationUser());
			logger.info("avd:" + dto.getAvd());
			logger.info("opd:" + dto.getOpd());
			logger.info("tur:" + dto.getTur());
			//
			
			if(strMgr.isNotNull(dto.getFbType()) && "fb".equals(dto.getFbType())) {logger.info("fbType:"+ dto.getFbType());}
			if(strMgr.isNotNull(dto.getCmrType()) && "cmr".equals(dto.getCmrType())) {logger.info("cmrType:"+ dto.getCmrType());}
			if(strMgr.isNotNull(dto.getFfType()) && "ff".equals(dto.getFfType())) {logger.info("ffType:"+ dto.getFfType());}
			if(strMgr.isNotNull(dto.getAordType()) && "aordi".equals(dto.getAordType())) {logger.info("aordType:"+ dto.getAordType());}
			if(strMgr.isNotNull(dto.getAordTypee()) && "aorde".equals(dto.getAordTypee())) {logger.info("aordTypee:"+ dto.getAordTypee());}
			if(strMgr.isNotNull(dto.getGodslistType()) && "gl".equals(dto.getFbType())) {logger.info("godslistType:"+ dto.getGodslistType());}
			if(strMgr.isNotNull(dto.getLastlistType()) && "ll".equals(dto.getFbType())) {logger.info("lastlistType:"+ dto.getLastlistType());}
			if(strMgr.isNotNull(dto.getTurkonvoluttType()) && "tk".equals(dto.getFbType())) {logger.info("turkonvoluttType:"+ dto.getTurkonvoluttType());}
			//
			if(strMgr.isNotNull(dto.getFbTypeOnList()) && "fb".equals(dto.getFbType())) {logger.info("fbTypeOnList:"+ dto.getFbTypeOnList()); }
			if(strMgr.isNotNull(dto.getCmrTypeOnList()) && "cmr".equals(dto.getCmrType())) {logger.info("cmrTypeOnList:"+ dto.getCmrTypeOnList()); }
			if(strMgr.isNotNull(dto.getFfTypeOnList()) && "ff".equals(dto.getFfType())) {logger.info("ffTypeOnList:"+ dto.getFfTypeOnList()); }
			if(strMgr.isNotNull(dto.getGodslistTypeOnList()) && "gl".equals(dto.getFbType())) {logger.info("godslistTypeOnList:"+ dto.getGodslistTypeOnList()); }
			if(strMgr.isNotNull(dto.getLastlistTypeOnList()) && "ll".equals(dto.getFbType())) {logger.info("lastlistTypeOnList:"+ dto.getLastlistTypeOnList()); }
			
			//Print fraktbrev
			if(strMgr.isNotNull(dto.getFbType()) || strMgr.isNotNull(dto.getFbTypeOnList())){ 
				list = this.printFraktbrev(dto); 
			}
			//Print CMR-fraktbrev
			if(strMgr.isNotNull(dto.getCmrType()) || strMgr.isNotNull(dto.getCmrTypeOnList())){ 
				list = this.printCmrFraktbrev(dto); 
			}
			//Print Ferdigmeldte fakt.
			if(strMgr.isNotNull(dto.getFfType()) || strMgr.isNotNull(dto.getFfTypeOnList())){ 
				list = this.printFFakt(dto); 
			}
			//Arbeidsordre intern/extern
			if(strMgr.isNotNull(dto.getAordType()) || strMgr.isNotNull(dto.getAordTypee())){ 
				list = this.printArbeidsOrdreFromTrip(dto); 
			}
			//Print Godslista
			if(strMgr.isNotNull(dto.getGodslistType()) || strMgr.isNotNull(dto.getGodslistTypeOnList())){ 
				list = this.printGodsListLasteListTurKonvolutt(dto, this.TYPE_GODSL); 
			}
			//Print Lastelista
			if(strMgr.isNotNull(dto.getLastlistType()) || strMgr.isNotNull(dto.getLastlistTypeOnList())){ 
				list = this.printGodsListLasteListTurKonvolutt(dto, this.TYPE_LASTL); 
			}
			//Print Turkonvolutt
			if(strMgr.isNotNull(dto.getTurkonvoluttType()) ){ 
				list = this.printGodsListLasteListTurKonvolutt(dto, this.TYPE_TURKONVOLUTT); 
			}
		    
			return list;
			  
		}
		
	  /**
	   * 	
	   * @param dto
	   * @return
	   */
	  private Collection<String> printFraktbrev(PrintFormObjectDto dto){
		  logger.info("print fraktbrev ...");
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + dto.getApplicationUser());
			
		 //check the parent caller for this print (ORDER or TRIP)
		 if(strMgr.isNotNull(dto.getOpd()) && strMgr.isNotNull(dto.getAvd()) ){
			 //fill other params
			 urlRequestParamsKeys.append("&avd=" + dto.getAvd());
			 urlRequestParamsKeys.append("&opd=" + dto.getOpd());
			 urlRequestParamsKeys.append("&tur=");
		 }else{
			//fill other params
			 urlRequestParamsKeys.append("&avd=&opd=");
			 urlRequestParamsKeys.append("&tur=" + dto.getTur());
		 }
		  //-------------------------------------
		  //get BASE URL = RPG-PROGRAM for PRINT
          //-------------------------------------
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_PRINT_OUT_FRAKTBREV;
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the Print (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			//Debug --> 
	    	logger.info(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	//END of PRINT here and now
	    	logger.info("Method PRINT END");
	    	
	    	Collection<String> list = new ArrayList<String>();
			list.add("dummy");
			
			return list;
		  
	  }
	  
	  /**
	   * http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=JOVO&wsavd=75&wsopd=113&wspro=&jbk=J&wssg=JOV&cm=J
	   * @param appUser
	   * @param dto
	   * @return
	   */
	  private Collection<String> printCmrFraktbrev(PrintFormObjectDto dto){
		  logger.info("print CMR-fraktbrev ...");
		  Collection<String> list = new ArrayList<String>();
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + dto.getApplicationUser());
			 
		 //check the parent caller for this print (ORDER or TRIP)
		 if(strMgr.isNotNull(dto.getOpd()) && strMgr.isNotNull(dto.getAvd()) ){
			 urlRequestParamsKeys.append("&wsavd=" + dto.getAvd());
			 urlRequestParamsKeys.append("&wsopd=" + dto.getOpd());
			 urlRequestParamsKeys.append("&wssg=" + dto.getSign());
			 urlRequestParamsKeys.append("&wspro=&jbk=J&cm=J");
			 
		 }else if (strMgr.isNotNull(dto.getTur())){
			 urlRequestParamsKeys.append("&wsavd=" + dto.getAvd() + "&wsopd=");
			 urlRequestParamsKeys.append("&wssg=" + dto.getSign());
			 urlRequestParamsKeys.append("&wspro=" + dto.getTur());
			 urlRequestParamsKeys.append("&jbk=J&cm=J");
			 
		 }
		 return this.executeFellesutskriftService(urlRequestParamsKeys);
		 
		  
	  }
	  
	  /**
	   * Printdialog Per Ordre
	   * http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=JOVO&wsavd=75&wsopd=128&wspro=&wssg=JOV&FFAK=J
	   * Printdialog Alle på EN TUR:
	   * http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=JOVO&wsavd=75&wsopd=&wspro=75000019&wssg=JOV&FFAK=J
	   * 
	   * @param dto
	   * @return
	   */
	  private Collection<String> printFFakt(PrintFormObjectDto dto){
		  logger.info("print Ferdigmeld.fakt ...");
		  Collection<String> list = new ArrayList<String>();
		  
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + dto.getApplicationUser());
		  
		  //check the parent caller for this print (ORDER or TRIP)
		  if(strMgr.isNotNull(dto.getOpd()) && strMgr.isNotNull(dto.getAvd()) ){
			 urlRequestParamsKeys.append("&wsavd=" + dto.getAvd());
			 urlRequestParamsKeys.append("&wsopd=" + dto.getOpd());
			 urlRequestParamsKeys.append("&wssg=" + dto.getSign());
			 urlRequestParamsKeys.append("&wspro=");
			 urlRequestParamsKeys.append("&FFAK=J");
			 
				 
		  }else if (strMgr.isNotNull(dto.getTur())){
			 urlRequestParamsKeys.append("&wsavd=" + dto.getAvd() + "&wsopd=");
			 urlRequestParamsKeys.append("&wssg=" + dto.getSign());
			 urlRequestParamsKeys.append("&wspro=" + dto.getTur());
			 urlRequestParamsKeys.append("&FFAK=J");
			 	 
		  }
		  return this.executeFellesutskriftService(urlRequestParamsKeys);
		  
		  
	  }
	  /**
	   * På ordre
	   * http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=JOVO&wsavd=75&wsopd=127&wspro=&wssg=JOV&cm=&aOrd=S
	   * 
	   * @param dto
	   * @return
	   */
	  private Collection<String> printArbeidsOrdre(PrintFormObjectDto dto){
		  logger.info("print Arbeidsordre ...");
		  
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + dto.getApplicationUser());
		  
		  //check the parent caller for this print (ORDER or TRIP)
		  if(strMgr.isNotNull(dto.getOpd()) && strMgr.isNotNull(dto.getAvd()) ){
			 urlRequestParamsKeys.append("&wsavd=" + dto.getAvd());
			 urlRequestParamsKeys.append("&wsopd=" + dto.getOpd());
			 urlRequestParamsKeys.append("&wssg=" + dto.getSign());
			 urlRequestParamsKeys.append("&wspro=");
			 urlRequestParamsKeys.append("&cm=&aOrd=" + dto.getAordDocumentType());
			 
				 
		  }else if (strMgr.isNotNull(dto.getTur())){
			 urlRequestParamsKeys.append("&wsavd=" + dto.getAvd() + "&wsopd=");
			 urlRequestParamsKeys.append("&wssg=" + dto.getSign());
			 urlRequestParamsKeys.append("&wspro=" + dto.getTur());
			 urlRequestParamsKeys.append("&FFAK=J");
			 	 
		  }
			
		  return this.executeFellesutskriftService(urlRequestParamsKeys);
		 
		  
	  }
	  
	  /**
	   * Arbeidsordre Ekstern
	   * http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=JOVO&wsavd=75&wsopd=&wspro=75000019&wssg=JOV&cm=&aOrd=E
   	   * Arbeidsordre Intern
   	   * http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=JOVO&wsavd=75&wsopd=&wspro=75000019&wssg=JOV&cm=&aOrd=I
	   
	   * @param dto
	   * @return
	   */
	  private Collection<String> printArbeidsOrdreFromTrip(PrintFormObjectDto dto){
		  logger.info("print Arbeidsordre ...");
		  Collection<String> list = new ArrayList<String>();
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + dto.getApplicationUser());
		  
		  if (strMgr.isNotNull(dto.getTur())){
			 urlRequestParamsKeys.append("&wsavd=" + dto.getAvd() + "&wsopd=");
			 urlRequestParamsKeys.append("&wssg=" + dto.getSign());
			 urlRequestParamsKeys.append("&wspro=" + dto.getTur());
			 String baseParams = urlRequestParamsKeys.toString();
			 
			 //Arbeidsordre Intern
			 if(strMgr.isNotNull(dto.getAordType())){
				 StringBuffer sb = new StringBuffer(baseParams);
				 sb.append("&cm=&aOrd=" + dto.getAordType());
				 list = this.executeFellesutskriftService(sb);
			 }
			 //Arbeidsordre Ekstern
			 if(strMgr.isNotNull(dto.getAordTypee())){
				 StringBuffer sb = new StringBuffer(baseParams);
				 sb.append("&cm=&aOrd=" + dto.getAordTypee());
				 list = this.executeFellesutskriftService(sb);
			 }
			 	 
		  }
			
		  return list;
		 
		  
	  }
	  
	   
	  /**
	   * 
	   * Godsliste: http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=JOVO&wsavd=75&wsopd=&wspro=75000019&wssg=JOV&GoLi=J
	   * Lasteliste: http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=JOVO&wsavd=75&wsopd=&wspro=75000019&wssg=JOV&LaLi=J
	   * TurKonvolutt: http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=JOVO&wsavd=75&wsopd=&wspro=75000019&wssg=JOV&TuKo=J 
	   * @param dto
	   * @return
	   */
	  
	  private Collection<String> printGodsListLasteListTurKonvolutt(PrintFormObjectDto dto, String typeParameter){
		  logger.info("print GodsLista ...");
		  
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + dto.getApplicationUser());
		  
		  if (strMgr.isNotNull(dto.getTur())){
			 urlRequestParamsKeys.append("&wsavd=" + dto.getAvd() + "&wsopd=");
			 urlRequestParamsKeys.append("&wssg=" + dto.getSign());
			 urlRequestParamsKeys.append("&wspro=" + dto.getTur());
			 if(this.TYPE_GODSL.equals(typeParameter)){
				 urlRequestParamsKeys.append("&GoLi=J");
				 
			 }else if(this.TYPE_LASTL.equals(typeParameter)){
				 urlRequestParamsKeys.append("&LaLi=J");
				 
			 }else if(this.TYPE_TURKONVOLUTT.equals(typeParameter)){
				 urlRequestParamsKeys.append("&TuKo=J");
			 }
			 	 
		  }
			
		  return this.executeFellesutskriftService(urlRequestParamsKeys);
		 
		  
	  }
	  
	  /**
	   * common URL for many print types
	   * @param urlRequestParamsKeys
	   * @return
	   */
	  private Collection<String> executeFellesutskriftService(StringBuffer urlRequestParamsKeys){
		  Collection<String> list = new ArrayList<String>();
		  
		  //-------------------------------------
		  //get BASE URL = RPG-PROGRAM for PRINT
		  //-------------------------------------
		  String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_EXECUTE_FELLESUTSKRIFT_URL;
			
		  logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		  logger.info("URL: " + BASE_URL);
		  logger.info("URL PARAMS: " + urlRequestParamsKeys);
		  //--------------------------------------
		  //EXECUTE the Print (RPG program) here
		  //--------------------------------------
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		  //Debug --> 
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  //END of PRINT here and now
		  logger.info("Method PRINT END");
		  list.add("dummy");
		  
		  return list;
			 
	  }
	
	  
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  private String getRequestUrlKeyParametersForSearchCustomer(String applicationUser, String customerName, String customerNumber){
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + applicationUser);
			
			if(customerNumber!=null && !"".equals(customerNumber)){
				urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokknr=" + customerNumber);
			}
			if(customerName!=null && !"".equals(customerName)){
				urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + customerName);
			}
			return urlRequestParamsKeys.toString();
		}
	
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  
	  @Qualifier 
	  private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	  @Autowired
	  @Required	
	  public void setTransportDispWorkflowSpecificTripService(TransportDispWorkflowSpecificTripService value){this.transportDispWorkflowSpecificTripService = value;}
	  public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){ return this.transportDispWorkflowSpecificTripService; }
	  
	  
	  @Qualifier 
	  private TransportDispChildWindowService transportDispChildWindowService;
	  @Autowired
	  @Required	
	  public void setTransportDispChildWindowService(TransportDispChildWindowService value){this.transportDispChildWindowService = value;}
	  public TransportDispChildWindowService getTransportDispChildWindowService(){ return this.transportDispChildWindowService; }
		
	  
	  @Qualifier 
	  private TransportDispWorkflowSpecificOrderService transportDispWorkflowSpecificOrderService;
	  @Autowired
	  @Required	
	  public void setTransportDispWorkflowSpecificOrderService(TransportDispWorkflowSpecificOrderService value){this.transportDispWorkflowSpecificOrderService = value;}
	  public TransportDispWorkflowSpecificOrderService getTransportDispWorkflowSpecificOrderService(){ return this.transportDispWorkflowSpecificOrderService; }
	
	  @Qualifier ("transportDispWorkflowBudgetService")
	  private TransportDispWorkflowBudgetService transportDispWorkflowBudgetService;
	  @Autowired
	  public void setTransportDispWorkflowBudgetService (TransportDispWorkflowBudgetService value){ this.transportDispWorkflowBudgetService=value; }
	  public TransportDispWorkflowBudgetService getTransportDispWorkflowBudgetService(){return this.transportDispWorkflowBudgetService;}
		
		
		
	  
}
