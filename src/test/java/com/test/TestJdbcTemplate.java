package com.test;

import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class TestJdbcTemplate {

	@Test
	public void add() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///springio?characterEncoding=utf8&useSSL=true");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
	}

}
