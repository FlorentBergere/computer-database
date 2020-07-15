package com.excilys.formation.computerDataBase.service;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.excilys.formation.computerDataBase.configuration.SpringConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@WebAppConfiguration
public class ConnectionFactoryTest {
	@Autowired
	private ConnectionFactory connectionFactory;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Connection connection = connectionFactory.getConnection();
		assertNotNull(connection);
	}

}
