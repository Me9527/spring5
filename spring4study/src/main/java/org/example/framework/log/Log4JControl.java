package org.example.framework.log;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.web.Log4jWebSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Log4JControl {
	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/log4j.do")
	public String log4j() {
		final LoggerContext ctx  = (LoggerContext)servletContext.getAttribute(Log4jWebSupport.CONTEXT_ATTRIBUTE);
//		final Configuration config = ctx.getConfiguration();
		ctx.reconfigure();
		
		return "Reload Log4j configure is success.";
	}
	
}
