package org.example.users.dao;

import java.util.List;

import org.example.users.vo.UserVO;


public interface IUserDAO {
	public List<UserVO> getUserInfoByUid(Long uid);	
	
}
