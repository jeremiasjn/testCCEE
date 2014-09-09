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
	
	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void readInformation(){
		
		try { 
			FileReader file = new FileReader(new File("./resources/information.txt")); 
			BufferedReader readedFile = new BufferedReader(file); 
			String line = readedFile.readLine();  
			while (line != null) { 
				 
				this.setDatabase ( line.substring(0,line.indexOf(":")).equals("database") ? line.substring(line.indexOf(":")+1,line.length()) : database );
				this.setUser ( line.substring(0,line.indexOf(":")).equals("user") ? line.substring(line.indexOf(":")+1,line.length()) : user ) ;
				this.setPassword ( line.substring(0,line.indexOf(":")).equals("password") ? line.substring(line.indexOf(":")+1,line.length()) : password );
				
				line = readedFile.readLine();  
				} 
			file.close(); 
			} catch (IOException e) { 
				System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); 
				System.out.println();  
			}						
		}
	
	public Connection getConnection() {
        try {
        	readInformation();
        	Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
            		this.getDatabase(), this.getUser(), this.getPassword());
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
