package com.excilys.formation.computerDataBase.service;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory  {
	private static HikariDataSource hikariConfig;
    private static ConnectionFactory connectionFactory = null;
    private final static Logger log = LoggerFactory.getLogger(ConnectionFactory.class);
    
    
    private ConnectionFactory () {
    	try {
    		HikariConfig config = new HikariConfig("/hikariConfig.properties");
    		hikariConfig = new HikariDataSource(config);
    	}catch(Exception e) {
    		e.printStackTrace();
    		//  TODO: handle exception
    	}
    	
    }
    
    
    public static ConnectionFactory getInstance () {
    	if (connectionFactory == null) {
    		connectionFactory = new ConnectionFactory();
    	}
    	return connectionFactory;
    }
    
    
    public Connection getConnection() {
    	Connection result = null;
		try {
			result = hikariConfig.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }

}
