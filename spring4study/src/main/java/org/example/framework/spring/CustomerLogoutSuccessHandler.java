package org.example.framework.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.framework.json.JsonResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.alibaba.fastjson.JSON;

public class CustomerLogoutSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler implements
		LogoutSuccessHandler {

	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String accepts = request.getHeader("accept");
		if (accepts != null && accepts.indexOf("application/json") >= 0) {
			JsonResult json = new JsonResult(true, "Logout Success");
			response.getWriter().write(JSON.toJSONString(json));
			return;
		}
		super.handle(request, response, authentication);
	}

}
