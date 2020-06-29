package com.excilys.formation.computerDataBase.service;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

public class ConnectionFactoryTest {
	private ConnectionFactory connectionFactory;

	@Before
	public void setUp() throws Exception {
		connectionFactory = ConnectionFactory.getInstance();
	}

	@Test
	public void test() {
		Connection connection = connectionFactory.getConnection(ConnectionFactory.Mode.TEST);
		assertNotNull(connection);
	}

}
