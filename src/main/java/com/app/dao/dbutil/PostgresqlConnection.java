package com.app.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnection {

	private static Connection connection;
	
	private PostgresqlConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
//		String url="jdbc:postgresql://localhost:5432/postgres?currentSchema=carlot";  **use for Local Host**
		String url="jdbc:postgresql://database-1.cxj9luoae5dn.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=carlot";
		
		String username="postgres";
		String password="password";
		connection=DriverManager.getConnection(url, username, password);
		return connection;

	}
}