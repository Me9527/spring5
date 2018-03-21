package org.example.module01.services.impl;

import org.example.module01.services.IServiceOne;
import org.springframework.jdbc.core.JdbcTemplate;

public class ServiceOneServiceImpl implements IServiceOne {

	private JdbcTemplate jdbcTemplate;
	private String namePrefix;

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

}
