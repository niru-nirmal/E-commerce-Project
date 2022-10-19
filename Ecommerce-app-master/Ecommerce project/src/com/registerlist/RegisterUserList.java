package com.registerlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.databaseconnection.Connectivity;

public class RegisterUserList {

	public static void showUserList() {

		PreparedStatement pst;

		// establish the connection with database.
		Connection con = Connectivity.getConnection();

		// Query to fetch the registered user list from database.
		String query = "select user_id,user_name ,mobile_num from register";
		try {
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			System.out.println("USER ID     USER NAME     MOBILE NUMBER");
			while (rs.next()) {

				System.out.println("   " + rs.getInt("user_id") + "          " + rs.getString("user_name")
						+ "       " + rs.getString("mobile_num"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
