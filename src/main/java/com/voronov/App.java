package com.voronov;

import com.sun.jndi.toolkit.dir.SearchFilter;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class App {
	public static void main(String[] args) {
//		DatabaseInit.init();

		try {
			FileInputStream fis = new FileInputStream("src/main/resources/connection.property");
			Properties prop = new Properties();
			prop.load(fis);

			Connection conn = DriverManager.getConnection(
					prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));

			if (conn != null) {
				System.out.println("Connected to database.");
			} else {
				System.out.println("Failed to make connection.");
			}

			conn.close();
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}



	}
}
