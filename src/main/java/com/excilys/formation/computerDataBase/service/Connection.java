package com.excilys.formation.computerDataBase.service;


import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
	
	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC";
    private static String LOGIN = "admincdb";
    private static String PASSWORD = "azerty1234";
    
    private static java.sql.Connection con = null;
    private static boolean initialise = false;
    
    public Connection () {
    	try {
    		if (!initialise) {
        		Connection.con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        		initialise = true;
        	}
    	}catch(Exception e) {
    		//  TODO si la tentative de connection echoue
    	}
    	
    }
    
    public java.sql.Connection getConnection () {
    	return Connection.con;
    }
    
    public void closeConnection() {
    	try {
    		System.out.println("Fermeture de la connection avec la bd");
    		Connection.con.close();
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	
    }

}
