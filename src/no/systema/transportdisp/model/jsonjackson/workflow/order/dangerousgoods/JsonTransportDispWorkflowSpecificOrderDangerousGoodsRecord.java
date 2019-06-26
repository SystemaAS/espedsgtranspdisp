/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods;

import java.util.*;
import java.lang.reflect.Field;
import lombok.Data;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeParametersRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingRecord;


/**
 * @author oscardelatorre
 * @date Jun 26, 2019
 * 
 */
@Data
public class JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord {

	private String isModeUpdate;
	
	private String fflin2;
	private String ffunnr;
	private String ffembg;
	private String ffindx;
	
	private String ffklas;
	private String ffsedd;
	private String fftres;
	private String fffakt;
	private String ffantk;
	private String ffante;
	
	private String ffenh;
	private String ffpoen;
	
	

}
