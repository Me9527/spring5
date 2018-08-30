package org.example.module01.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

@Service
public class ServiceTwoServiceImpl {

	private String namePrefix;
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	RestTemplate restTemplate;

	@Autowired
    private ILoadBalancer ribbonLoadBalancer;
    
	public String getNamePrefix() {
		return namePrefix;
	}


	@HystrixCommand(fallbackMethod = "fallback")
	public String testHystrix(String param, Integer uid){
		Server server = ribbonLoadBalancer.chooseServer("service-provide-01");
		String url = "http://" + server.getHost() + ":" + server.getPort() + "/user/getUser/" + uid;
		logger.info(url);
		String obj = restTemplate.getForObject(url, String.class);
		logger.info(obj);
		return obj;
	}
	
	public String fallback(String param, Integer uid) {
		return "fallbck123";
	}
	

}
