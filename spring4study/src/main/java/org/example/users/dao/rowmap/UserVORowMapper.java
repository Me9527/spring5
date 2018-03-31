package org.example.users.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.users.vo.UserVO;
import org.springframework.jdbc.core.RowMapper;

//select ud.id, ud.uid, ud.nickname, ud.address  from p_users_detail ud where uid = ?";

public class UserVORowMapper implements RowMapper<UserVO> {
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserVO u = new UserVO();
		u.setId(rs.getLong(2));
		u.setNickName(rs.getString(3));
		u.setNickName(rs.getString(4));
		
		return u;
	}
}
