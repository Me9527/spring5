package org.example.users.services;

import java.util.List;
import java.util.Map;

import org.example.users.model.MUser;
import org.example.users.vo.UserInfoVO;

public interface IUserService {
	public List<MUser> searchUser(Map<String, Object> paramters);
	
	public UserInfoVO getUserInfoByUid(Long uid);
	
}
