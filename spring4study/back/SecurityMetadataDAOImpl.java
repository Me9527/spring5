package org.example.users.dao.impl;

import java.util.List;

import org.example.users.dao.ISecurityMetadataDAO;
import org.example.users.dao.rowmap.SecurityMetadataRowMapper;
import org.example.users.model.SecurityMetadata;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("securityMetadataDao")
public class SecurityMetadataDAOImpl extends JdbcDaoSupport implements ISecurityMetadataDAO {


	private final String urlRes = "SELECT ID, RESOURCE, PERMISSION, NAME FROM P_PERMISSIONS P WHERE P.ENABLE = 'Y' AND RESOURCE_TYPE = 'URL'";
	private final String serviceRes = "SELECT ID, RESOURCE, PERMISSION, NAME FROM P_PERMISSIONS P WHERE P.ENABLE = 'Y' AND RESOURCE_TYPE = 'Method'";

	"SELECT r.id, r.R_TYPE, r.IDENTIFICATION, a.NAME, CONCAT(a.A_TYPE, a.id) attribute "
	+ "FROM p_resources r left join p_res_attr_map m on r.ID = m.RESOURCE_ID left join p_res_attribute a on a.id = m.ATTRIBUTE_ID "
	+ "where r.R_TYPE = 'URL' and r.enable = 'Y' and a.enable = 'Y'"
	
	public SecurityMetadataDAOImpl() { }

	public List<SecurityMetadata> loadUrlPermission(){
		List<SecurityMetadata> tmp = getJdbcTemplate().query(urlRes, new SecurityMetadataRowMapper());
		return tmp;
	}
	
	public List<SecurityMetadata> loadServicePermission(){
		List<SecurityMetadata> tmp = getJdbcTemplate().query(serviceRes, new SecurityMetadataRowMapper());
		return tmp;
	}

	
}
