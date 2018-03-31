package org.example.users.dao.impl;

import java.util.List;

import org.example.users.dao.IUserDAO;
import org.example.users.dao.rowmap.UserVORowMapper;
import org.example.users.vo.UserVO;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDAOImpl extends JdbcDaoSupport implements IUserDAO {

	
	private final String queryByUid = "select ud.id, ud.uid, ud.nickname, ud.address  from p_users_detail ud where uid = ?";



	public UserDAOImpl() { }

	public List<UserVO> getUserInfoByUid(Long uid){
		List<UserVO> tmp = getJdbcTemplate().query(queryByUid, new Long[] { uid }, new UserVORowMapper());
		return tmp;
	}
	


}
