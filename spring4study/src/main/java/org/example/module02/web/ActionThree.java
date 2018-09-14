package org.example.module02.web;

import java.util.List;
import java.util.Map;

import org.example.module02.services.IBizTwo;
import org.example.users.vo.UserInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@RequestMapping("/modules/module02/")
@Controller
public class ActionThree {

	private Integer abc;
	private IBizTwo bizTwo;
	
	@RequestMapping("/actionTwo.do")
	public String actionTwo(@RequestParam(value = "username", required = false, defaultValue = "钟馗") String username,
			Model model) {
		
		model.addAttribute("username", username);
		System.out.println("page02");
		bizTwo.addBiz01(username);
		@SuppressWarnings("unused")
		List<Map<String, Object>>  rs = bizTwo.getBiz01(username);
		return "page02";
	}

	@RequestMapping
	@ResponseBody
	public UserInfoVO aabbcc(@RequestParam(value = "username", required = false, defaultValue = "钟馗") String username,
			Model model) {
		
		bizTwo.addBiz01(username);
		UserInfoVO user = new UserInfoVO(123L, "山胖子", "象牙山");
		System.out.println("aabbcc");
		return user;
	}

	public void anConttrolMethod() {
		System.out.println("anConttrolMethod");
	}
	
	public Integer getAbc() {
		return abc;
	}

	public void setAbc(Integer abc) {
		this.abc = abc;
	}

	public IBizTwo getBizTwo() {
		return bizTwo;
	}

	public void setBizTwo(IBizTwo bizTwo) {
		this.bizTwo = bizTwo;
	}
}
