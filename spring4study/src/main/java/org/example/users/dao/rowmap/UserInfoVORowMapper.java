package org.example.users.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.users.vo.UserInfoVO;
import org.springframework.jdbc.core.RowMapper;

//select ud.id, ud.uid, ud.nickname, ud.address  from p_users_detail ud where uid = ?";

public class UserInfoVORowMapper implements RowMapper<UserInfoVO> {
	public UserInfoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInfoVO u = new UserInfoVO();
		u.setId(rs.getLong(2));
		u.setNickName(rs.getString(3));
		u.setAddress(rs.getString(4));
		
		return u;
	}
}
