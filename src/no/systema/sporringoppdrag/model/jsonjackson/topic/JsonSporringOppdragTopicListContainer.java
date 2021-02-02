/**
 * 
 */
package no.systema.sporringoppdrag.model.jsonjackson.topic;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Feb 4, 2015
 *
 */
@Data
public class JsonSporringOppdragTopicListContainer {
	private String user = null;
	private String avd = null;
	private String sign = null;
	private String knavn = null;
	private String visFaktSum = null;
	private String errMsg = null;
	
	private String custFld1Name = null;
	private String custFld2Name = null;
	private String custFld3Name = null;
	private String custFld4Name = null;
	private String custFld5Name = null;
	
	
	private Collection<JsonSporringOppdragTopicListRecord> qryoppdrag;
	public void setQryoppdrag(Collection<JsonSporringOppdragTopicListRecord> value){ this.qryoppdrag = value; }
	public Collection<JsonSporringOppdragTopicListRecord> getQryoppdrag(){ return qryoppdrag; }
	
}
