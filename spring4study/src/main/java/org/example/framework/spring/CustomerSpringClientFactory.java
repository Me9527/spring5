package org.example.framework.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClientSpecification;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;

public class CustomerSpringClientFactory extends SpringClientFactory {

	public CustomerSpringClientFactory() {
		super();
		
		List<RibbonClientSpecification> configurations = new ArrayList<RibbonClientSpecification>();
		RibbonClientSpecification s1 = new RibbonClientSpecification("org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration", null);
		Class<?>[] c1 = {org.springframework.cloud.netflix.ribbon.eureka.EurekaRibbonClientConfiguration.class};
		RibbonClientSpecification s2 = new RibbonClientSpecification("default.org.springframework.cloud.netflix.ribbon.eureka.RibbonEurekaAutoConfiguration", c1);
		configurations.add(s1); configurations.add(s2);
		this.setConfigurations(configurations);
	}
}
