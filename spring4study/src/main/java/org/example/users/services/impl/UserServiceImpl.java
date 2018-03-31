package org.example.users.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.example.users.dao.IUserDAO;
import org.example.users.model.MUser;
import org.example.users.services.IUserService;
import org.example.users.vo.UserVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

// @Component、@Repository、@Service
@Service("userService")
public class UserServiceImpl implements IUserService {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private IUserDAO userDao;
	
	private Logger logger = Logger.getLogger(this.getClass());

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
 
	public IUserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<MUser> searchUser(Map<String, Object> paramters){
		String query = "select id, name from test_user";
		List<Map<String, Object>> rs = jdbcTemplate.queryForList(query);
		// System.out.println(rs);
		return Collections.EMPTY_LIST;
	}

	public UserVO getUserInfoByUid(Long uid){
		List<UserVO> user = userDao.getUserInfoByUid(uid);
		return user.get(0);
	}
}
