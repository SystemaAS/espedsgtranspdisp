/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson.avdsignature;

//jackson library
import org.slf4j.*;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.avdsignature.JsonTransportDispSignatureContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.avdsignature.JsonTransportDispSignatureRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.avdsignature.JsonTransportDispAvdGroupsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.avdsignature.JsonTransportDispAvdGroupsRecord;



import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 10, 2015
 * 
 * 
 */
public class JsonTransportDispSignatureMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = LoggerFactory.getLogger(JsonTransportDispSignatureMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispSignatureContainer getContainer(String utfPayload) throws Exception{
		JsonTransportDispSignatureContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTransportDispSignatureContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTransportDispSignatureRecord> fields = container.getSignaturer();
			for(JsonTransportDispSignatureRecord record : fields){
				
			}
		}
			
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispAvdGroupsContainer getContainerAvdGroups(String utfPayload) throws Exception{
		JsonTransportDispAvdGroupsContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTransportDispAvdGroupsContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			//Collection<JsonTransportDispAvdGroupsRecord> fields = container.getInqAvdGrupp();
			
		}
			
		return container;
	}
	
}
