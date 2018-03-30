package org.example.framework.spring;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.framework.json.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.alibaba.fastjson.JSON;

public class CustomerAccessDeniedHandlerImpl implements AccessDeniedHandler {

	protected static final Log logger = LogFactory.getLog(AccessDeniedHandlerImpl.class);
//	private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";
	// ~ Instance fields
	// ================================================================================================

	private String errorPage;

	// ~ Methods
	// ========================================================================================================

	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
//		String contentType = request.getHeader("content-type");
//		if(null != contentType && contentType.indexOf("application/json") >= 0) {
//			response.addHeader(DEFAULT_CSRF_HEADER_NAME, "a92251a2-b171-43d4-95bf-3a9c87f7ce91");
//			logger.debug("Request contentType" + contentType);
//		}
		if (!response.isCommitted()) {
			String accepts = request.getHeader("accept");
			if(accepts != null && accepts.indexOf("application/json") >= 0){
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403,
						accessDeniedException);

				// Set the 403 status code.
				response.setStatus(HttpStatus.FORBIDDEN.value());
				JsonResult json = new JsonResult(false, "AccessDenied");
				response.getWriter().write(JSON.toJSONString(json));
				return;
			}
			
			if (errorPage != null) {
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403,
						accessDeniedException);

				// Set the 403 status code.
				response.setStatus(HttpStatus.FORBIDDEN.value());

				// forward to error page.
				RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			}
			else {
				response.sendError(HttpStatus.FORBIDDEN.value(),
					HttpStatus.FORBIDDEN.getReasonPhrase());
			}
		}
	}

	/**
	 * The error page to use. Must begin with a "/" and is interpreted relative to the
	 * current context root.
	 *
	 * @param errorPage the dispatcher path to display
	 *
	 * @throws IllegalArgumentException if the argument doesn't comply with the above
	 * limitations
	 */
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = errorPage;
	}

}
