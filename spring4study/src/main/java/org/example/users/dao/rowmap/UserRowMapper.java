package org.example.users.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.users.model.MUser;
import org.springframework.jdbc.core.RowMapper;

//"select ID, LOGINNAME, PASSWORD, ENABLED from P_USERS where LOGINNAME   = ?";

public class UserRowMapper implements RowMapper<MUser> {
	public MUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		MUser t = new MUser();
		t.setId(rs.getLong(1));
		t.setLoginName(rs.getString(2));
		t.setPassword(rs.getString(3));
		t.setEnabled(rs.getBoolean(4));
		
		return t;
	}
}
