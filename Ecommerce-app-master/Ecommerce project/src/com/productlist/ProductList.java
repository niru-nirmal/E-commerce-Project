package com.productlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.databaseconnection.Connectivity;

public class ProductList {

	public static void productList() {

		/// get the connection

		Connection con = Connectivity.getConnection();

		try {
			PreparedStatement pt = con.prepareStatement("SELECT * FROM products ORDER BY prod_name");
			ResultSet rs = pt.executeQuery();

			System.out.println("-----------------------------------");
			System.out.println("Please select product as per items list.");
			System.out.println("-----------------------------------");
			System.out.println("                                                ");

			System.out.println("Id  Product Name   Description   Price       ");

			while (rs.next()) {

				System.out.println(
						rs.getInt(1) + "   " + rs.getString(4) + "       " + rs.getString(2) + "   " + rs.getInt(3));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
