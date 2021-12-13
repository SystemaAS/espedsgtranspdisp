package no.systema.transportdisp.validator;

import org.apache.logging.log4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.validator.DateValidator;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2020
 * 
 *
 */
public class TransportDispWorkflowSpecificTripRAMBERGValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = LogManager.getLogger(TransportDispWorkflowSpecificTripRAMBERGValidator.class.getName());
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTransportDispWorkflowSpecificTripRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTransportDispWorkflowSpecificTripRecord record = (JsonTransportDispWorkflowSpecificTripRecord)obj;
		
		//Check for Mandatory fields first
		if(record!=null){
			if( this.valueExists(record.getTusjn1()) && this.valueExists(record.getTusjn2()) && 
				this.valueExists(record.getWskpma()) &&  this.valueExists(record.getWskptl()) &&
				this.valueExists(record.getWsenid()) &&  this.valueExists(record.getWssjna()) && this.valueExists(record.getWssjmo()) &&  
				this.valueExists(record.getTudt()) && this.valueExists(record.getTutm()) ){
				//do nothing. Validation test passed!
			}else{
				//at least avd or sign must exist IF everything else is empty... 
				errors.rejectValue("tupro", "systema.transportdisp.workflow.trip.error.null.leastNumberOfValues.ramberg"); 
			}
			
			if(record.getTutm()!=null && !"".equals(record.getTutm())){
				if(!this.dateValidator.validateTimeHHmm(record.getTutm())){
					errors.rejectValue("tutm", "systema.transportdisp.workflow.trip.error.rule.time.tutm.invalid");
				}
			}
			if(record.getTutmt()!=null && !"".equals(record.getTutmt())){
				if(!this.dateValidator.validateTimeHHmm(record.getTutmt())){
					errors.rejectValue("tutmt", "systema.transportdisp.workflow.trip.error.rule.time.tutmt.invalid");
				}
			}
		}
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean valueExists(String value){
		boolean retval = false;
		if(value!=null){
			if(!"".equals(value)){
				retval = true;
			}
		}
		
		return retval;
	}
}
