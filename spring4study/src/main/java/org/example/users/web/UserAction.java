package org.example.users.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.example.framework.json.JsonResult;
import org.example.users.services.IUserService;
import org.example.users.util.UserConstants;
import org.example.users.vo.MenuNode;
import org.example.users.vo.UserInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserAction {

	private IUserService userService;

	@RequestMapping("/getAllUser.do")
	public String getAllUser(@RequestParam(value = "username", required = false, defaultValue = "钟馗") String username,
			Model model) {

		return "page02";
	}
	
	@ResponseBody
//	@RequestMapping("/getUserInfo.do")	//为什么在  MappingJackson2HttpMessageConverter 中配置不起作用。
	@RequestMapping(value="/getUserInfo.do",produces = "application/json; charset=utf-8")
	public JsonResult getUserInfo(HttpSession session) {
//		UserInfoVO user = (UserInfoVO)session.getAttribute(UserConstants.UserInfoInHttpSession);
		
		Object obj = session.getAttribute(UserConstants.UserInfoInHttpSession);
		if(obj != null && obj instanceof UserInfoVO)
			return new JsonResult(true, "success", obj);
		else
			return new JsonResult(false, "Not login");
		
	}
	
	@ResponseBody
	@RequestMapping("/getUserInfoDemoData.do")
	public JsonResult getUserInfoDemoData(HttpSession session) {
		
		List<UserInfoVO> obj = makeDemoData();
		return new JsonResult(true, "success", obj);
		
	}
	
	private List<UserInfoVO> makeDemoData(){
		List<UserInfoVO> datas = new ArrayList<UserInfoVO>();
		Long id = 1L;
		List<String> userRole = new ArrayList<String>();
		userRole.add("打酱油的");	userRole.add("孙悟空");	userRole.add("猪八戒");
		List<MenuNode> menus = new ArrayList<MenuNode>();
		MenuNode m1 = new MenuNode(88, 0, "菜单一", "/abc/efg.do");
		MenuNode m2 = new MenuNode(89, 0, "菜单二", "/abc/efg.do");
		menus.add(m1); menus.add(m2);
		
		MenuNode m21 = new MenuNode(90, 89, "菜单二一", "/abc/efg.do");
		MenuNode m22 = new MenuNode(91, 89, "菜单二二", "/abc/efg.do");
		List<MenuNode> children = new ArrayList<MenuNode>();
		children.add(m21); children.add(m22);
		m2.setChildren(children);
		
		for(int i=0; i<100; i++) {
			UserInfoVO tmp = new UserInfoVO();
			datas.add(tmp);
			
			tmp.setId(id++);
			tmp.setNickName("测试用户-" + i);
			tmp.setSex("男");
			tmp.setMobilePhone("13866668888");
			tmp.setTelphone("010-66668888");
			tmp.setQq("68005800");
			tmp.setIm("微信没有");
			tmp.setCompany("全日制皮包科技股份公司");
			tmp.setDepartment("领导部门");
			tmp.setPosition("大领导");
			tmp.setAddress("北京是西城区灵境胡同甲一号");
			tmp.setBirthday(new Date());
			tmp.setUserRole(userRole);
			tmp.setMenus(menus);
		}
		
		
		
		return datas;
	}
	
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
