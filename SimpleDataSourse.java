package ¨Fºz¬ü­¹«ü«n;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleDataSourse {
	private static String url;
	private static String username;
	private static String password;

	public static Connection getConnection() throws SQLException {
		String server = "jdbc:mysql://140.119.19.79/";
		String database = "TG05?characterEncoding=utf8";
		String url = server + database;
		String username = "TG05";
		String password = "6czych";
		return DriverManager.getConnection(url, username, password);
	}

	

}
