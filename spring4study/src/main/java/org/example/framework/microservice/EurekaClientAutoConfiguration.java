package org.example.framework.microservice;



import static org.springframework.cloud.commons.util.IdUtils.getDefaultInstanceId;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.MalformedURLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.cloud.client.CommonsClientAutoConfiguration;
import org.springframework.cloud.client.actuator.HasFeatures;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.noop.NoopDiscoveryClientAutoConfiguration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.ServiceRegistryAutoConfiguration;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.cloud.netflix.eureka.CloudEurekaClient;
import org.springframework.cloud.netflix.eureka.CloudEurekaInstanceConfig;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.InstanceInfoFactory;
import org.springframework.cloud.netflix.eureka.config.DiscoveryClientOptionalArgsConfiguration;
import org.springframework.cloud.netflix.eureka.metadata.DefaultManagementMetadataProvider;
import org.springframework.cloud.netflix.eureka.metadata.ManagementMetadata;
import org.springframework.cloud.netflix.eureka.metadata.ManagementMetadataProvider;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaAutoServiceRegistration;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaServiceRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.util.StringUtils;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;

/**
 * @author Dave Syer
 * @author Spencer Gibb
 * @author Jon Schneider
 * @author Matt Jenkins
 * @author Ryan Baxter
 * @author Daniel Lavoie
 */
@Configuration
@EnableConfigurationProperties
@ConditionalOnClass(EurekaClientConfig.class)
@Import(DiscoveryClientOptionalArgsConfiguration.class)
@ConditionalOnProperty(value = "eureka.client.enabled", matchIfMissing = true)
@AutoConfigureBefore({ NoopDiscoveryClientAutoConfiguration.class,
		CommonsClientAutoConfiguration.class, ServiceRegistryAutoConfiguration.class })
@AutoConfigureAfter(name = {"org.springframework.cloud.autoconfigure.RefreshAutoConfiguration",
		"org.springframework.cloud.netflix.eureka.EurekaDiscoveryClientConfiguration",
		"org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration"})
public class EurekaClientAutoConfiguration {

	@Autowired(required = false)
	private HealthCheckHandler healthCheckHandler;
	
	@Bean
	public HasFeatures eurekaFeature() {
		return HasFeatures.namedFeature("Eureka Client", EurekaClient.class);
	}

	@Bean
	@ConditionalOnMissingBean(value = EurekaClientConfig.class, search = SearchStrategy.CURRENT)
	public EurekaClientConfigBean eurekaClientConfigBean(ConfigurableEnvironment env) {
		EurekaClientConfigBean client = new EurekaClientConfigBean();
		if ("bootstrap".equals(new RelaxedPropertyResolver(env).getProperty("spring.config.name"))) {
			// We don't register during bootstrap by default, but there will be another
			// chance later.
			client.setRegisterWithEureka(false);
		}
		client.getServiceUrl().put("defaultZone", "http://localhost:1001/eureka/");	//TODO
		return client;
	}

	@Bean
	@ConditionalOnMissingBean
	public ManagementMetadataProvider serviceManagementMetadataProvider() {
		return new DefaultManagementMetadataProvider();
	}

	@Bean
	@ConditionalOnMissingBean(value = EurekaInstanceConfig.class, search = SearchStrategy.CURRENT)
	public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils,
															 ManagementMetadataProvider managementMetadataProvider, ConfigurableEnvironment env) throws MalformedURLException {
		//addPropertySources(env, null);
		
		PropertyResolver environmentPropertyResolver = new RelaxedPropertyResolver(env);
		PropertyResolver eurekaPropertyResolver = new RelaxedPropertyResolver(env, "eureka.instance.");
		String hostname = eurekaPropertyResolver.getProperty("hostname");

		boolean preferIpAddress = Boolean.parseBoolean(eurekaPropertyResolver.getProperty("preferIpAddress"));
		String ipAddress = eurekaPropertyResolver.getProperty("ipAddress");
		boolean isSecurePortEnabled = Boolean.parseBoolean(eurekaPropertyResolver.getProperty("securePortEnabled"));
		String serverContextPath = environmentPropertyResolver.getProperty("server.contextPath", "/");
//		int serverPort = Integer.valueOf(environmentPropertyResolver.getProperty("server.port", environmentPropertyResolver.getProperty("port", "8080")));

		int serverPort = 2101;
		Integer managementPort = environmentPropertyResolver.getProperty("management.port", Integer.class);// nullable. should be wrapped into optional
		String managementContextPath = environmentPropertyResolver.getProperty("management.contextPath");// nullable. should be wrapped into optional
		Integer jmxPort = environmentPropertyResolver.getProperty("com.sun.management.jmxremote.port", Integer.class);//nullable
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

		ManagementMetadata metadata = managementMetadataProvider.get(instance, serverPort,
				serverContextPath, managementContextPath, managementPort);

		if(metadata != null) {
			instance.setStatusPageUrl(metadata.getStatusPageUrl());
			instance.setHealthCheckUrl(metadata.getHealthCheckUrl());
			if(instance.isSecurePortEnabled()) {
				instance.setSecureHealthCheckUrl(metadata.getSecureHealthCheckUrl());
			}
			Map<String, String> metadataMap = instance.getMetadataMap();
			if (metadataMap.get("management.port") == null) {
				metadataMap.put("management.port", String.valueOf(metadata.getManagementPort()));
			}
		}
		instance.setInstanceId("DESKTOP-7T1PPNB:eureka-consumer:2101");
		setupJmxPort(instance, jmxPort);
		return instance;
	}

	private void setupJmxPort(EurekaInstanceConfigBean instance, Integer jmxPort) {
		Map<String, String> metadataMap = instance.getMetadataMap();
		if (metadataMap.get("jmx.port") == null && jmxPort != null) {
			metadataMap.put("jmx.port", String.valueOf(jmxPort));
		}
	}

	@Bean
	public DiscoveryClient discoveryClient(EurekaInstanceConfig config, EurekaClient client) {
		return new EurekaDiscoveryClient(config, client);
	}

	@Bean
	public EurekaServiceRegistry eurekaServiceRegistry() {
		return new EurekaServiceRegistry();
	}

	@Bean
	@ConditionalOnBean(AutoServiceRegistrationProperties.class)
	@ConditionalOnProperty(value = "spring.cloud.service-registry.auto-registration.enabled", matchIfMissing = true)
	public EurekaRegistration eurekaRegistration(EurekaClient eurekaClient, CloudEurekaInstanceConfig instanceConfig, ApplicationInfoManager applicationInfoManager) {
		return EurekaRegistration.builder(instanceConfig)
				.with(applicationInfoManager)
				.with(eurekaClient)
				.with(healthCheckHandler)
				.build();
	}

	@Bean
	@ConditionalOnBean(AutoServiceRegistrationProperties.class)
	@ConditionalOnProperty(value = "spring.cloud.service-registry.auto-registration.enabled", matchIfMissing = true)
	public EurekaAutoServiceRegistration eurekaAutoServiceRegistration(ApplicationContext context, EurekaServiceRegistry registry, EurekaRegistration registration) {
		return new EurekaAutoServiceRegistration(context, registry, registration);
	}

	@Configuration
	@ConditionalOnMissingRefreshScope
	protected static class EurekaClientConfiguration {

		@Autowired
		private ApplicationContext context;

		@Autowired
		private AbstractDiscoveryClientOptionalArgs<?> optionalArgs;

		@Bean(destroyMethod = "shutdown")
		@ConditionalOnMissingBean(value = EurekaClient.class, search = SearchStrategy.CURRENT)
		public EurekaClient eurekaClient(ApplicationInfoManager manager, EurekaClientConfig config) {
			manager.getInfo(); // force initialization
			return new CloudEurekaClient(manager, config, this.optionalArgs,
					this.context);
		}

		@Bean
		@ConditionalOnMissingBean(value = ApplicationInfoManager.class, search = SearchStrategy.CURRENT)
		public ApplicationInfoManager eurekaApplicationInfoManager(
				EurekaInstanceConfig config) {
			InstanceInfo instanceInfo = new InstanceInfoFactory().create(config);
			return new ApplicationInfoManager(config, instanceInfo);
		}
	}

	@Configuration
	@ConditionalOnRefreshScope
	protected static class RefreshableEurekaClientConfiguration {

		@Autowired
		private ApplicationContext context;

		@Autowired
		private AbstractDiscoveryClientOptionalArgs<?> optionalArgs;

		@Bean(destroyMethod = "shutdown")
		@ConditionalOnMissingBean(value = EurekaClient.class, search = SearchStrategy.CURRENT)
		@org.springframework.cloud.context.config.annotation.RefreshScope
		@Lazy
		public EurekaClient eurekaClient(ApplicationInfoManager manager, EurekaClientConfig config, EurekaInstanceConfig instance) {
			manager.getInfo(); // force initialization
			return new CloudEurekaClient(manager, config, this.optionalArgs,
					this.context);
		}

		@Bean
		@ConditionalOnMissingBean(value = ApplicationInfoManager.class, search = SearchStrategy.CURRENT)
		@org.springframework.cloud.context.config.annotation.RefreshScope
		@Lazy
		public ApplicationInfoManager eurekaApplicationInfoManager(EurekaInstanceConfig config) {
			InstanceInfo instanceInfo = new InstanceInfoFactory().create(config);
			return new ApplicationInfoManager(config, instanceInfo);
		}

	}

	@Target({ ElementType.TYPE, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Conditional(OnMissingRefreshScopeCondition.class)
	@interface ConditionalOnMissingRefreshScope {

	}

	@Target({ ElementType.TYPE, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@ConditionalOnClass(RefreshScope.class)
	@ConditionalOnBean(RefreshAutoConfiguration.class)
	@interface ConditionalOnRefreshScope {

	}

	private static class OnMissingRefreshScopeCondition extends AnyNestedCondition {

		public OnMissingRefreshScopeCondition() {
			super(ConfigurationPhase.REGISTER_BEAN);
		}

		@ConditionalOnMissingClass("org.springframework.cloud.context.scope.refresh.RefreshScope")
		static class MissingClass {
		}

		@ConditionalOnMissingBean(RefreshAutoConfiguration.class)
		static class MissingScope {
		}

	}


	
//	
//	
//	public void postProcessEnvironment(ConfigurableEnvironment environment) {
//		addPropertySources(environment, null);
//	}
//	
//	protected void addPropertySources(ConfigurableEnvironment environment,
//			ResourceLoader resourceLoader) {
//		RandomValuePropertySource.addToEnvironment(environment);
//		new Loader(environment, resourceLoader).load();
//	}
//
//	
//
//	private static final String DEFAULT_PROPERTIES = "defaultProperties";
//
//	// Note the order is from least to most specific (last one wins)
////	private static final String DEFAULT_SEARCH_LOCATIONS = "classpath:/,classpath:/config/,file:./,file:./config/";
//	private static final String DEFAULT_SEARCH_LOCATIONS = "classpath:/";
//
//	private static final String DEFAULT_NAMES = "application";
//
//	/**
//	 * The "active profiles" property name.
//	 */
//	public static final String ACTIVE_PROFILES_PROPERTY = "spring.profiles.active";
//
//	/**
//	 * The "includes profiles" property name.
//	 */
//	public static final String INCLUDE_PROFILES_PROPERTY = "spring.profiles.include";
//
//	/**
//	 * The "config name" property name.
//	 */
//	public static final String CONFIG_NAME_PROPERTY = "spring.config.name";
//
//	/**
//	 * The "config location" property name.
//	 */
//	public static final String CONFIG_LOCATION_PROPERTY = "spring.config.location";
//
//	/**
//	 * The default order for the processor.
//	 */
//	public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 10;
//
//	/**
//	 * Name of the application configuration {@link PropertySource}.
//	 */
//	public static final String APPLICATION_CONFIGURATION_PROPERTY_SOURCE_NAME = "applicationConfigurationProperties";
//
//	private final DeferredLog logger = new DeferredLog();
//
//	private String searchLocations;
//
//	private String names;
//
//	private int order = DEFAULT_ORDER;
//	/**
//	 * Loads candidate property sources and configures the active profiles.
//	 */
//	private class Loader {
////		private final DeferredLog logger = new DeferredLog();
//		private final Log logger = this.logger;
//
//		private final ConfigurableEnvironment environment;
//
//		private final ResourceLoader resourceLoader;
//
//		private PropertySourcesLoader propertiesLoader;
//
//		private Queue<Profile> profiles;
//
//		private List<Profile> processedProfiles;
//
//		private boolean activatedProfiles;
//
//		Loader(ConfigurableEnvironment environment, ResourceLoader resourceLoader) {
//			this.environment = environment;
//			this.resourceLoader = resourceLoader == null ? new DefaultResourceLoader()
//					: resourceLoader;
//		}
//
//		public void load() {
//			this.propertiesLoader = new PropertySourcesLoader();
//			this.activatedProfiles = false;
//			this.profiles = Collections.asLifoQueue(new LinkedList<Profile>());
//			this.processedProfiles = new LinkedList<Profile>();
//
//			// Pre-existing active profiles set via Environment.setActiveProfiles()
//			// are additional profiles and config files are allowed to add more if
//			// they want to, so don't call addActiveProfiles() here.
//			Set<Profile> initialActiveProfiles = initializeActiveProfiles();
//			this.profiles.addAll(getUnprocessedActiveProfiles(initialActiveProfiles));
//			if (this.profiles.isEmpty()) {
//				for (String defaultProfileName : this.environment.getDefaultProfiles()) {
//					Profile defaultProfile = new Profile(defaultProfileName, true);
//					if (!this.profiles.contains(defaultProfile)) {
//						this.profiles.add(defaultProfile);
//					}
//				}
//			}
//
//			// The default profile for these purposes is represented as null. We add it
//			// last so that it is first out of the queue (active profiles will then
//			// override any settings in the defaults when the list is reversed later).
//			this.profiles.add(null);
//
//			while (!this.profiles.isEmpty()) {
//				Profile profile = this.profiles.poll();
//				for (String location : getSearchLocations()) {
//					if (!location.endsWith("/")) {
//						// location is a filename already, so don't search for more
//						// filenames
//						load(location, null, profile);
//					}
//					else {
//						for (String name : getSearchNames()) {
//							load(location, name, profile);
//						}
//					}
//				}
//				this.processedProfiles.add(profile);
//			}
//
//			addConfigurationProperties(this.propertiesLoader.getPropertySources());
//		}
//
//		private Set<Profile> initializeActiveProfiles() {
//			if (!this.environment.containsProperty(ACTIVE_PROFILES_PROPERTY)
//					&& !this.environment.containsProperty(INCLUDE_PROFILES_PROPERTY)) {
//				return Collections.emptySet();
//			}
//			// Any pre-existing active profiles set via property sources (e.g. System
//			// properties) take precedence over those added in config files.
//			SpringProfiles springProfiles = bindSpringProfiles(
//					this.environment.getPropertySources());
//			Set<Profile> activeProfiles = new LinkedHashSet<Profile>(
//					springProfiles.getActiveProfiles());
//			activeProfiles.addAll(springProfiles.getIncludeProfiles());
//			maybeActivateProfiles(activeProfiles);
//			return activeProfiles;
//		}
//
//		/**
//		 * Return the active profiles that have not been processed yet. If a profile is
//		 * enabled via both {@link #ACTIVE_PROFILES_PROPERTY} and
//		 * {@link ConfigurableEnvironment#addActiveProfile(String)} it needs to be
//		 * filtered so that the {@link #ACTIVE_PROFILES_PROPERTY} value takes precedence.
//		 * <p>
//		 * Concretely, if the "cloud" profile is enabled via the environment, it will take
//		 * less precedence that any profile set via the {@link #ACTIVE_PROFILES_PROPERTY}.
//		 * @param initialActiveProfiles the profiles that have been enabled via
//		 * {@link #ACTIVE_PROFILES_PROPERTY}
//		 * @return the unprocessed active profiles from the environment to enable
//		 */
//		private List<Profile> getUnprocessedActiveProfiles(
//				Set<Profile> initialActiveProfiles) {
//			List<Profile> unprocessedActiveProfiles = new ArrayList<Profile>();
//			for (String profileName : this.environment.getActiveProfiles()) {
//				Profile profile = new Profile(profileName);
//				if (!initialActiveProfiles.contains(profile)) {
//					unprocessedActiveProfiles.add(profile);
//				}
//			}
//			// Reverse them so the order is the same as from getProfilesForValue()
//			// (last one wins when properties are eventually resolved)
//			Collections.reverse(unprocessedActiveProfiles);
//			return unprocessedActiveProfiles;
//		}
//
//		private void load(String location, String name, Profile profile) {
//			String group = "profile=" + (profile == null ? "" : profile);
//			if (!StringUtils.hasText(name)) {
//				// Try to load directly from the location
//				loadIntoGroup(group, location, profile);
//			}
//			else {
//				// Search for a file with the given name
//				for (String ext : this.propertiesLoader.getAllFileExtensions()) {
//					if (profile != null) {
//						// Try the profile-specific file
//						loadIntoGroup(group, location + name + "-" + profile + "." + ext,
//								null);
//						for (Profile processedProfile : this.processedProfiles) {
//							if (processedProfile != null) {
//								loadIntoGroup(group, location + name + "-"
//										+ processedProfile + "." + ext, profile);
//							}
//						}
//						// Sometimes people put "spring.profiles: dev" in
//						// application-dev.yml (gh-340). Arguably we should try and error
//						// out on that, but we can be kind and load it anyway.
//						loadIntoGroup(group, location + name + "-" + profile + "." + ext,
//								profile);
//					}
//					// Also try the profile-specific section (if any) of the normal file
//					loadIntoGroup(group, location + name + "." + ext, profile);
//				}
//			}
//		}
//
//		private PropertySource<?> loadIntoGroup(String identifier, String location,
//				Profile profile) {
//			try {
//				return doLoadIntoGroup(identifier, location, profile);
//			}
//			catch (Exception ex) {
//				throw new IllegalStateException(
//						"Failed to load property source from location '" + location + "'",
//						ex);
//			}
//		}
//
//		private PropertySource<?> doLoadIntoGroup(String identifier, String location,
//				Profile profile) throws IOException {
//			Resource resource = this.resourceLoader.getResource(location);
//			PropertySource<?> propertySource = null;
//			StringBuilder msg = new StringBuilder();
//			if (resource != null && resource.exists()) {
//				String name = "applicationConfig: [" + location + "]";
//				String group = "applicationConfig: [" + identifier + "]";
//				propertySource = this.propertiesLoader.load(resource, group, name,
//						(profile == null ? null : profile.getName()));
//				if (propertySource != null) {
//					msg.append("Loaded ");
//					handleProfileProperties(propertySource);
//				}
//				else {
//					msg.append("Skipped (empty) ");
//				}
//			}
//			else {
//				msg.append("Skipped ");
//			}
//			msg.append("config file ");
//			msg.append(getResourceDescription(location, resource));
//			if (profile != null) {
//				msg.append(" for profile ").append(profile);
//			}
//			if (resource == null || !resource.exists()) {
//				msg.append(" resource not found");
//				this.logger.trace(msg);
//			}
//			else {
//				this.logger.debug(msg);
//			}
//			return propertySource;
//		}
//
//		private String getResourceDescription(String location, Resource resource) {
//			String resourceDescription = "'" + location + "'";
//			if (resource != null) {
//				try {
//					resourceDescription = String.format("'%s' (%s)",
//							resource.getURI().toASCIIString(), location);
//				}
//				catch (IOException ex) {
//					// Use the location as the description
//				}
//			}
//			return resourceDescription;
//		}
//
//		private void handleProfileProperties(PropertySource<?> propertySource) {
//			SpringProfiles springProfiles = bindSpringProfiles(propertySource);
//			maybeActivateProfiles(springProfiles.getActiveProfiles());
//			addProfiles(springProfiles.getIncludeProfiles());
//		}
//
//		private SpringProfiles bindSpringProfiles(PropertySource<?> propertySource) {
//			MutablePropertySources propertySources = new MutablePropertySources();
//			propertySources.addFirst(propertySource);
//			return bindSpringProfiles(propertySources);
//		}
//
//		private SpringProfiles bindSpringProfiles(PropertySources propertySources) {
//			SpringProfiles springProfiles = new SpringProfiles();
//			RelaxedDataBinder dataBinder = new RelaxedDataBinder(springProfiles,
//					"spring.profiles");
//			dataBinder.bind(new PropertySourcesPropertyValues(propertySources, false));
//			springProfiles.setActive(resolvePlaceholders(springProfiles.getActive()));
//			springProfiles.setInclude(resolvePlaceholders(springProfiles.getInclude()));
//			return springProfiles;
//		}
//
//		private List<String> resolvePlaceholders(List<String> values) {
//			List<String> resolved = new ArrayList<String>();
//			for (String value : values) {
//				resolved.add(this.environment.resolvePlaceholders(value));
//			}
//			return resolved;
//		}
//
//		private void maybeActivateProfiles(Set<Profile> profiles) {
//			if (this.activatedProfiles) {
//				if (!profiles.isEmpty()) {
//					this.logger.debug("Profiles already activated, '" + profiles
//							+ "' will not be applied");
//				}
//				return;
//			}
//			if (!profiles.isEmpty()) {
//				addProfiles(profiles);
//				this.logger.debug("Activated profiles "
//						+ StringUtils.collectionToCommaDelimitedString(profiles));
//				this.activatedProfiles = true;
//				removeUnprocessedDefaultProfiles();
//			}
//		}
//
//		private void removeUnprocessedDefaultProfiles() {
//			for (Iterator<Profile> iterator = this.profiles.iterator(); iterator
//					.hasNext();) {
//				if (iterator.next().isDefaultProfile()) {
//					iterator.remove();
//				}
//			}
//		}
//
//		private void addProfiles(Set<Profile> profiles) {
//			for (Profile profile : profiles) {
//				this.profiles.add(profile);
//				if (!environmentHasActiveProfile(profile.getName())) {
//					// If it's already accepted we assume the order was set
//					// intentionally
//					prependProfile(this.environment, profile);
//				}
//			}
//		}
//
//		private boolean environmentHasActiveProfile(String profile) {
//			for (String activeProfile : this.environment.getActiveProfiles()) {
//				if (activeProfile.equals(profile)) {
//					return true;
//				}
//			}
//			return false;
//		}
//
//		private void prependProfile(ConfigurableEnvironment environment,
//				Profile profile) {
//			Set<String> profiles = new LinkedHashSet<String>();
//			environment.getActiveProfiles(); // ensure they are initialized
//			// But this one should go first (last wins in a property key clash)
//			profiles.add(profile.getName());
//			profiles.addAll(Arrays.asList(environment.getActiveProfiles()));
//			environment.setActiveProfiles(profiles.toArray(new String[profiles.size()]));
//		}
//
//		private Set<String> getSearchLocations() {
//			Set<String> locations = new LinkedHashSet<String>();
//			// User-configured settings take precedence, so we do them first
//			if (this.environment.containsProperty(CONFIG_LOCATION_PROPERTY)) {
//				for (String path : asResolvedSet(
//						this.environment.getProperty(CONFIG_LOCATION_PROPERTY), null)) {
//					if (!path.contains("$")) {
//						path = StringUtils.cleanPath(path);
//						if (!ResourceUtils.isUrl(path)) {
//							path = ResourceUtils.FILE_URL_PREFIX + path;
//						}
//					}
//					locations.add(path);
//				}
//			}
//			locations.addAll(
//					asResolvedSet(EurekaClientAutoConfiguration.this.searchLocations,
//							DEFAULT_SEARCH_LOCATIONS));
//			return locations;
//		}
//
//		private Set<String> getSearchNames() {
//			if (this.environment.containsProperty(CONFIG_NAME_PROPERTY)) {
//				return asResolvedSet(this.environment.getProperty(CONFIG_NAME_PROPERTY),
//						null);
//			}
//			return asResolvedSet(EurekaClientAutoConfiguration.this.names, DEFAULT_NAMES);
//		}
//
//		private Set<String> asResolvedSet(String value, String fallback) {
//			List<String> list = Arrays.asList(StringUtils.trimArrayElements(
//					StringUtils.commaDelimitedListToStringArray(value != null
//							? this.environment.resolvePlaceholders(value) : fallback)));
//			Collections.reverse(list);
//			return new LinkedHashSet<String>(list);
//		}
//
//		private void addConfigurationProperties(MutablePropertySources sources) {
//			List<PropertySource<?>> reorderedSources = new ArrayList<PropertySource<?>>();
//			for (PropertySource<?> item : sources) {
//				reorderedSources.add(item);
//			}
//			addConfigurationProperties(
//					new ConfigurationPropertySources(reorderedSources));
//		}
//
//		private void addConfigurationProperties(
//				ConfigurationPropertySources configurationSources) {
//			MutablePropertySources existingSources = this.environment
//					.getPropertySources();
//			if (existingSources.contains(DEFAULT_PROPERTIES)) {
//				existingSources.addBefore(DEFAULT_PROPERTIES, configurationSources);
//			}
//			else {
//				existingSources.addLast(configurationSources);
//			}
//		}
//
//	}
//
//	
//	private static class Profile {
//
//		private final String name;
//
//		private final boolean defaultProfile;
//
//		Profile(String name) {
//			this(name, false);
//		}
//
//		Profile(String name, boolean defaultProfile) {
//			Assert.notNull(name, "Name must not be null");
//			this.name = name;
//			this.defaultProfile = defaultProfile;
//		}
//
//		public String getName() {
//			return this.name;
//		}
//
//		public boolean isDefaultProfile() {
//			return this.defaultProfile;
//		}
//
//		@Override
//		public String toString() {
//			return this.name;
//		}
//
//		@Override
//		public int hashCode() {
//			return this.name.hashCode();
//		}
//
//		@Override
//		public boolean equals(Object obj) {
//			if (obj == this) {
//				return true;
//			}
//			if (obj == null || obj.getClass() != getClass()) {
//				return false;
//			}
//			return ((Profile) obj).name.equals(this.name);
//		}
//
//	}
//	
//	static final class SpringProfiles {
//
//		private List<String> active = new ArrayList<String>();
//
//		private List<String> include = new ArrayList<String>();
//
//		public List<String> getActive() {
//			return this.active;
//		}
//
//		public void setActive(List<String> active) {
//			this.active = active;
//		}
//
//		public List<String> getInclude() {
//			return this.include;
//		}
//
//		public void setInclude(List<String> include) {
//			this.include = include;
//		}
//
//		Set<Profile> getActiveProfiles() {
//			return asProfileSet(this.active);
//		}
//
//		Set<Profile> getIncludeProfiles() {
//			return asProfileSet(this.include);
//		}
//
//		private Set<Profile> asProfileSet(List<String> profileNames) {
//			List<Profile> profiles = new ArrayList<Profile>();
//			for (String profileName : profileNames) {
//				profiles.add(new Profile(profileName));
//			}
//			Collections.reverse(profiles);
//			return new LinkedHashSet<Profile>(profiles);
//		}
//
//	}
//
//	/**
//	 * Holds the configuration {@link PropertySource}s as they are loaded can relocate
//	 * them once configuration classes have been processed.
//	 */
//	static class ConfigurationPropertySources
//			extends EnumerablePropertySource<Collection<PropertySource<?>>> {
//
//		private final Collection<PropertySource<?>> sources;
//
//		private final String[] names;
//
//		ConfigurationPropertySources(Collection<PropertySource<?>> sources) {
//			super(APPLICATION_CONFIGURATION_PROPERTY_SOURCE_NAME, sources);
//			this.sources = sources;
//			List<String> names = new ArrayList<String>();
//			for (PropertySource<?> source : sources) {
//				if (source instanceof EnumerablePropertySource) {
//					names.addAll(Arrays.asList(
//							((EnumerablePropertySource<?>) source).getPropertyNames()));
//				}
//			}
//			this.names = names.toArray(new String[names.size()]);
//		}
//
//		@Override
//		public Object getProperty(String name) {
//			for (PropertySource<?> propertySource : this.sources) {
//				Object value = propertySource.getProperty(name);
//				if (value != null) {
//					return value;
//				}
//			}
//			return null;
//		}
//
//		public static void finishAndRelocate(MutablePropertySources propertySources) {
//			String name = APPLICATION_CONFIGURATION_PROPERTY_SOURCE_NAME;
//			ConfigurationPropertySources removed = (ConfigurationPropertySources) propertySources
//					.get(name);
//			if (removed != null) {
//				for (PropertySource<?> propertySource : removed.sources) {
//					if (propertySource instanceof EnumerableCompositePropertySource) {
//						EnumerableCompositePropertySource composite = (EnumerableCompositePropertySource) propertySource;
//						for (PropertySource<?> nested : composite.getSource()) {
//							propertySources.addAfter(name, nested);
//							name = nested.getName();
//						}
//					}
//					else {
//						propertySources.addAfter(name, propertySource);
//					}
//				}
//				propertySources.remove(APPLICATION_CONFIGURATION_PROPERTY_SOURCE_NAME);
//			}
//		}
//
//		@Override
//		public String[] getPropertyNames() {
//			return this.names;
//		}
//
//	}
//	@Configuration
//	@ConditionalOnClass(Endpoint.class)
//	protected static class EurekaHealthIndicatorConfiguration {
//		@Bean
//		@ConditionalOnMissingBean
//		public EurekaHealthIndicator eurekaHealthIndicator(EurekaClient eurekaClient,
//														   EurekaInstanceConfig instanceConfig, EurekaClientConfig clientConfig) {
//			return new EurekaHealthIndicator(eurekaClient, instanceConfig, clientConfig);
//		}
//	}
}
