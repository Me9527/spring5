package org.example.module01.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

import org.apache.log4j.Logger;
import org.example.module01.services.IServiceOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.loadbalancer.ILoadBalancer;

public class ServiceOneServiceImpl implements IServiceOne {

	private JdbcTemplate jdbcTemplate;
	private String namePrefix;
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient eurekaClient;

    private ILoadBalancer ribbonLoadBalancer;
    
	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public String funcOne(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(String param) {
		String insert = "insert into test_user (name) values (?)";
		jdbcTemplate.update(insert, namePrefix + param);
	}

	public Object testEurekaClient(String param) {
		String vipAddress = "service-provide-01";
		InstanceInfo nextServerInfo = null;
		try {
			nextServerInfo = eurekaClient.getNextServerFromEureka(vipAddress, false);
			// String url = "http://" + nextServerInfo.getHost() + ":" +
			// nextServerInfo.getPort() + "/user/getUser/2";
			String url = nextServerInfo.getHomePageUrl() + "/user/getUser/2";
			Object obj = restTemplate.getForObject(url, String.class);
			logger.info(obj);
			return obj;
		} catch (Exception e) {
			logger.info("Cannot get an instance of example service to talk to from eureka");
		}

		return "testEurekaClient";
	}

	public Object testRibbonClient(String param) {

		return "testRibbonClient";
	}

	private void sendRequestToServiceUsingEureka(EurekaClient eurekaClient, String param) {
		// initialize the client
		// this is the vip address for the example service to talk to as defined in
		// conf/sample-eureka-service.properties
		String vipAddress = "service-provide-01";
		InstanceInfo nextServerInfo = null;
		try {
			nextServerInfo = eurekaClient.getNextServerFromEureka(vipAddress, false);
		} catch (Exception e) {
			logger.info("Cannot get an instance of example service to talk to from eureka");
			System.exit(-1);
		}

		logger.info("Found an instance of example service to talk to from eureka: " + nextServerInfo.getVIPAddress()
				+ ":" + nextServerInfo.getPort());

		logger.info("healthCheckUrl: " + nextServerInfo.getHealthCheckUrl());
		logger.info("override: " + nextServerInfo.getOverriddenStatus());

		Socket s = new Socket();
		int serverPort = nextServerInfo.getPort();
		try {
			s.connect(new InetSocketAddress(nextServerInfo.getHostName(), serverPort));
		} catch (IOException e) {
			System.err.println(
					"Could not connect to the server :" + nextServerInfo.getHostName() + " at port " + serverPort);
		} catch (Exception e) {
			System.err.println("Could not connect to the server :" + nextServerInfo.getHostName() + " at port "
					+ serverPort + "due to Exception " + e);
		}
		try {
			String request = "FOO " + new Date();
			logger.info("Connected to server. Sending a sample request: " + request);

			PrintStream out = new PrintStream(s.getOutputStream());
			out.println(request);

			logger.info("Waiting for server response..");
			BufferedReader rd = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = rd.readLine();
			if (str != null) {
				logger.info("Received response from server: " + str);
				logger.info("Exiting the client. Demo over..");
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
