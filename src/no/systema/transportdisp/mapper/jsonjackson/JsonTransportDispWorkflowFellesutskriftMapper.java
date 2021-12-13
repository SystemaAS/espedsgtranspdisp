/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.logging.log4j.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowFellesutskriftContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowFellesutskriftRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jan 2019
 * 
 */
public class JsonTransportDispWorkflowFellesutskriftMapper {
	private static final Logger logger = LogManager.getLogger(JsonTransportDispWorkflowFellesutskriftMapper.class.getName());
	
	public JsonTransportDispWorkflowFellesutskriftContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowFellesutskriftContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowFellesutskriftContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTransportDispWorkflowFellesutskriftRecord> list = container.getFellesutskrift();
		for(JsonTransportDispWorkflowFellesutskriftRecord record : list){
			//logger.info("wsavd" + record.getWsavd());
		}
		return container;
	}
}
