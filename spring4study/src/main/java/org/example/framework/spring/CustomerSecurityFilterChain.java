package org.example.framework.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CustomerSecurityFilterChain implements SecurityFilterChain {

	private static final Log logger = LogFactory.getLog(DefaultSecurityFilterChain.class);
	private final List<RequestMatcher> requestMatchers = new ArrayList<RequestMatcher>();
	private final List<Filter> filters;

	public CustomerSecurityFilterChain(List<String> requestMatcher, Filter... filters) {
		this(requestMatcher, Arrays.asList(filters));
	}

	public CustomerSecurityFilterChain(List<String> requestMatcher, List<Filter> filters) {
		logger.info("Creating filter chain: " + requestMatcher + ", " + filters);
		for(String str: requestMatcher) {
			RequestMatcher tmp = new AntPathRequestMatcher(str);
			this.requestMatchers.add(tmp);
		}
		this.filters = new ArrayList<Filter>(filters);
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public boolean matches(HttpServletRequest request) {
		for(RequestMatcher rm: requestMatchers) {
			if(rm.matches(request))
				return true;
		}
		
		return false;
	}

//	public RequestMatcher getRequestMatcher() {
//	return requestMatcher;
//}
	
	@Override
	public String toString() {
		return "[ " + requestMatchers + ", " + filters + "]";
	}
}
