package org.example.users.dao;

import java.util.List;

import org.example.users.model.MUser;
import org.springframework.security.core.GrantedAuthority;

public interface ISpringSecurityUserDAO {
	List<MUser> loadUsersByUsername(String username);
//	List<GrantedAuthority> loadUserAuthorities(String username);
	List<GrantedAuthority> loadUserAuthorities(Long userId);
	//List<GrantedAuthority> loadGroupAuthorities(String username);
	
	
}
