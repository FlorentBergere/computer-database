package com.excilys.formation.computerDataBase.service;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class ConnectionFactory  {
	@Autowired
	private HikariDataSource hikariDataSource;
    private final static Logger log = LoggerFactory.getLogger(ConnectionFactory.class);
    
    
    private ConnectionFactory () {    	
    }
    
    

    
    public Connection getConnection() {
    	Connection result = null;
		try {
			result = hikariDataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }

}
