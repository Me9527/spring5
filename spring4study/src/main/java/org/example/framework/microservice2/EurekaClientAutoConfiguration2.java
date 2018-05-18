package org.example.framework.microservice2;



import static org.springframework.cloud.commons.util.IdUtils.getDefaultInstanceId;

import java.net.MalformedURLException;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.InstanceInfoFactory;
import org.springframework.cloud.netflix.eureka.config.DiscoveryClientOptionalArgsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.util.StringUtils;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;

@Configuration
@EnableConfigurationProperties
@Import(DiscoveryClientOptionalArgsConfiguration.class)
public class EurekaClientAutoConfiguration2 {


	@Bean(name = "eurekaClient")
//	@ConditionalOnMissingBean(value = EurekaInstanceConfig.class, search = SearchStrategy.CURRENT)
	public DiscoveryClient eurekaInstanceConfigBean(InetUtils inetUtils, ConfigurableEnvironment env) throws MalformedURLException {
		//addPropertySources(env, null);
		
		PropertyResolver environmentPropertyResolver = new RelaxedPropertyResolver(env);
		PropertyResolver eurekaPropertyResolver = new RelaxedPropertyResolver(env, "eureka.instance.");
		String hostname = eurekaPropertyResolver.getProperty("hostname");

		boolean preferIpAddress = Boolean.parseBoolean(eurekaPropertyResolver.getProperty("preferIpAddress"));
		String ipAddress = eurekaPropertyResolver.getProperty("ipAddress");
		boolean isSecurePortEnabled = Boolean.parseBoolean(eurekaPropertyResolver.getProperty("securePortEnabled"));
//		int serverPort = Integer.valueOf(environmentPropertyResolver.getProperty("server.port", environmentPropertyResolver.getProperty("port", "8080")));

		int serverPort = 2101;
		EurekaInstanceConfigBean instance = new EurekaInstanceConfigBean(inetUtils);

		instance.setNonSecurePort(serverPort);
		instance.setInstanceId(getDefaultInstanceId(environmentPropertyResolver));
		instance.setPreferIpAddress(preferIpAddress);
		instance.setSecurePortEnabled(isSecurePortEnabled);
		if (StringUtils.hasText(ipAddress)) {
			instance.setIpAddress(ipAddress);
		}

		if(isSecurePortEnabled) {
			instance.setSecurePort(serverPort);
		}

		if (StringUtils.hasText(hostname)) {
			instance.setHostname(hostname);
		}
		String statusPageUrlPath = eurekaPropertyResolver.getProperty("statusPageUrlPath");
		String healthCheckUrlPath = eurekaPropertyResolver.getProperty("healthCheckUrlPath");

		if (StringUtils.hasText(statusPageUrlPath)) {
			instance.setStatusPageUrlPath(statusPageUrlPath);
		}
		if (StringUtils.hasText(healthCheckUrlPath)) {
			instance.setHealthCheckUrlPath(healthCheckUrlPath);
		}

		instance.setInstanceId("DESKTOP-7T1PPNB:eureka-consumer:2101");
//		setupJmxPort(instance, jmxPort);
		
		InstanceInfo instanceInfo = new InstanceInfoFactory().create(instance);
		ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(instance, instanceInfo);
		
		EurekaClientConfigBean clientConfig = new EurekaClientConfigBean();
		if ("bootstrap".equals(new RelaxedPropertyResolver(env).getProperty("spring.config.name"))) {
			clientConfig.setRegisterWithEureka(false);
		}
		clientConfig.getServiceUrl().put("defaultZone", "http://localhost:1001/eureka/");	
		
		DiscoveryClient eurekaClient = new DiscoveryClient(applicationInfoManager, clientConfig);
		
		return eurekaClient;
	}

	
}
