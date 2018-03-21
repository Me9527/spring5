package org.example.users.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.users.model.SecurityMetadata;
import org.springframework.jdbc.core.RowMapper;

//SELECT r.id, r.R_TYPE, r.IDENTIFICATION, a.NAME, CONCAT(a.A_TYPE, a.id) attribute "
//		+ "FROM p_resources r left join p_res_attr_map m on r.ID = m.RESOURCE_ID left join p_res_attribute a on a.id = m.ATTRIBUTE_ID "
//		+ "where r.R_TYPE = 'URL' and r.enable = 'Y' and a.enable = 'Y'"
public class SecurityMetadataRowMapper implements RowMapper<SecurityMetadata> {
	public SecurityMetadata mapRow(ResultSet rs, int rowNum) throws SQLException {
		SecurityMetadata t = new SecurityMetadata();
		t.setId(rs.getLong(1));
		t.setResourceType(rs.getString(2));
		t.setResource(rs.getString(3));
		t.setName(rs.getString(4));
		t.setAttribute(rs.getString(5));
		
		return t;
	}
}
