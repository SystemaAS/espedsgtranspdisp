package no.systema.transportdisp.util;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import no.systema.main.model.SystemaWebUser;
import no.systema.transportdisp.controller.TransportDispMainOrderController;

public class TransportDispJspViewManager {
	private static Logger logger = Logger.getLogger(TransportDispJspViewManager.class.getName());
	
	/**
	 * If there are any adaptations, the Controller will redirect to this JSP-view
	 * @param appUser
	 * @param sourceView
	 * @return
	 */
	public ModelAndView getSuccessView(SystemaWebUser appUser, ModelAndView sourceView){
		ModelAndView successView = sourceView;
		
		//This hard-coded value is for testing RAMBERG-adaptation during development
		if(appUser.getInsid()!=null && "00000001SYXXX".equals(appUser.getInsid().trim())){
			successView = new ModelAndView(sourceView.getViewName() + TransportDispConstants.ADAPTATION_RAMBERG_JSP_CODE);
			logger.warn("View:" + successView.getViewName());
			
		}else if (appUser.getInsid()!=null && TransportDispConstants.ADAPTATION_RAMBERG_INSID.equals(appUser.getInsid().trim())){
			successView = new ModelAndView(sourceView.getViewName() + TransportDispConstants.ADAPTATION_RAMBERG_JSP_CODE);
			logger.warn("View:" + successView.getViewName());
		}
		
		return successView;
	}
}
