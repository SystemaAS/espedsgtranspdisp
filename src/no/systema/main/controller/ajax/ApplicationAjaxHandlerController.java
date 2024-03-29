/**
 * 
 */
package no.systema.main.controller.ajax;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import no.systema.main.service.FirmLoginService;
//
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.general.UploadFileToArchiveService;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.JsonFirmLoginContainer;
import no.systema.main.model.jsonjackson.JsonFirmLoginRecord;
import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;
import no.systema.main.model.jsonjackson.general.JsonFileUploadToArchiveValidationContainer;

import no.systema.main.service.general.notisblock.NotisblockService;
import no.systema.main.service.login.SystemaWebLoginService;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockRecord;
import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.util.AppConstants;
import no.systema.main.validator.UserValidator;
/**
 * This Ajax handler is the class handling ajax request general in the whole Application. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Jan 20, 2015
 * 
 */
@Controller
public class ApplicationAjaxHandlerController {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationAjaxHandlerController.class.getName());
	private final String COMPANY_CODE_REQUIRED_FLAG_VALUE = "1";
	
	/**
	 * Use only when changing password in the login form
	 * 
	 * @param request
	 * @return
	 */
	/* OBSOLETE 17.Maj.2018 ? Wait 1 month ...
	@RequestMapping(value = "logonDashboardThin.do", method = RequestMethod.POST)
	public @ResponseBody String logonDashboardThin(MultipartHttpServletRequest request) {
		String result = null;
		logger.info("Inside: logonDashboardThin.do");
		
		String companyCode = null;
    	if(COMPANY_CODE_REQUIRED_FLAG_VALUE.equals(AppConstants.LOGIN_FIRMA_CODE_REQUIRED)){
    		companyCode = this.getCompanyCodeForLogin();
    	}
		
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
    	String user = request.getParameter("user");
    	String pwd = request.getParameter("password");
    	
		String BASE_URL = MainUrlDataStore.SYSTEMA_WEB_LOGIN_URL;
		String urlRequestParamsKeys = this.getRequestUrlKeyParameters(user, pwd,  companyCode);
		logger.info(BASE_URL);
		logger.info(urlRequestParamsKeys);
		
    	try{
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    	//Debug --> 
	    	//System.out.println(jsonPayload);
	    	logger.info(jsonPayload);
	    	//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp " + new StringBuilder(credentailsPwd).reverse().toString() + "carebum");
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp ");
	    	if(jsonPayload!=null){ 
	    		JsonSystemaUserContainer jsonSystemaUserContainer = this.systemaWebLoginService.getSystemaUserContainer(jsonPayload);
	    		//check for errors
	    		if(jsonSystemaUserContainer!=null){
	    			if(jsonSystemaUserContainer.getErrMsg()!=null && !"".equals(jsonSystemaUserContainer.getErrMsg())){
	    				result = null;
	    			}else{
	    				result = user;
	    			}
	    		}
	    	}
    	}catch(Exception e){
    		String msg = "NO CONNECTION:" + e.toString();
    		logger.info("[ERROR Fatal] NO CONNECTION ");
    	}
    	
    	return result;
			    	
	}
	*/
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificNotisblockItemChosenFromGuiElement.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonNotisblockRecord> getSpecificNotisblockItemChosenFromGuiElement
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 
		 final String METHOD = "[DEBUG] getSpecificNotisblockItemChosenFromGuiElement ";
		 logger.info(METHOD + "Inside");
		 Set<JsonNotisblockRecord> result = new HashSet<JsonNotisblockRecord>();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = MainUrlDataStore.SYSTEMA_NOTIS_BLOCK_FETCH_ITEMLINE_URL;
		 String[] fields = elementValue.split("_");
		 //String lineNr = null;
		 String frtli = null;
		 String frtdt = null;
		 if(fields!=null && fields.length==3){
			 //logger.info(METHOD + "FIELDs: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 frtli = fields[1];
			 frtdt = fields[2];
			 //logger.info(applicationUser + "-" + elementValue + " frtli:" + frtli + " frtdt:" + frtdt);
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForNotisblockItem(applicationUser, avd, opd, frtli, frtdt);
			 logger.info(METHOD + "URL: " + BASE_URL);
			 logger.info(METHOD + "PARAMS: " + urlRequestParamsKeys);
			 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now
				 logger.info(METHOD + jsonPayload);
				 JsonNotisblockContainer container = this.notisblockService.getNotisblockListContainer(jsonPayload);
				 if(container!=null){
					 for(JsonNotisblockRecord  record : container.getFreetxtGet()){
						 //TODO record.setDebugPrintlnAjax(BASE_URL + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
				         logger.info("=====>debugFetch: OK output on GUI");
				         //add to list
				         result.add(record);
					 }
				 }
			 }
		 }else{
			 logger.error(METHOD + "[ERROR] on fields[]...null or incorrect length???...");
		 }
		 return result;
	 }	 

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uploadFileToArchive.do", method = RequestMethod.POST)
    public @ResponseBody String uploadFileToArchive(MultipartHttpServletRequest request) {
		final String ERROR_TAG = "[ERROR] ";
		
		logger.info("Inside: uploadFileToArchive");
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
    		String fileName = file.getOriginalFilename();
    		logger.info("FILE NAME:" + fileName);
    		if(fileNameNew!=null && !"".equals(fileNameNew)){ fileName = fileNameNew; }
            //validate file
    		JsonFileUploadToArchiveValidationContainer uploadValidationContainer = this.validateFileUpload(fileName, applicationUser);
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
	 * @param fileName
	 * @param applicationUser
	 * @return
	 */
	private JsonFileUploadToArchiveValidationContainer validateFileUpload(String fileName, String applicationUser){
		JsonFileUploadToArchiveValidationContainer uploadValidationContainer = null;
		//prepare the access CGI with RPG back-end
		String BASE_URL = MainUrlDataStore.SYSTEMA_UPLOAD_FILE_VALIDATION_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&wsdokn=" + fileName;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			uploadValidationContainer = this.uploadFileToArchiveService.getFileUploadValidationContainer(jsonPayload);
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
	private JsonFileUploadToArchiveValidationContainer saveFileUpload(JsonFileUploadToArchiveValidationContainer uploadValidationContainer, String fileName, String applicationUser, String userDate, String userTime){
		//prepare the access CGI with RPG back-end
		String BASE_URL = MainUrlDataStore.SYSTEMA_UPLOAD_FILE_AFTER_VALIDATION_APPROVAL_URL;
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
			uploadValidationContainer = this.uploadFileToArchiveService.getFileUploadValidationContainer(jsonPayload);
			logger.info(uploadValidationContainer.getErrMsg());
		}
		return uploadValidationContainer;
	}
	
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @param frtli
	   * @param frtdt
	   * @return
	   */
	  private String getRequestUrlKeyParametersForNotisblockItem(String applicationUser, String avd, String opd,  String frtli, String frtdt){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(avd!=null && !"".equals(avd) && opd!=null && !"".equals(opd) && frtli!=null && !"".equals(frtli) && frtdt!=null && !"".equals(frtdt)){
			  sb.append( AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd );
			  sb.append( AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd );
			  sb.append( AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dat=" + frtdt );
			  sb.append( AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + frtli );
		  }
		  return sb.toString();
	  }
	  
	  /**
	   * 
	   * @param user
	   * @param pwd
	   * @param companyCode
	   * @return
	   */
	  private String getRequestUrlKeyParameters(String user, String pwd,  String companyCode){
			StringBuffer urlRequestParamsKeys = null;
			//logger.info("XXXXXXXXXX_" + appUser);
			//logger.info("UUUUUUUUUU_" + appUser.getPassword());
			if(user!=null){
				if( (user!=null && !"".equals(user)) && (pwd!=null && !"".equals(pwd))){
					urlRequestParamsKeys = new StringBuffer();
					urlRequestParamsKeys.append("user=" + user);
					urlRequestParamsKeys.append(AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "pwd=" + pwd);
					if(companyCode!=null){
						urlRequestParamsKeys.append(AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "firma=" + companyCode);
					}
				}
			}
			return urlRequestParamsKeys.toString();
		}
	  
	  /**
	   * 
	   * @return
	   */
	  private String getCompanyCodeForLogin(){
			String companyCode = null;
			
			String FIRM_URL = MainUrlDataStore.SYSTEMA_WEB_FIRMLOGIN_URL;
	    	String jsonFirmPayload = this.urlCgiProxyService.getJsonContent(FIRM_URL);
	    	logger.info(FIRM_URL);
	    	logger.info(jsonFirmPayload);
	    	JsonFirmLoginContainer firmContainer = this.firmLoginService.getContainer(jsonFirmPayload);
	    	for(JsonFirmLoginRecord record : firmContainer.getList()){
	    		companyCode = record.getFifirm();
	    	}
	    	logger.info(companyCode);
		
	    	return companyCode;
		}

	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  @Qualifier ("notisblockService")
	  private NotisblockService notisblockService;
	  @Autowired
	  public void setNotisblockService (NotisblockService value){ this.notisblockService=value; }
	  public NotisblockService getNotisblockService(){return this.notisblockService;}
	
	  
	  @Qualifier ("uploadFileToArchiveService")
	  private UploadFileToArchiveService uploadFileToArchiveService;
	  @Autowired
	  public void setUploadFileToArchiveService (UploadFileToArchiveService value){ this.uploadFileToArchiveService=value; }
	  public UploadFileToArchiveService getUploadFileToArchiveService(){return this.uploadFileToArchiveService;}
	
	  
	  	@Qualifier ("firmLoginService")
		private FirmLoginService firmLoginService;
		@Autowired
		@Required
		public void setFirmLoginService (FirmLoginService value){ this.firmLoginService = value; }
		public FirmLoginService getFirmLoginService(){ return this.firmLoginService; }
		
		@Qualifier ("systemaWebLoginService")
		private SystemaWebLoginService systemaWebLoginService;
		@Autowired
		@Required
		public void setSystemaWebLoginService (SystemaWebLoginService value){ this.systemaWebLoginService = value; }
		public SystemaWebLoginService getSystemaWebLoginService(){ return this.systemaWebLoginService; }
		
		
}
