/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtlikContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Mar 13, 2017
 * 
 *
 */
public interface MaintMainKodtlikService {

	public JsonMaintMainKodtlikContainer getContainer(String utfPayload);
	
}
