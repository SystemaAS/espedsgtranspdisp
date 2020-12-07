package no.systema.transportdisp.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispMerkMottContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingRecordEdit;

import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;

/**
 * 
 * @author oscardelatorre
 * @date Dec 2020
 * 
 *
 */
public class TransportDispWorkflowSpecificMerkMottValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TransportDispWorkflowSpecificMerkMottValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	private StringManager strMgr = new StringManager();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTransportDispMerkMottContainer.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTransportDispMerkMottContainer record = (JsonTransportDispMerkMottContainer)obj;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "todo", "systema.transportdisp.ttrace.form.error.null.todo");
		
		//Rule errors
		if(record!=null){
			//------
			//dates 
			//------
			logger.warn(record.getWsDTMO());
			if(strMgr.isNotNull(record.getWsDTMO())){
				if(!dateValidator.validateDate(record.getWsDTMO(), DateValidator.DATE_MASK_NO)){
					errors.rejectValue("wsDTMO", "systema.transportdisp.orders.merkmott.form.error.rule.wsdtmo.invalid");
				}
			}
			if(strMgr.isNotNull(record.getWsDTG())){
				if(!dateValidator.validateDate(record.getWsDTG(), DateValidator.DATE_MASK_NO)){
					errors.rejectValue("wsDTG", "systema.transportdisp.orders.merkmott.form.error.rule.wsdtg.invalid");
				}
			}
			if(!"".equals(record.getWsKLMO())){
				if("".equals(record.getWsDTMO())){
					errors.rejectValue("wsKLMO", "systema.transportdisp.orders.merkmott.form.error.rule.wsklmo.orphan.invalid");
				}else{
					if(!this.dateValidator.validateTimeHHmm(record.getWsKLMO())){
						errors.rejectValue("wsKLMO", "systema.transportdisp.orders.merkmott.form.error.rule.wsklmo.invalid");
					}
				}
			}
			
			
		}	
	}
}
