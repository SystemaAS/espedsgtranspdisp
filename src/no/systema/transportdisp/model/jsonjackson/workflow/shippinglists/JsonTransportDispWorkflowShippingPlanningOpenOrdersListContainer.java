/**
 * 
 */
package no.systema.transportdisp.model.jsonjackson.workflow.shippinglists;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 11, 2015
 *
 */
public class JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String spedKuKod = null;
	public void setSpedKuKod(String value) {  this.spedKuKod = value; }
	public String getSpedKuKod() { return this.spedKuKod;}
	
	private String spedKuNrs = null;
	public void setSpedKuNrs(String value) {  this.spedKuNrs = value; }
	public String getSpedKuNrs() { return this.spedKuNrs;}
	
	private String spedKuAvd = null;
	public void setSpedKuAvd(String value) {  this.spedKuAvd = value; }
	public String getSpedKuAvd() { return this.spedKuAvd;}
	
	private String spedKuIE = null;
	public void setSpedKuIE(String value) {  this.spedKuIE = value; }
	public String getSpedKuIE() { return this.spedKuIE;}
	
	
	private String wssdf = null;
	public void setWssdf(String value) {  this.wssdf = value; }
	public String getWssdf() { return this.wssdf;}
	
	private String wssdt = null;
	public void setWssdt(String value) {  this.wssdt = value; }
	public String getWssdt() { return this.wssdt;}
	
	private String wssavd = null;
	public void setWssavd(String value) {  this.wssavd = value; }
	public String getWssavd() { return this.wssavd;}
	
	private String stdavd = null;
	public void setStdavd(String value) {  this.stdavd = value; }
	public String getStdavd() { return this.stdavd;}
	
	private String maxWarning = null;
	public void setMaxWarning(String value) {  this.maxWarning = value; }
	public String getMaxWarning() { return this.maxWarning;}

	
	private String error = null;
	public void setError(String value) {  this.error = value; }
	public String getError() { return this.error;}

	private Collection<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord> orderlistlandled;
	public void setOrderlistlandled(Collection<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord> value){ this.orderlistlandled = value; }
	public Collection<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord> getOrderlistlandled(){ return orderlistlandled; }
	
	
}
