package org.example.module01.dao;

import java.util.List;
import java.util.Map;

import org.example.module01.model.UserInfo;



public interface UserInfoMapper {
    int insert(UserInfo record);

    List<UserInfo> selectData(Map<String,Object> params);
    
	//List<Map<String, Object>> search(Map<String,Object> params);
    
}