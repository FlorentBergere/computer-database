package com.excilys.persistence;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectionFactory  {
	
	@Autowired
	private DataSource hikariDataSource;
	
    
    private ConnectionFactory () {    	
    }
    
   
    
    public Connection getConnection() {
    	Connection result = null;
		try {
			result = hikariDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
    }

}
