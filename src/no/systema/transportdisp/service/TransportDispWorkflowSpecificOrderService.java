/**
 * 
 */
package no.systema.transportdisp.service;

import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderMessageNoteContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispFrisokveiGiltighetsListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispCustomerDeliveryAddressContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderArchivedDocsContainer;

import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.validationbackend.JsonTransportDispWorkflowSpecificOrderValidationBackendContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.frisokvei.JsonTransportDispWorkflowSpecificOrderFrisokveiContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.dangerousgoods.JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer;


/**
 * 
 * @author oscardelatorre
 * @date Maj 8, 2015
 * 
 *
 */
public interface TransportDispWorkflowSpecificOrderService {
	public JsonTransportDispWorkflowSpecificOrderContainer getContainer(String utfPayload);
	public JsonTransportDispWorkflowSpecificOrderMessageNoteContainer getMessageNoteContainer(String utfPayload);
	public JsonTransportDispWorkflowSpecificOrderFraktbrevContainer getFraktbrevContainer(String utfPayload);
	public JsonTransportDispCustomerDeliveryAddressContainer getDeliveryAddressContainer(String utfPayload);
	public JsonTransportDispWorkflowSpecificOrderValidationBackendContainer getOrderValidationBackendContainer(String utfPayload);
	public JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer getOrderFraktbrevPdfContainer(String utfPayload);
	public JsonTransportDispWorkflowSpecificOrderArchivedDocsContainer getOrderArchivedDocsContainer(String utfPayload);
	//Order invoice
	public JsonTransportDispWorkflowSpecificOrderInvoiceContainer getOrderInvoiceContainer(String utfPayload);
	public JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer getOrderInvoiceReadyMarkContainer(String utfPayload);
	//Frisokvei
	public JsonTransportDispWorkflowSpecificOrderFrisokveiContainer getOrderFrisokveiContainer(String utfPayload);
	//Order Logging
	public JsonTransportDispWorkflowSpecificOrderLoggingContainer getOrderLoggingContainer(String utfPayload);
	//Dangerous goods
	public JsonTransportDispWorkflowSpecificOrderDangerousGoodsContainer getOrderDangerousGoodsContainer(String utfPayload);
	
}

