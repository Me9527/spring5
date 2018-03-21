http://localhost:8000/spring4study/modules/module01/page01.jsp
http://localhost:8080/spring4study/modules/module01/registration.jsp
http://localhost:8080/spring4study/modules/module02/registration.jsp
http://localhost:8000/spring4study/log4j.do

select * from test_user;

truncate table test_user;


This information is kept in the file DispatcherServlet.properties in the package org.springframework.web.servlet

BeanFactoryUtils.beanNamesForTypeIncludingAncestors(getApplicationContext(), Object.class) 


RequestMappingHandlerMapping.initHandlerMethods()

 
createRequestMappingInfo()
ActionOne.java

MvcNamespaceHandler
AnnotationDrivenBeanDefinitionParser

mail, message, dubble, global session, log mgr, cache, security, web service, time task
Remote Fetch URL	https://github.com/Me9527/JChineseTerm.git


SELECT ID, LOGINNAME, PASSWORD, ENABLED, LAST_UPDATE_DATE, LAST_UPDATED_BY, CREATION_DATE, CREATED_BY
FROM users;


create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(50) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);


AuthenticationConfigBuilder.java
	private String createKey() {
		SecureRandom random = new SecureRandom();
		return Long.toString(random.nextLong());
	}

org.springframework.security.access.annotation.SecuredAnnotationSecurityMetadataSource
org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource


SELECT * FROM spring.authorities;

SELECT * FROM spring.authorities for update;
insert into authorities values('zhubajie', 'teller')
commit;

update  spring.authorities set authority = 'supervisor' where LOGINNAME = 'shunwukong';
delete from authorities a WHERE a.LOGINNAME = 'shunwukong';
SET SQL_SAFE_UPDATES = 0;


$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.
	
	
$ redis-cli keys '*' | xargs redis-cli del
$ redis-cli del spring:session:sessions:7e8383a4-082c-4ffe-a4bc-c40fd3363c5e	
	
	
	
	
	