package org.example.framework.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.framework.json.JsonResult;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

public class CustomerLogoutSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

	private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;
	
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String accepts = request.getHeader("accept");
		if (accepts != null && accepts.indexOf("application/json") >= 0) {
			JsonResult json = new JsonResult(true, "Logout Success");
			HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
			fastJsonHttpMessageConverter.write(json, MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE), outputMessage);
			return;
		}
		super.handle(request, response, authentication);
	}

	public FastJsonHttpMessageConverter getFastJsonHttpMessageConverter() {
		return fastJsonHttpMessageConverter;
	}

	public void setFastJsonHttpMessageConverter(
			FastJsonHttpMessageConverter fastJsonHttpMessageConverter) {
		this.fastJsonHttpMessageConverter = fastJsonHttpMessageConverter;
	}
	
}
