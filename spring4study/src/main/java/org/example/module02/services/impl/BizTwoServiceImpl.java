package org.example.module02.services.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.example.module02.services.IBizTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

// @Component、@Repository、@Service
@Service("bizTwo")
public class BizTwoServiceImpl implements IBizTwo {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private Logger logger = Logger.getLogger(this.getClass());

    private LoadBalancerClient loadBalancerClient;
    
	public LoadBalancerClient getLoadBalancerClient() {
		return loadBalancerClient;
	}

	public void setLoadBalancerClient(LoadBalancerClient loadBalancerClient) {
		this.loadBalancerClient = loadBalancerClient;
	}

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

	@Override
	public String funcOne(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void funcTwo(String param) {
		// TODO Auto-generated method stub

	}

	@Override
	//@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
	public List<Map<String, Object>> getBiz01(String param) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-provide-01");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/getUser/2";
        System.out.println(url);
        
		String query = "select id, name from test_user";
		List<Map<String, Object>> rs = jdbcTemplate.queryForList(query);
		// System.out.println(rs);
		return rs;
	}

	@Override
	//@Secured("ROLE_TELLER")
	public void addBiz01(String param) {
		String insert = "insert into test_user (name) values (?)";
		logger.info(param);
		jdbcTemplate.update(insert, param);
	}
}
