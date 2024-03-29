/**
 * 
 */
package no.systema.external.tvinn.sad.z.maintenance.mapper;

//jackson library
import org.slf4j.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadFellesKodtlbContainer;
import no.systema.external.tvinn.sad.z.maintenance.model.JsonMaintSadFellesKodtlbRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 29, 2018
 * 
 */
public class MaintSadFellesKodtlbMapper {
	private static final Logger logger = LoggerFactory.getLogger(MaintSadFellesKodtlbMapper.class.getName());
	
	public JsonMaintSadFellesKodtlbContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadFellesKodtlbContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadFellesKodtlbContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadFellesKodtlbRecord> list = container.getList();
		for(JsonMaintSadFellesKodtlbRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
