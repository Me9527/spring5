package org.example.users.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.example.users.dao.IUserDetailDAO;
import org.springframework.context.ApplicationContextException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository("userDetailDao")
public class UserDetailDAOImpl extends JdbcDaoSupport implements IUserDetailDAO {

	public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY = "select g.id, g.group_name, ga.authority "
			+ "from groups g, group_members gm, group_authorities ga "
			+ "where gm.username = ? " + "and g.id = ga.group_id "
			+ "and g.id = gm.group_id";


	private String usersByUsernameQuery = "select LOGINNAME,PASSWORD,ENABLED from users where LOGINNAME = ?";
	private String authoritiesByUsernameQuery = "select LOGINNAME, AUTHORITY from authorities where LOGINNAME = ?";
//	private String groupAuthoritiesByUsernameQuery;
	private String rolePrefix = "";
	private boolean enableAuthorities = true;
	private boolean enableGroups;


	public UserDetailDAOImpl() { }

	public List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(this.usersByUsernameQuery,
				new String[] { username }, new RowMapper<UserDetails>() {
					@Override
					public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						String username = rs.getString(1);
						String password = rs.getString(2);
						boolean enabled = rs.getBoolean(3);
						return new User(username, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES);
					}
				});
	}
	
	public List<GrantedAuthority> loadUserAuthorities(String username) {
		return getJdbcTemplate().query(this.authoritiesByUsernameQuery,
				new String[] { username }, new RowMapper<GrantedAuthority>() {
					@Override
					public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
						String roleName = UserDetailDAOImpl.this.rolePrefix + rs.getString(2);
						return new SimpleGrantedAuthority(roleName);
					}
				});
	}
	
//	public List<GrantedAuthority> loadGroupAuthorities(String username) {
//		return getJdbcTemplate().query(this.groupAuthoritiesByUsernameQuery,
//				new String[] { username }, new RowMapper<GrantedAuthority>() {
//					@Override
//					public GrantedAuthority mapRow(ResultSet rs, int rowNum)
//							throws SQLException {
//						String roleName = getRolePrefix() + rs.getString(3);
//
//						return new SimpleGrantedAuthority(roleName);
//					}
//				});
//	}


	@Override
	protected void initDao() throws ApplicationContextException {
		Assert.isTrue(this.enableAuthorities || this.enableGroups,
				"Use of either authorities or groups must be enabled");
	}


}
