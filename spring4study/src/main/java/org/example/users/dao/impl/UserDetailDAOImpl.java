package org.example.users.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.example.users.dao.IUserDetailDAO;
import org.example.users.dao.rowmap.UserRowMapper;
import org.example.users.model.MUser;
import org.springframework.context.ApplicationContextException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository("userDetailDao")
public class UserDetailDAOImpl extends JdbcDaoSupport implements IUserDetailDAO {

//	public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY = "select g.id, g.group_name, ga.authority "
//			+ "from groups g, group_members gm, group_authorities ga "
//			+ "where gm.username = ? " + "and g.id = ga.group_id "
//			+ "and g.id = gm.group_id";
//	private String groupAuthoritiesByUsernameQuery;

	private final String usersByUsernameQuery = "select ID, LOGINNAME, PASSWORD, ENABLED from P_USERS where LOGINNAME = ?";
	
	private final String authoritiesByUsernameQuery = "SELECT up.USER_ID, a.NAME, CONCAT(a.A_TYPE, a.id) attribute "
			+ "FROM P_USER_PERMISSION up left join p_res_attribute a on up.RES_ATTRIBUTE_ID = a.id where up.USER_ID = ?";

	private boolean enableAuthorities = true;
	private boolean enableGroups;


	public UserDetailDAOImpl() { }

	public List<MUser>  loadUsersByUsername(String username) {
		List<MUser> tmp = getJdbcTemplate().query(usersByUsernameQuery, new String[] { username }, new UserRowMapper());
		return tmp;

	}
	
	public List<GrantedAuthority> loadUserAuthorities(Long userId) {
		return getJdbcTemplate().query(this.authoritiesByUsernameQuery,
				new Long[] { userId }, new RowMapper<GrantedAuthority>() {
					@Override
					public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
						String roleName =   rs.getString(3);
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
