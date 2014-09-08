package br.com.infoserver.testccee.dao;

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

	public Connection getConnection() {
        try {
            return DriverManager.getConnection(
            		database, user, password);
        } catch (SQLException e) {
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
