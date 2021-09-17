package no.systema.transportdisp.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.transportdisp.controller.TransportDispMainOrderController;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevRecord;

import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;
/**
 * 
 * @author oscardelatorre
 * @date Maj 12, 2015
 * 
 *
 */
public class TransportDispWorkflowSpecificOrderValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TransportDispWorkflowSpecificOrderValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	private StringManager strMgr = new StringManager();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTransportDispWorkflowSpecificOrderRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTransportDispWorkflowSpecificOrderRecord record = (JsonTransportDispWorkflowSpecificOrderRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heavd", "systema.transportdisp.orders.form.error.null.dept.heavd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "trknfa", "systema.transportdisp.orders.form.error.null.oppdgiv.nr.trknfa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "henaa", "systema.transportdisp.orders.form.error.null.oppdgiv.name.henaa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heot", "systema.transportdisp.orders.form.error.null.oppdtype.heot"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hefr", "systema.transportdisp.orders.form.error.null.frankatur.hefr");  
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "henas", "systema.transportdisp.orders.form.error.null.shipper.name.henas");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heads1", "systema.transportdisp.orders.form.error.null.shipper.address.heads1");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "henak", "systema.transportdisp.orders.form.error.null.consignee.namn.henak");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "headk1", "systema.transportdisp.orders.form.error.null.consignee.address.headk1");
		//References
		if(record!=null){
			if(record.getTrkdak()!=null && !"".equals(record.getTrkdak())){
				if( "S".equals(record.getTrkdak()) ) {
					if("".equals(record.getHerfa())){
						errors.rejectValue("herfa", "systema.transportdisp.orders.form.error.null.orderRef.herfa");
					}
				}else if( "K".equals(record.getTrkdak()) ) {
					if("".equals(record.getHerfk())){
						errors.rejectValue("herfk", "systema.transportdisp.orders.form.error.null.orderRef.herfk");
					}
				}  
			}
		}
		//Invoice parties
		if(record!=null){
			if("".equals(record.getHeknsf()) && "".equals(record.getHenasf()) && "".equals(record.getHeknkf()) && "".equals(record.getHenakf()) ){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heknsf", "systema.transportdisp.orders.form.error.null.invoiceparty.heknsfOrheknkf");
			}
			String INVALID_STRING = "adr.kunde?";
			//logger.info("###########:AVS" + record.getHenasf());
			if(!"".equals(record.getHeknsf()) && (strMgr.isNotNull(record.getHenasf()) &&  record.getHenasf().contains(INVALID_STRING)) ){
				errors.rejectValue("henasf", "systema.transportdisp.orders.form.error.null.invoiceparty.seller.inaktivPart");
			}
			//logger.info("###########:MOTT" + record.getHenakf());
			if(!"".equals(record.getHeknkf()) && (strMgr.isNotNull(record.getHenakf()) &&  record.getHenakf().contains(INVALID_STRING)) ){
				errors.rejectValue("henakf", "systema.transportdisp.orders.form.error.null.invoiceparty.buyer.inaktivPart");
			}
		}
		
		//Booking time
		if(record!=null){
			if("J".equals(record.getOppdragTypeParameters().getKlokkeJN())){
				if("".equals(record.getWsbotm())){
					errors.rejectValue("wsbotm", "systema.transportdisp.orders.form.error.null.bookingTime.wsbotm");
				}
				if("".equals(record.getWsetdk())){
					errors.rejectValue("wsetdk", "systema.transportdisp.orders.form.error.null.date.etdk.wsetdk");
				}
				if("".equals(record.getWsetak())){
					errors.rejectValue("wsetak", "systema.transportdisp.orders.form.error.null.date.etak.wsetak");
				}
			}
			
			//------
			//dates 
			//------
			if(strMgr.isNotNull(record.getHebodt())){
				if(!dateValidator.validateDate(record.getHebodt(), DateValidator.DATE_MASK_ISO)){
					errors.rejectValue("hebodt", "systema.transportdisp.orders.form.error.rule.date.hebodt.invalid"); 
				}
			}
			if(!"".equals(record.getWsbotm())){
				if(!this.dateValidator.validateTimeHHmm(record.getWsbotm())){
					errors.rejectValue("wsbotm", "systema.transportdisp.orders.form.error.rule.time.wsbotm.invalid");
				}
			}

		}
		//Booking time
		if(record!=null){
			if(record.getWsco2()!=null && !"".equals(record.getWsco2())){
				String tmp = record.getWsco2().replace(",", ".");
				double maxValue = Double.parseDouble(tmp);
				if(maxValue>999.999) {
					errors.rejectValue("wsco2", "systema.transportdisp.orders.form.error.rule.time.wsco2.invalid");
				}
			}
		}
		//Dates ETD/ETA
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "wsetdd", "systema.transportdisp.orders.form.error.null.etd.datetime");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "wsetad", "systema.transportdisp.orders.form.error.null.eta.datetime");
		if(record!=null){
			
			//------
			//dates 
			//------
			if(strMgr.isNotNull(record.getWsetdd())){
				if(!dateValidator.validateDate(record.getWsetdd(), DateValidator.DATE_MASK_ISO)){
					errors.rejectValue("wsetdd", "systema.transportdisp.orders.form.error.rule.date.etd.invalid");
				}
			}
			if(strMgr.isNotNull(record.getWsetad())){
				if(!dateValidator.validateDate(record.getWsetad(), DateValidator.DATE_MASK_ISO)){
					errors.rejectValue("wsetad", "systema.transportdisp.orders.form.error.rule.date.eta.invalid");
				}
			}
			
			if(strMgr.isNotNull(record.getWsatdd())){
				if(!dateValidator.validateDate(record.getWsatdd(), DateValidator.DATE_MASK_ISO)){
					errors.rejectValue("wsatdd", "systema.transportdisp.orders.form.error.rule.date.atd.invalid");
				}
			}
			if(strMgr.isNotNull(record.getWsatad())){
				if(!dateValidator.validateDate(record.getWsatad(), DateValidator.DATE_MASK_ISO)){
					errors.rejectValue("wsatad", "systema.transportdisp.orders.form.error.rule.date.ata.invalid");
				}
			}
			
		}
		//Time ETD/ETA
		if(record!=null){
			if(!"".equals(record.getWsetdk())){
				if(!this.dateValidator.validateTimeHHmm(record.getWsetdk())){
					errors.rejectValue("wsetdk", "systema.transportdisp.orders.form.error.rule.time.etdk.invalid");
				}
			}
			if(!"".equals(record.getWsetak())){
				if(!this.dateValidator.validateTimeHHmm(record.getWsetak())){
					errors.rejectValue("wsetak", "systema.transportdisp.orders.form.error.rule.time.etak.invalid");
				}
			}
			if(!"".equals(record.getWsatdk())){
				if(!this.dateValidator.validateTimeHHmm(record.getWsatdk())){
					errors.rejectValue("wsatdk", "systema.transportdisp.orders.form.error.rule.time.atdk.invalid");
				}
			}
			if(!"".equals(record.getWsatak())){
				if(!this.dateValidator.validateTimeHHmm(record.getWsatak())){
					errors.rejectValue("wsatak", "systema.transportdisp.orders.form.error.rule.time.atak.invalid");
				}
			}
			
		}
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//Postal codes (valid real postal codes)
				if(record.getHesdf()!=null && !"".equals(record.getHesdf())){
					if(record.getHelka()!=null && !"".equals(record.getHelka())){
						if(!record.getIsValidPostalCodeHesdf()){
							errors.rejectValue("hesdf", "systema.transportdisp.orders.form.error.rule.postalcode.hesdf.invalid");
						}
					}else{
						errors.rejectValue("hesdf", "systema.transportdisp.orders.form.error.rule.postalcode.hesdf.invalid");
					}
				}
				if(record.getHesdff()!=null && !"".equals(record.getHesdff())){
					if(record.getHelks()!=null && !"".equals(record.getHelks())){
						if(!record.getIsValidPostalCodeHesdff()){
							errors.rejectValue("hesdff", "systema.transportdisp.orders.form.error.rule.postalcode.hesdff.invalid");
						}
					}else{
						errors.rejectValue("hesdff", "systema.transportdisp.orders.form.error.rule.postalcode.hesdff.invalid");
					}
				}
				if(record.getHesdvt()!=null && !"".equals(record.getHesdvt())){
					if(record.getHelkk()!=null && !"".equals(record.getHelkk())){
						if(!record.getIsValidPostalCodeHesdvt()){
							errors.rejectValue("hesdvt", "systema.transportdisp.orders.form.error.rule.postalcode.hesdvt.invalid");
						}
					}else{
						errors.rejectValue("hesdvt", "systema.transportdisp.orders.form.error.rule.postalcode.hesdvt.invalid");
					}
				}
				if(record.getHesdt()!=null && !"".equals(record.getHesdt())){
					if(record.getHetri()!=null && !"".equals(record.getHetri())){
						if(!record.getIsValidPostalCodeHesdt()){
							errors.rejectValue("hesdt", "systema.transportdisp.orders.form.error.rule.postalcode.hesdt.invalid");
						}
					}else{
						errors.rejectValue("hesdt", "systema.transportdisp.orders.form.error.rule.postalcode.hesdt.invalid");
					}
				}
				//From/To
				if(record!=null){
					if("".equals(record.getHelka()) || "".equals(record.getHesdf()) ){
						errors.rejectValue("helka", "systema.transportdisp.orders.form.error.null.from.helkaAndhesdf");
					}
					if("".equals(record.getHetri()) || "".equals(record.getHesdt()) ){
						errors.rejectValue("hetri", "systema.transportdisp.orders.form.error.null.to.hetriAndhesdt");
					}
				}
				//Percent on hevalp
				if(record.getHevalp()!=null && !"".equals(record.getHevalp())){
					Double hevalp = formatter.getDouble(record.getHevalp());
					if(hevalp>=0 && hevalp<1){
						//valid
					}else{
						errors.rejectValue("hevalp", "systema.transportdisp.orders.form.error.rule.percentSats.hevalp.invalid");
					}
				}
				
				//Order line (only on update)
				if(strMgr.isNotNull(record.getHeopd())){
					//There could be item lines that have ONLY a description. These are not subjected to front-end validation.
					//Bring has these requirement (06.May.2019). It is used as an extra description of line nr 1. Any back-end validation should be still active (as a last resort)
					if(strMgr.isNull(record.getLineValidationOffset())){
						logger.info("INFO! LineValidationOffset:" + record.getLineValidationOffset());
						if(!this.isValidOrderLine(record.getFraktbrevRecord())){
							//dummy hent since we lack item lines variables.
							errors.rejectValue("hent", "systema.transportdisp.orders.form.error.rule.mandatory.fields.orderlines.invalid");
						}
					}
				}
				
				
				//TOTALS (only on update)
				if(strMgr.isNotNull(record.getHeopd())){
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hent", "systema.transportdisp.orders.form.error.null.colli.hent");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hevs1", "systema.transportdisp.orders.form.error.null.goodsdescription.hevs1");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hevkt", "systema.transportdisp.orders.form.error.null.weight.hevkt");
				}
				//DUP validation
				if(strMgr.isNotNull(record.getHelks()) &&  strMgr.isNotNull(record.getHesdff()) ){
					if(strMgr.isNotNull(record.getFfavd())){
						//OK
					}else{
						//DEACTIVATED ... errors.rejectValue("ffavd", "systema.transportdisp.orders.form.error.rule.dup.avd.mandatory");
					}
				}
				if(strMgr.isNotNull(record.getHelkk()) &&  strMgr.isNotNull(record.getHesdvt()) ){
					if(strMgr.isNotNull(record.getVfavd())){
						//OK
					}else{
						//DEACTIVATED .. errors.rejectValue("vfavd", "systema.transportdisp.orders.form.error.rule.dup.avd.mandatory");
					}
				}
				
				
			}
		}
	}
		
	/**
	 * 	
	 * @param lineRecord
	 * @return
	 */
	private boolean isValidOrderLine(JsonTransportDispWorkflowSpecificOrderFraktbrevRecord lineRecord){
		boolean isValid =true;
		boolean fvant = true; 
		boolean fvvt = true; 
		boolean fvvkt = true;
		if(lineRecord!=null){
			if( lineRecord.getFvant()==null || "".equals(lineRecord.getFvant())){
				fvant = false;
				isValid = false;
			}
			if( lineRecord.getFvvt()==null || "".equals(lineRecord.getFvvt())){
				fvvt = false;
				isValid = false;
			}
			if( lineRecord.getFvvkt()==null || "".equals(lineRecord.getFvvkt())){
				fvvkt = false;
				isValid = false;
			}
		}
		//check if this is a null line (special case)
		if(!isValid){
			if(!fvant && !fvvt && !fvvkt){
				isValid = true;
			}
		}
		
		return isValid;
	}
	
	

	
}
