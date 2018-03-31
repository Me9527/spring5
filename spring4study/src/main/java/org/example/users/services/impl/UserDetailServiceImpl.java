package org.example.users.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.users.dao.IUserDetailDAO;
import org.example.users.model.MUser;
import org.example.users.vo.UserDetailsVO;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("custUserDetailService")
public class UserDetailServiceImpl implements UserDetailsService, MessageSourceAware{
	
	private String rolePrefix = "ROLE_EXAMPLE";
	private boolean usernameBasedPrimaryKey = true;
	private boolean enableAuthorities = true;
	private boolean enableGroups;
	private final Log logger = LogFactory.getLog(getClass());
	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private IUserDetailDAO userDetailDao;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		List<MUser> users = userDetailDao.loadUsersByUsername(username);
		if (users.size() == 0) {
			logger.debug("Query returned no results for user '" + username + "'");
			throw new UsernameNotFoundException(messages.getMessage("UserDetailDAOImpl.notFound",new Object[] { username }, "Username {0} not found"));
		}
		MUser tmpUer = users.get(0);
//		UserDetails user = new User(tmpUer.getLoginName(), tmpUer.getPassword(), tmpUer.isEnabled(), true, true, true, AuthorityUtils.NO_AUTHORITIES);
		
		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		if (enableAuthorities) 
			dbAuthsSet.addAll(userDetailDao.loadUserAuthorities(tmpUer.getId()));
//		if (enableGroups) 
//			dbAuthsSet.addAll(userDetailDao.loadGroupAuthorities(user.getUsername()));
		
		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);
//		addCustomAuthorities(user.getUsername(), dbAuths);

		if (dbAuths.size() == 0) {
			logger.debug("User '" + username + "' has no authorities and will be treated as 'not found'");
		}

		UserDetails user = new UserDetailsVO(tmpUer.getId(), tmpUer.getLoginName(), tmpUer.getPassword(), dbAuths);
		return user;
	}

//	protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
//		String returnUsername = userFromUserQuery.getUsername();
//		if (!usernameBasedPrimaryKey) {
//			returnUsername = username;
//		}
//
//		return new User(returnUsername, userFromUserQuery.getPassword(),
//				userFromUserQuery.isEnabled(), true, true, true, combinedAuthorities);
//	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		Assert.notNull(messageSource, "messageSource cannot be null");
		this.messages = new MessageSourceAccessor(messageSource);
	}
	
	protected MessageSourceAccessor getMessages() {
		return this.messages;
	}
	
	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	public boolean isUsernameBasedPrimaryKey() {
		return usernameBasedPrimaryKey;
	}

	public void setUsernameBasedPrimaryKey(boolean usernameBasedPrimaryKey) {
		this.usernameBasedPrimaryKey = usernameBasedPrimaryKey;
	}

	public boolean isEnableAuthorities() {
		return enableAuthorities;
	}

	public void setEnableAuthorities(boolean enableAuthorities) {
		this.enableAuthorities = enableAuthorities;
	}

	public boolean isEnableGroups() {
		return enableGroups;
	}

	public void setEnableGroups(boolean enableGroups) {
		this.enableGroups = enableGroups;
	}

	public IUserDetailDAO getUserDetailDao() {
		return userDetailDao;
	}

	public void setUserDetailDao(IUserDetailDAO userDetailDao) {
		this.userDetailDao = userDetailDao;
	}
	
	
}
