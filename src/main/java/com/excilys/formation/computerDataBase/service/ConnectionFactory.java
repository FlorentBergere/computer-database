package com.excilys.formation.computerDataBase.service;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionFactory {	
	private String url;
	private String login;
	private String password;
	private String driverClassName;
	
    private static ConnectionFactory connectionFactory = null;
    
    private final static Logger log = LoggerFactory.getLogger(ConnectionFactory.class);
    
    private ConnectionFactory () {
    	InputStream inputStream;
    	try {
    		inputStream = ConnectionFactory.class.getResourceAsStream("/configDB.properties");
    		Properties properties = new Properties();
    		properties.load(inputStream);
			url = properties.getProperty("url");
			log.debug("Valeur de url = " + url);
			login = properties.getProperty("login");
			password = properties.getProperty("password");
			driverClassName = properties.getProperty("driverClassName");
			log.debug("Valeur de driverClassName = " + driverClassName);
    		//permet de recuperer les URL Login et password afin de generer des connction plus tard
    	}catch(Exception e) {
    		e.printStackTrace();
    		//  TODO: handle exception
    	}
    	
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			result = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
    }

}
