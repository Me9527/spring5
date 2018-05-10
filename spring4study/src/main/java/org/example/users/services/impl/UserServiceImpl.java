package org.example.users.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.example.users.dao.IUserDAO;
import org.example.users.model.MUser;
import org.example.users.services.IUserService;
import org.example.users.vo.MenuNode;
import org.example.users.vo.UserInfoVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

// @Component、@Repository、@Service
@Service("userService")
public class UserServiceImpl implements IUserService {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private IUserDAO userDao;
	
	private Logger logger = Logger.getLogger(this.getClass());

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
 
	public IUserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<MUser> searchUser(Map<String, Object> paramters){
		String query = "select id, name from test_user";
		List<Map<String, Object>> rs = jdbcTemplate.queryForList(query);
		// System.out.println(rs);
		return Collections.EMPTY_LIST;
	}

	public UserInfoVO getUserInfoByUid(Long uid){
		List<UserInfoVO> users = userDao.getUserInfoByUid(uid);
		if(null == users || users.size() < 1) {
			throw new RuntimeException("Get UserInfoVO failed!");
		}else {
			UserInfoVO user = users.get(0);
			makeMenu(user);
			return user;
		}
	}
	
	private void makeMenu(UserInfoVO user) {
		List<MenuNode> menus = new ArrayList<MenuNode>();
		user.setMenus(menus);
		
		String url = "http://localhost:8080/spring4study/jsp/modules/module01/registration.jsp";
		int id = 1;
		for(int i=0; i<9; i++) {
			MenuNode t1 = new MenuNode(id++, 0, "菜单-" + i, url, new ArrayList<MenuNode>());
			menus.add(t1);
			for(int k=0; k<3; k++) {
				MenuNode t2 = new MenuNode(id++, 0, "菜单-" + i + "-" + k, url, new ArrayList<MenuNode>());
				//t2.setParent(t1); 
				t2.setParentId(t1.getId());
				t1.getChildren().add(t2);
				if(3==i || 5==i || 6==i || 7==i) {
					for(int m=0; m<5; m++) {
						MenuNode t3 = new MenuNode(id++, 0, "菜单-" + i + "-" + k + "-" + m, url, new ArrayList<MenuNode>());
						//t3.setParent(t2); 
						t3.setParentId(t2.getId());
						t2.getChildren().add(t3);
//						if(m>3) {
//							for(int n=0; n<5; n++) {
//								MenuNode t4 = new MenuNode(id++, 0, "菜单-" + i + "-" + k + "-" + m + "-" + n, url, new ArrayList<MenuNode>());
//								t4.setParent(t3); t4.setParentId(t3.getId());
//								t3.getChildren().add(t4);
////								for(int p=0; p<3; p++) {
////									MenuNode t5 = new MenuNode(id++, 0, "菜单-" + i + "-" + k + "-" + m + "-" + n, url);
////									t5.setParent(t4); t5.setParentId(t4.getId());
////									t4.getChildren().add(t5);
////								}
//							}
//						}
					}
				}
			}
		}
		
	}
}
