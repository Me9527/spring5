package org.example.framework.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.framework.json.JsonResult;
import org.example.users.services.IUserService;
import org.example.users.util.UserConstants;
import org.example.users.vo.UserDetailsVO;
import org.example.users.vo.UserVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class CustomerAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	protected final Log logger = LogFactory.getLog(this.getClass());

	private RequestCache requestCache = new HttpSessionRequestCache();
	private IUserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		UserVO userInfo = loadUserInfo(authentication, request);
		String accepts = request.getHeader("accept");
		if(accepts != null && accepts.indexOf("application/json") >= 0){
			clearAuthenticationAttributes(request);
			JsonResult json = new JsonResult(true, "AuthenticationSuccess ", userInfo);
			response.getWriter().write(JSON.toJSONString(json));
			return;
		}
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}
		
		String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl()
				|| (targetUrlParameter != null && StringUtils.hasText(request
						.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}

		clearAuthenticationAttributes(request);

		// Use the DefaultSavedRequest URL
		String targetUrl = savedRequest.getRedirectUrl();
		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
	
	//for AJAX login request, return user information to front page. for example HTML page.
	private UserVO loadUserInfo(Authentication authentication, HttpServletRequest request){
		UserDetailsVO user = (UserDetailsVO)authentication .getPrincipal(); 
		UserVO userInfo = userService.getUserInfoByUid(user.getUid());
		HttpSession session = request.getSession(false);
		if(null != session){
			session.setAttribute(UserConstants.UserInfoInHtml, userInfo);
		}
		return userInfo;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
}
