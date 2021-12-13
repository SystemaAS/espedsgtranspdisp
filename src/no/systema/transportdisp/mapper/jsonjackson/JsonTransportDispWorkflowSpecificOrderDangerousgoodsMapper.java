/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.logging.log4j.*;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord;

/**
 * @author oscardelatorre
 * @date Jun 2019
 * 
 */
public class JsonTransportDispWorkflowSpecificOrderDangerousgoodsMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = LogManager.getLogger(JsonTransportDispWorkflowSpecificOrderDangerousgoodsMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		if(container!=null && container.getAdrlinelist()!=null){
			for (JsonTransportDispWorkflowSpecificOrderDangerousGoodsRecord record : container.getAdrlinelist()){
				//DEBUG
			}
		}
		return container;
	}

	
}
