package br.com.infoserver.testccee.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	String database;
	String user;
	String password;
	String driver;
	
	public void readInformation(){
		
		try { 
			FileReader file = new FileReader(new File("./resources/information.txt")); 
			BufferedReader readedFile = new BufferedReader(file); 
			String line = readedFile.readLine();  
			while (line != null) { 
				 
				driver =  line.substring(0,line.indexOf(":")).equals("driver") ? line.substring(line.indexOf(":")+1,line.length()) : driver ;
				database =  line.substring(0,line.indexOf(":")).equals("database") ? line.substring(line.indexOf(":")+1,line.length()) : database ;
				user =  line.substring(0,line.indexOf(":")).equals("user") ? line.substring(line.indexOf(":")+1,line.length()) : user  ;
				password =  line.substring(0,line.indexOf(":")).equals("password") ? line.substring(line.indexOf(":")+1,line.length()) : password ;
				
				line = readedFile.readLine();  
				} 
			file.close(); 
			} catch (IOException ex) { 
				ex.printStackTrace();  
			}						
		}
	
	public Connection getConnection() {
        try {
        	readInformation();
        	Class.forName(driver);
            return DriverManager.getConnection(
            		database, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
	
	public Connection getConnection( String database, String user, String password ) {
        try {
            return DriverManager.getConnection(
            		database, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
}
