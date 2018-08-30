package org.example.module01.services;

public interface IServiceOne {
	public String funcOne(String param);
	
	public void addUser(String param);
//	public void loadUser(String param);
	
	public Object testEurekaClient(String param);
	public Object testRibbonClient(String param);
	
	public Object testHystrix(String param, Integer uid);
}
