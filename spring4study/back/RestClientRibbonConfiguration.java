package org.example.framework.microservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.ServerIntrospector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.netflix.client.AbstractLoadBalancerAwareClient;
import com.netflix.client.RetryHandler;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.niws.client.http.RestClient;

/**
 * @author Spencer Gibb
 */
@SuppressWarnings("deprecation")
//@Configuration
//@RibbonAutoConfiguration.ConditionalOnRibbonRestClient
class RestClientRibbonConfiguration {
	@Value("${ribbon.client.name}")
	private String name = "client";

	/**
	 * Create a Netflix {@link RestClient} integrated with Ribbon if none already exists
	 * in the application context. It is not required for Ribbon to work properly and is
	 * therefore created lazily if ever another component requires it.
	 *
	 * @param config             the configuration to use by the underlying Ribbon instance
	 * @param loadBalancer       the load balancer to use by the underlying Ribbon instance
	 * @param serverIntrospector server introspector to use by the underlying Ribbon instance
	 * @param retryHandler       retry handler to use by the underlying Ribbon instance
	 * @return a {@link RestClient} instances backed by Ribbon
	 */
	@Bean
	@Lazy
	@ConditionalOnMissingBean(AbstractLoadBalancerAwareClient.class)
	public RestClient ribbonRestClient(IClientConfig config, ILoadBalancer loadBalancer,
									   ServerIntrospector serverIntrospector, RetryHandler retryHandler) {
		RestClient client = new RibbonClientConfiguration.OverrideRestClient(config, serverIntrospector);
		client.setLoadBalancer(loadBalancer);
		client.setRetryHandler(retryHandler);
		return client;
	}
}
