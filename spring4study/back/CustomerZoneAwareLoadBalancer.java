package org.example.framework.microservice;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ServerListFilter;
import com.netflix.loadbalancer.ServerListUpdater;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;

public class CustomerZoneAwareLoadBalancer<T extends Server> extends ZoneAwareLoadBalancer<T> {
    public CustomerZoneAwareLoadBalancer() {
        super();
    }

    public CustomerZoneAwareLoadBalancer(IClientConfig clientConfig, IRule rule,
                                 IPing ping, ServerList<T> serverList, ServerListFilter<T> filter,
                                 ServerListUpdater serverListUpdater) {
        super(clientConfig, rule, ping, serverList, filter, serverListUpdater);
    }

    public CustomerZoneAwareLoadBalancer(IClientConfig niwsClientConfig) {
        super(niwsClientConfig);
    }
    
}
