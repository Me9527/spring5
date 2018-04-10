package org.example.users.dao;

import java.util.List;

import org.example.users.vo.UserInfoVO;


public interface IUserDAO {
	public List<UserInfoVO> getUserInfoByUid(Long uid);	
	
}
