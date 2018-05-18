package org.example.module01.web;

import java.util.List;
import java.util.Map;

import org.example.module01.services.IServiceOne;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//TODO mike module02.web 下不能存在同名的ActionOne类问题。
@Controller
@RequestMapping("/modules/module01/ActionOne")
public class ActionOne {

	private Integer abc;
	private IServiceOne serviceOne;
	private JdbcTemplate jdbcTemplate;
	
	public Integer getAbc() {
		return abc;
	}

	public void setAbc(Integer abc) {
		this.abc = abc;
	}

	public IServiceOne getServiceOne() {
		return serviceOne;
	}

	public void setServiceOne(IServiceOne serviceOne) {
		this.serviceOne = serviceOne;
	}

	@RequestMapping("/bbb.do")
	public String greeting(@RequestParam(value = "username", required = false, defaultValue = "World") String username,
			Model model) {
		model.addAttribute("username", username);
		String query = "select id, name from test_user";
		List<Map<String, Object>> rs = jdbcTemplate.queryForList(query);
		System.out.println(rs);
		String insert = "insert into test_user (name) values (?)";
		String args = "tanghulu";
		jdbcTemplate.update(insert, args);
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getName()+ "----------------------------------------" + userDetails.getUsername());
		return "page01";
	}

	@RequestMapping("/eee.do")
	public String custMapping(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		System.out.println("custMapping-" + "page01");
		String args = "tanghulu";
		serviceOne.addUser(args);
		return "page02";
	}

	@RequestMapping("/index.do")
	public String index(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getName()+ "-" + userDetails.getUsername());
		return "index";
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@RequestMapping("/testEurekaClient.do")
	@ResponseBody
	public Object testEurekaClient(String param) {
		
		Object rs = serviceOne.testEurekaClient(param);
		
		return rs;
	}
	
	@RequestMapping("/testRibbonClient.do")
	@ResponseBody
	public Object testRibbonClient(String param) {
		
		Object rs = serviceOne.testRibbonClient(param);
		
		return rs;
	}
	
	
}
