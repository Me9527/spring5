package org.example.module02.services;

import java.util.List;
import java.util.Map;

public interface IBizTwo {
	public String funcOne(String param);
	public void funcTwo(String param);
	
	public List<Map<String, Object>> getBiz01(String param);
	public void addBiz01(String param);
	
}
