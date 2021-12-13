package no.systema.transportdisp.validator;

import org.apache.logging.log4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord;
import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * 
 * @author oscardelatorre
 * @date Mars 2019
 * 
 *
 */
public class TransportDispWorkflowSpecificDangerousGoodsValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = LogManager.getLogger(TransportDispWorkflowSpecificDangerousGoodsValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord record = (JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord)obj;
		
		/*
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fskode", "systema.transportdisp.frisokvei.form.error.null.fskode");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fssok", "systema.transportdisp.frisokvei.form.error.null.fssok"); 
		
		//Rule errors
		if(record!=null){
			/*
			if(!record.TODO().equals(record.TODO())){
			    	//TODO errors.rejectValue("fskode", "systema.transportdisp.frisokvei.form.error.null.fskode");
			}
			 
		}
		 */
	}
}
