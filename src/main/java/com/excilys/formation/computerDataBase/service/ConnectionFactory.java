package com.excilys.formation.computerDataBase.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;

public class ConnectionFactory {
	
	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC";
    private static String LOGIN = "admincdb";
    private static String PASSWORD = "azerty1234";
    private static ConnectionFactory connectionFactory = null;
 //   private Connection con = null;
    
    private ConnectionFactory () {
    	try {
    		//permet de recuperer les URL Login et password afin de generer des connction plus tard
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
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			result = DriverManager.getConnection(URL, LOGIN, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	return result;
    }
    
    public void closeConnection(Connection connection) {
    	try {
    		connection.close();
    		System.out.println("fermeture de la connexion");
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	
    }

}
