package org.example.users.web;

import javax.servlet.http.HttpSession;

import org.example.framework.json.JsonResult;
import org.example.users.services.IUserService;
import org.example.users.util.UserConstants;
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
	@RequestMapping("/getUserInfo.do")
	public JsonResult getUserInfo(HttpSession session) {
//		UserInfoVO user = (UserInfoVO)session.getAttribute(UserConstants.UserInfoInHttpSession);
		
		Object obj = session.getAttribute(UserConstants.UserInfoInHttpSession);
		if(obj != null && obj instanceof UserInfoVO)
			return new JsonResult(true, "success", obj);
		else
			return new JsonResult(false, "Not login");
		
	}
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
