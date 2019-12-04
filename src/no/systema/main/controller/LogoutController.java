package no.systema.main.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//application imports
import no.systema.main.util.AppConstants;
import no.systema.transportdisp.util.manager.Log4jMgr;


@Controller
public class LogoutController {
	private static final Logger logger = Logger.getLogger(LogoutController.class.getName());
	
	@RequestMapping("logout.do")
	public void logout(HttpSession session, HttpServletResponse response){
		
		
		if (session!=null){ 
			
			Log4jMgr log4jMgr = new Log4jMgr();
			log4jMgr.doLogoutLogger();
			
            session.removeAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
            session.invalidate();
            logger.info("Session invalidated..." + Calendar.getInstance().getTime());       
        }
		try{
			//issue a redirect for a fresh start
			response.sendRedirect("/espedsg2/dashboard.do");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	
    
}

