package no.systema.transportdisp.validator;

import org.slf4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetRecord;
import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;

/**
 * 
 * @author oscardelatorre
 * @date Oct 17, 2015
 * 
 *
 */
public class TransportDispWorkflowSpecificBudgetValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = LoggerFactory.getLogger(TransportDispWorkflowSpecificBudgetValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	private StringManager strMgr = new StringManager();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTransportDispWorkflowSpecificBudgetRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTransportDispWorkflowSpecificBudgetRecord record = (JsonTransportDispWorkflowSpecificBudgetRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buvk", "systema.transportdisp.budget.form.error.null.gebyr.buvk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bubl", "systema.transportdisp.budget.form.error.null.belop.bubl"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buval", "systema.transportdisp.budget.form.error.null.valuta.buval"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "butype", "systema.transportdisp.budget.form.error.null.type.butype"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bublst", "systema.transportdisp.budget.form.error.null.type.bublst"); 
		
		//Rule errors
		if(record!=null){
			if( record.getHepro()!=null && !"".equals(record.getHepro()) ){
				//the budget record belongs to the Trip-Use Case.
			}else{
				//If the budget record belongs to the Order-Use Case then this fields are mandatory
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buoavd", "systema.transportdisp.budget.form.error.null.type.buoavd"); 
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buopd", "systema.transportdisp.budget.form.error.null.type.buopd"); 
			}
			
			if(strMgr.isNotNull(record.getBufedt())){
				if(!dateValidator.validateDate(record.getBufedt(), DateValidator.DATE_MASK_ISO)){
					errors.rejectValue("bufedt", "systema.transportdisp.budget.form.error.rule.date.invalid"); 
				}
			}
		}	
	}
}
