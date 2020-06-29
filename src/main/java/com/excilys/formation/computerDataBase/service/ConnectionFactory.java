package com.excilys.formation.computerDataBase.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;

public class ConnectionFactory {
	
	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC";
    private static String LOGIN = "admincdb";
    private static String PASSWORD = "azerty1234";
    
	private static String URLH2 ="jdbc:h2:mem:computer-database-db;INIT=RUNSCRIPT FROM '/home/florent/Documents/excilys/computer-database/src/main/resources/initTestH2.sql';DB_CLOSE_DELAY=-1";
	private static String LOGINH2 = "test";
	private static String PASSWORDH2 = "";
	
    private static ConnectionFactory connectionFactory = null;
    
    public static enum Mode {PROD, TEST};
    
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
    
    
    public Connection getConnection(Mode mode) {
    	Connection result = null;
    	if (mode == Mode.PROD) {
    		result = normalConnction();
    	}else if (mode == Mode.TEST){
    		result = testConnection();
    	}
    	return result;
    }
    
    private Connection normalConnction () {
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
    
    public Connection testConnection() {
    	Connection result = null;

		try {
			Class.forName ("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		try {
			result = DriverManager.getConnection(URLH2, LOGINH2, PASSWORDH2);
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
