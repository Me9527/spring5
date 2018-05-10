package org.example.framework.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.framework.json.JsonResult;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomerLogoutSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

//	private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;
	private MappingJackson2HttpMessageConverter	mappingJackson2HttpMessageConverter;
	
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String accepts = request.getHeader("accept");
		if (accepts != null && accepts.indexOf("application/json") >= 0) {
			JsonResult json = new JsonResult(true, "Logout Success");
			HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
			mappingJackson2HttpMessageConverter.write(json, MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE), outputMessage);
			return;
		}
		super.handle(request, response, authentication);
	}

	public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
		return mappingJackson2HttpMessageConverter;
	}

	public void setMappingJackson2HttpMessageConverter(
			MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
		this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
	}
	
}
