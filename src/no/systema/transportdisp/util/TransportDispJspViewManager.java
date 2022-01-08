package no.systema.transportdisp.util;

import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;

import no.systema.main.model.SystemaWebUser;
import no.systema.transportdisp.controller.TransportDispMainOrderController;

public class TransportDispJspViewManager {
	private static Logger logger = LoggerFactory.getLogger(TransportDispJspViewManager.class.getName());
	
	/**
	 * If there are any adaptations, the Controller will redirect to this JSP-view
	 * @param appUser
	 * @param sourceView
	 * @return
	 */
	public ModelAndView getSuccessView(SystemaWebUser appUser, ModelAndView sourceView){
		ModelAndView successView = sourceView;
		
		if (appUser.getInsid()!=null && TransportDispConstants.ADAPTATION_RAMBERG_INSID.equals(appUser.getInsid().trim())){
			successView = new ModelAndView(sourceView.getViewName() + TransportDispConstants.ADAPTATION_RAMBERG_JSP_CODE);
			logger.warn("View:" + successView.getViewName());
		}
		
		return successView;
	}
}
