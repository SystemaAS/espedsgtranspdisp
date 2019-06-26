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
public class JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer extends JsonAbstractGrandFatherRecord{

	private String user;
	private String avd;
	private String opd;
	
	private String fvant;
	private String fvpakn;
	private String fvvt;
	private String fvvkt;

	private String fbn;
	private String lin;
	private String lin2;
	private String mode;
	
	private String unik;
	private String reff;
	private String fvavd;
	private String fvopd;
	
	private String fvfbnr;
	private String fvlinr;
	private String fmmrk1;
	
	private String fvvol;
	private String fvlm;
	private String fvlm2;
	private String fvlen;
	private String fvbrd;
	private String fvhoy;
	
	private String errMsg;
	private Collection<JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord> adrlinelist;
	private Collection<JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord> awblineUpdate;
	
	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

}
