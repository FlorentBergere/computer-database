package com.excilys.formation.computerDataBase.service;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.excilys.formation.computerDataBase.configuration.HibernateConfig;
import com.excilys.formation.computerDataBase.configuration.SpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class,HibernateConfig.class})
public class ConnectionFactoryTest {
	@Autowired
	private ConnectionFactory connectionFactory;


	@Test
	public void test() {
		Connection connection = connectionFactory.getConnection();
		assertNotNull(connection);
	}

}
