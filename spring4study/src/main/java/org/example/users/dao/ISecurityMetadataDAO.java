package org.example.users.dao;

import java.util.List;

import org.example.users.model.SecurityMetadata;

public interface ISecurityMetadataDAO {
	List<SecurityMetadata> loadUrlPermission();
	List<SecurityMetadata> loadServicePermission();
	
	
}
