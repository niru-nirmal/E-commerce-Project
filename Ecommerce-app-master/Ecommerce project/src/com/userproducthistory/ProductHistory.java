package com.userproducthistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.databaseconnection.Connectivity;

public class ProductHistory {

	public static void showhistory() {

		Connection con = Connectivity.getConnection();
		PreparedStatement pt;

		String query = "select cart.user_id , cart.prod_id ,cart.quantity , products.prod_name "
				+ "from cart join products on cart.prod_id = products.prod_id";

		try {
			pt = con.prepareStatement(query);
			ResultSet rs = pt.executeQuery();
			System.out.println("Product history of user :");

			System.out.println("ID    PROD ID  QUANTITY  PRODUCT NAME");

			while (rs.next()) {

				System.out.println(rs.getInt("user_id") + "        " + rs.getInt("prod_id") + "         " + rs.getInt("quantity")
						+ "      " + rs.getString("prod_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
