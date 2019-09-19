package com.voronov;

import java.sql.*;

public final class DatabaseInit {
	private static String connString = "jdbc:postgresql://localhost:5432/";
	private static String username= "postgres";
	private static String password= "mssql";

	public static void init() {

		Connection conn = getConnection(connString, username, password);
		DatabaseInit.createDatabase(conn, "MyBookTracker");



	}

	private static Connection getConnection(String connString, String username, String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connString, username, password);

			if (conn != null) {
				//System.out.println("Connected to database.");
			} else {
				//System.out.println("Failed to make connection.");
			}
		} catch (SQLException e) {
		System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	} catch (Exception e) {
		e.printStackTrace();
	}
		return conn;
	}

	private static void createDatabase(Connection conn, String name) {
		String query = "CREATE DATABASE \"" + name + "\"\n    WITH \n    OWNER = postgres\n    ENCODING = 'UTF8';";
		Statement statement = null;
		try {
			statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1007) {
				System.out.println(e.getMessage());
			} else {
				e.printStackTrace();
			}
		}

	}
}
