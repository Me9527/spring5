package org.example.users.services.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.example.users.dao.ISecurityMetadataDAO;
import org.example.users.model.SecurityMetadata;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


public class CustomSecurityUrlMetadataSource extends DefaultFilterInvocationSecurityMetadataSource 
	implements FilterInvocationSecurityMetadataSource, InitializingBean {

    private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    private ISecurityMetadataDAO securityMetadataDao;
    private List<String> excludeUrls;
    
    public void afterPropertiesSet() throws java.lang.Exception{
    	List<SecurityMetadata> secUrls = securityMetadataDao.loadUrlPermission();
    	logger.debug("secUrls = " + secUrls);
    	Map<String, SecurityConfig> attrs = new HashMap<String, SecurityConfig>();
    	for(SecurityMetadata ss: secUrls){
    		SecurityConfig sc = attrs.get(ss.getAttribute());
    		if(null == sc){
    			SecurityConfig configAttribute = new SecurityConfig(ss.getAttribute());
    			attrs.put(ss.getAttribute(), configAttribute);
    		}
    	}
    	for(SecurityMetadata ss: secUrls){
			Collection<ConfigAttribute> c = new HashSet<ConfigAttribute>();
			c.add(attrs.get(ss.getAttribute()));
			AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(ss.getResource());
			requestMap.put(requestMatcher, c);
    	}
    }
    
    public CustomSecurityUrlMetadataSource() {
    	super(null);
    	requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    public Collection<ConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
//        for(String s: excludeUrls){
//        	
//        }
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public ISecurityMetadataDAO getSecurityMetadataDao() {
		return securityMetadataDao;
	}

	public void setSecurityMetadataDao(ISecurityMetadataDAO securityMetadataDao) {
		this.securityMetadataDao = securityMetadataDao;
	}

	
    
}
