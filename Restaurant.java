package ¨Fºz¬ü­¹«ü«n;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Restaurant {
	private ArrayList<String> RestaurantNameList;

	public Restaurant() throws SQLException {
		// TODO Auto-generated constructor stub
		RestaurantNameList = new ArrayList<>();
		loadRestaurantList();

	}

	public void loadRestaurantList() throws SQLException {
		Connection conn = SimpleDataSourse.getConnection();
		try {
			Statement stat = conn.createStatement();
			String ResName = "SELECT Name FROM totalinformation";
			ResultSet resultSet = stat.executeQuery(ResName);
			while (resultSet.next()) {
				RestaurantNameList.add(resultSet.getString("Name"));

			}

		} finally {
			conn.close();
		}

	}
	//public ArrayList<E> () {
		
	//}
	
}
