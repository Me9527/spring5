package org.example.users.web;

import org.example.users.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserAction {

	private IUserService userService;

	@RequestMapping("/getAllUser.do")
	public String getAllUser(@RequestParam(value = "username", required = false, defaultValue = "钟馗") String username,
			Model model) {

		/*
		 * model.addAttribute("username", username); System.out.println("page02");
		 * bizTwo.addBiz01(username);
		 * 
		 * @SuppressWarnings("unused") List<Map<String, Object>> rs =
		 * bizTwo.getBiz01(username);
		 */
		return "page02";
	}
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
