package no.systema.transportdisp.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;

//Trans.Disp
import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.mapper.url.request.UrlRequestParameterMapper;

import no.systema.transportdisp.util.manager.CodeDropDownMgr;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord;

import no.systema.transportdisp.validator.TransportDispWorkflowSpecificDangerousGoodsValidator;

import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;

/**
 * Transport disponering Controller - Dangerous goods window
 * 
 * @author oscardelatorre
 * @date March, 2019
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TransportDispWorkflowDangerousGoodsController {
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private static final Logger logger = Logger.getLogger(TransportDispWorkflowDangerousGoodsController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr;
	private StringManager strMgr = new StringManager();
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_dangerousgoods.do", method={RequestMethod.GET} )
	public ModelAndView doInit(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		String avd = request.getParameter("avd"); 
		String opd = request.getParameter("opd"); 
		String linNr = request.getParameter("linNr");
		model.put("avd", avd);
		model.put("opd", opd);
		model.put("linNr", linNr);
		
		logger.info("ACTION: " + action);
		ModelAndView successView = new ModelAndView("transportdisp_workflow_dangerousgoods");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			this.fetchItemLines(appUser, avd, opd, linNr, model, session);
			//aux find floating window
			this.getFindDangerousList(appUser, model);
			model.put("record", recordToValidate);
	    	//
	    	successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    	return successView;
		}
	}	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transportdisp_workflow_dangerousgoods_edit.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditDangerousGoods(@ModelAttribute ("record") JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		String avd = request.getParameter("avd");
		String opd = request.getParameter("opd");
		String linNr = request.getParameter("linNr");
		model.put("avd", avd);
		model.put("opd", opd);
		model.put("linNr", linNr);
		
		logger.info("isModeUpdate:" + recordToValidate.getIsModeUpdate());
		logger.info("linNr:" + linNr);
		logger.info("fflin2:" + recordToValidate.getFflin2());
		
		
		
		//Params
		StringBuffer params = new StringBuffer();
		//params.append("&bnr=" + lineId);
		if(strMgr.isNotNull(avd)){ params.append("&avd=" + avd); }
		if(strMgr.isNotNull(opd)){ params.append("&opd=" + opd); }
		if(strMgr.isNotNull(linNr)){ params.append("&linNr=" + linNr); }
		
		logger.info("ACTION: " + action);
		ModelAndView successView = new ModelAndView("redirect:transportdisp_workflow_dangerousgoods.do?action=doFind" + params.toString() );
		ModelAndView errorView = new ModelAndView("transportdisp_workflow_dangerousgoods");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(TransportDispConstants.ACTION_UPDATE.equals(action)){
				logger.info("[INFO] doUpdate action ...");
				TransportDispWorkflowSpecificDangerousGoodsValidator validator = new TransportDispWorkflowSpecificDangerousGoodsValidator();
				
				//Validate
				//TODO validator.validate(recordToValidate, bindingResult);
				//check for ERRORS
				if(bindingResult.hasErrors()){
					logger.info("[ERROR Validation] Record does not validate)");
			    	//fetch of lines
					this.fetchItemLines(appUser, avd, opd, linNr, model, session);
					model.put("record", recordToValidate);
			    	errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
			    	return errorView;
			    	
				}else{
					
					String MODE = "U";
					if(recordToValidate.getIsModeUpdate()!=null && "true".equalsIgnoreCase(recordToValidate.getIsModeUpdate())){
						//UPDATE
						logger.info("[INFO] UPDATE code: " + recordToValidate.getFfunnr() + " start process... ");
					}else{
						//CREATE NEW
						MODE = "A";
						logger.info("[INFO] CREATE new line in process...");
					}
					//------------------------------------------------
					//STEP [1] Series of back-end-validation steps ...
					//------------------------------------------------
					boolean isValidUpdate = true;
					
					/*
					//We now must validate valid codes in the s.k. giltighetslista ... if any
					JsonTransportDispFrisokveiGiltighetsListContainer giltighetsListContainer = this.getGiltihetsList(appUser, recordToValidate, avd, opd);
					if(giltighetsListContainer.getGyldigliste()!=null && giltighetsListContainer.getGyldigliste().size() > 0 ){
						//[A]there is at least some record. We must check some match with the end-user record...
						JsonTransportDispFrisokveiGiltighetsListContainer singleRecordGiltighetsListContainer = this.validateCodeTowardsGiltihetsList(appUser, recordToValidate, avd, opd);
						if(strMgr.isNotNull(singleRecordGiltighetsListContainer.getErrMsg())){
							//DO SOMETHING
							logger.info("[ERROR] Back-end Error: " + singleRecordGiltighetsListContainer.getErrMsg());
	    					this.populateAspectsOnBackendError(appUser, singleRecordGiltighetsListContainer.getErrMsg(), recordToValidate, model, session);
	    					//fetch item lines
	    					this.fetchItemLines(appUser, avd, opd, model, session);
	    					errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    					return errorView;
						}else{
							isValidUpdate = true;
						}
					}else{
						//There is no giltighets-list to check to. Meaning that any value will be accepted. Unless and error has occurred.
						if(strMgr.isNotNull(giltighetsListContainer.getErrMsg())){
							logger.info("[ERROR] Back-end Error: " + giltighetsListContainer.getErrMsg());
						}else{
							isValidUpdate = true;
						}
					}
					*/
					//----------------------------------------------
					//STEP [2] Go on with the Update (if applicable)
					//----------------------------------------------
					if(isValidUpdate){
						
						JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer container = this.executeUpdateLine(appUser, recordToValidate, MODE, avd, opd, linNr);
						if(container!=null){
		    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
		    					logger.info("[ERROR] Back-end Error: " + container.getErrMsg());
		    					this.populateAspectsOnBackendError(appUser, container.getErrMsg(), recordToValidate, model, session);
		    					//fetch item lines
		    					this.fetchItemLines(appUser, avd, opd, linNr, model, session);
		    					model.put("record", recordToValidate);
		    			    	errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
		    					return errorView;
		    				}else{
		    					//succefully done!
		    		    		logger.info("[INFO] Valid Update -- Record successfully updated, OK ");
		    				}
		    			}

						logger.info("[INFO] UPDATE line 2: " + recordToValidate.getFflin2() + " end process. ");
					}
				}
				
			}else if(TransportDispConstants.ACTION_DELETE.equals(action)){
				String DELETE_MODE = "D";
				JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer container = this.executeUpdateLine(appUser, recordToValidate, DELETE_MODE, avd, opd, linNr);
				if(container!=null){
    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
    					logger.info("[ERROR] Back-end Error: " + container.getErrMsg());
    					this.populateAspectsOnBackendError(appUser, container.getErrMsg(), recordToValidate, model, session);
    					//fetch item lines
    					//this.fetchItemLines(appUser, avd, opd, model, session);
    					
    			    	errorView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
    					return errorView;
    				}else{
    					//succefully done!
    		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
    				}
    			}

				logger.info("[INFO] DELETE line2: " + recordToValidate.getFflin2() + " end process. ");
				
			}
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param appUser
	 * @param model
	 */
	private void getFindDangerousList(SystemaWebUser appUser, Map model){
		
		Collection<JsonTransportDispDangerousGoodsRecord> outputList = new ArrayList<JsonTransportDispDangerousGoodsRecord>();
		String DATATABLE_DANGEROUS_GOODS_LIST = "findDangerousGoodsList";
			
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_DANGEROUS_GOODS_URL;
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		//user=JOVO&unnr=1950=&embg=&indx=&getval=&fullinfo=J
		urlRequestParamsKeys.append("&fullinfo=J"); //always the max. nr of columns (as default)
		
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		
		if(jsonPayload!=null){
			JsonTransportDispDangerousGoodsContainer container = this.transportDispChildWindowService.getDangerousGoodsContainer(jsonPayload);
				if(container!=null){
					outputList = container.getUnNumbers();
				}
		}
	
		model.put(DATATABLE_DANGEROUS_GOODS_LIST, outputList);
	}
	
	/**
	 * 
	 * Update line ( (A)dd, (U)pdate, (D)elete )
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @param mode
	 * @param avd
	 * @param opd
	 * @param linNr
	 * 
	 * @return
	 */
	
	private JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer executeUpdateLine(SystemaWebUser appUser, JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord recordToValidate, String mode,String avd, String opd, String linNr){
		JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer retval = null;
		
		logger.info("[INFO] EXECUTE Update(D/A/U) line nr:" + recordToValidate.getFflin2() + " start process... ");
		String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_DANGEROUSGOODS_URL;
    	//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&avd=" + avd);
		urlRequestParams.append("&opd=" + opd);
		urlRequestParams.append("&mode=" + mode);
		
		if("U".equals(mode) || "A".equals(mode)){
			
			//http://gw.systema.no/sycgip/tjge23RF.pgm?user=JOVO&mode=U&avd=75&opd=113&lin=2&lin2=4&ffunnr=1049&ffembg=&ffindx=
			//&ffklas=1A&ffsedd=2.1&fftres=(%20B/D%20)&fffakt=3&ffantk=1&ffante=15,000&ffenh=KG&ffpoen=45 
			
			urlRequestParams.append("&lin=" + linNr);
			if("A".equals(mode)){
				urlRequestParams.append("&lin2=0");
			}else{
				urlRequestParams.append("&lin2=" + recordToValidate.getFflin2());
			}
			urlRequestParams.append("&ffunnr=" + recordToValidate.getFfunnr());
			urlRequestParams.append("&ffembg=" + recordToValidate.getFfembg());
			urlRequestParams.append("&ffindx=" + recordToValidate.getFfindx());
			urlRequestParams.append("&ffklas=" + recordToValidate.getFfklas());
			urlRequestParams.append("&ffsedd=" + recordToValidate.getFfsedd());
			urlRequestParams.append("&fftres=" + recordToValidate.getFftres());
			
			urlRequestParams.append("&fffakt=" + recordToValidate.getFffakt());
			urlRequestParams.append("&ffantk=" + recordToValidate.getFfantk());
			urlRequestParams.append("&ffante=" + recordToValidate.getFfante());
			urlRequestParams.append("&ffenh=" + recordToValidate.getFfenh());
			urlRequestParams.append("&ffpoen=" + recordToValidate.getFfpoen());
			
			
		}else if("D".equals(mode)){
			urlRequestParams.append("&lin=" + linNr);
			urlRequestParams.append("&lin2=" + recordToValidate.getFflin2());

		}
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	
		if(jsonPayload!=null){
			JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer container = this.transportDispWorkflowSpecificOrderService.getOrderDangerousGoodsContainer(jsonPayload);
			retval = container;
			if(container.getErrMsg()!=null){
				logger.info(container.getErrMsg());
			}
		}
		return retval;
	}
	
	
	
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 * @param model
	 * @param session
	 */
	private void fetchItemLines(SystemaWebUser appUser, String avd, String opd, String linNr, Map model, HttpSession session ){
		final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_DANGEROUSGOODS_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&avd=" + avd); 
		urlRequestParams.append("&opd=" + opd);
		urlRequestParams.append("&lin=" + linNr);
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.debug(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer container = this.transportDispWorkflowSpecificOrderService.getOrderDangerousGoodsContainer(jsonPayload);
			if(container!=null){
				this.setDomainObjectsInView(model, container, session);
			}	
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param errorMessage
	 * @param recordToValidate
	 * @param model
	 * @param parentTrip
	 * @param session
	 */
	private void populateAspectsOnBackendError(SystemaWebUser appUser, String errorMessage, JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord recordToValidate, Map model, HttpSession session ){
		model.put(TransportDispConstants.ASPECT_ERROR_MESSAGE, "Ffunnr:[" + recordToValidate.getFfunnr() +  errorMessage);
		
    	//return objects on validation errors
		model.put("record", recordToValidate);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		/*this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.transportDispDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		this.codeDropDownMgr.populateHtmlDropDownsFromJsonStringGebyrCodes(this.urlCgiProxyService, this.transportDispDropDownListPopulationService, 
				model, appUser);
		*/		
	}
	
	/**
	 * 
	 * @param model
	 */
	private void setDropDownsFromFiles(Map<String, Object> model){
		//model.put(TransportDispConstants.RESOURCE_MODEL_KEY_CURRENCY_CODE_LIST, this.transportDispDropDownListPopulationService.getCurrencyList());
	}
	
	/**
	 * 
	 * @param model
	 * @param container
	 * @param session
	 */
	private void setDomainObjectsInView(Map model, JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer container, HttpSession session){
		Collection<JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord> list = new ArrayList<JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord>();
		//could be two options
		if(container.getAdrlinelist()!=null){
			list = container.getAdrlinelist();
			
		}
		
		//always keep track of the total nr of item lines
		//String nrOfItems = String.valueOf(list.size());
		//container.setTotalNumberOfItemLines(nrOfItems);
		
		logger.info("putting on model...");
		model.put(TransportDispConstants.DOMAIN_CONTAINER, container);
		model.put(TransportDispConstants.DOMAIN_LIST, list);
		//put the objects in session ONLY for the validation errors routine in an UPDATE. Otherwise we do have to retrive th
		//session.setAttribute(session.getId() + TransportDispConstants.DOMAIN_CONTAINER, container);
		//session.setAttribute(session.getId() + TransportDispConstants.DOMAIN_LIST, list);
		
	}
	
	
	/**
	 * 
	 * @param ccyy
	 * @return
	 */
	private String getCentury2digits(String ccyy){
		String retval = ccyy;
		if(ccyy!=null && !"".equals(ccyy)){
		  if(ccyy.length()==4){
			  retval = ccyy.substring(0,2);
		  }
		}
		return retval;
	}
	/**
	 * 
	 * @param ccyy
	 * @return
	 */
	private String getYear2digits(String ccyy){
		String retval = ccyy;
		if(ccyy!=null && !"".equals(ccyy)){
		  if(ccyy.length()==4){
			  retval = ccyy.substring(2);
		  }
		}
		return retval;
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier 
	private TransportDispChildWindowService transportDispChildWindowService;
	@Autowired
	@Required	
	public void setTransportDispChildWindowService(TransportDispChildWindowService value){this.transportDispChildWindowService = value;}
	public TransportDispChildWindowService getTransportDispChildWindowService(){ return this.transportDispChildWindowService; }
	
	
	@Qualifier ("transportDispDropDownListPopulationService")
	private TransportDispDropDownListPopulationService transportDispDropDownListPopulationService;
	@Autowired
	public void setTransportDispDropDownListPopulationService (TransportDispDropDownListPopulationService value){ this.transportDispDropDownListPopulationService=value; }
	public TransportDispDropDownListPopulationService getTransportDispDropDownListPopulationService(){return this.transportDispDropDownListPopulationService;}
	
	@Qualifier ("transportDispWorkflowSpecificOrderService")
	private TransportDispWorkflowSpecificOrderService transportDispWorkflowSpecificOrderService;
	@Autowired
	public void setTransportDispWorkflowSpecificOrderService (TransportDispWorkflowSpecificOrderService value){ this.transportDispWorkflowSpecificOrderService=value; }
	public TransportDispWorkflowSpecificOrderService getTransportDispWorkflowSpecificOrderService(){return this.transportDispWorkflowSpecificOrderService;}
	
	@Qualifier 
	private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	@Autowired
	@Required	
	public void setTransportDispWorkflowSpecificTripService(TransportDispWorkflowSpecificTripService value){this.transportDispWorkflowSpecificTripService = value;}
	public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){ return this.transportDispWorkflowSpecificTripService; }
	
	
}


