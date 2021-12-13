/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.logging.log4j.*;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispTTraceCodesContainer;



/**
 * @author oscardelatorre
 * @date Aug 14, 2015
 * 
 */
public class JsonTransportDispWorkflowSpecificOrderLoggingMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = LogManager.getLogger(JsonTransportDispWorkflowSpecificOrderLoggingMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowSpecificOrderLoggingContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowSpecificOrderLoggingContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTransportDispWorkflowSpecificOrderLoggingContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTransportDispWorkflowSpecificOrderLoggingRecord record : container.getTrackTraceEvents()){
			//DEBUG
		}
		return container;
	}
	
	
	public JsonTransportDispTTraceCodesContainer getEventCodeContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTransportDispTTraceCodesContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTransportDispTTraceCodesContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
	
	
	
	
}
