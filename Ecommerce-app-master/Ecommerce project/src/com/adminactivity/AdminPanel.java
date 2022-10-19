package com.adminactivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.databaseconnection.Connectivity;
import com.productlist.AddProduct;
import com.registerlist.RegisterUserList;
import com.userproducthistory.ProductHistory;

public class AdminPanel {

	public static void adminActivity() {

		Scanner sc = new Scanner(System.in);

		System.out.println("                             ");
		System.out.println("Enter your option as per below");
		System.out.println("1 - Add Products.");
		System.out.println("2 - Check quantity of product.");
		System.out.println("3 - Check history of user purchase");
		System.out.println("4 - Check Registered user list.");

		int ch = sc.nextInt();
		switch (ch) {

		case 1:

			AddProduct.addProduct();
			break;
		case 2:
			checkProductQuantity();
			break;

		case 3:

			// calling product history class.
			ProductHistory.showhistory();
			break;

		case 4:
			RegisterUserList.showUserList();
			break;

		}
	}

	public static void checkProductQuantity() {

		Connection con = Connectivity.getConnection();

		Scanner sc = new Scanner(System.in);

		try {
			PreparedStatement pt = con.prepareStatement("SELECT * FROM products ORDER BY prod_name");
			ResultSet rs = pt.executeQuery();

			System.out.println("-----------------------------------");
			System.out.println("        LIST OF PRODUCTS           ");
			System.out.println("-----------------------------------");
			System.out.println("                                   ");

			System.out.println("Id  Description   Price   Product Name         ");

			while (rs.next()) {

				System.out.println(
						rs.getInt(1) + "   " + rs.getString(2) + "   " + rs.getInt(3) + "   " + rs.getString(4));
			}

			System.out.println("                                   ");
			System.out.println("Enter product Id to check quantity.");
			int id = sc.nextInt();
			PreparedStatement pt1 = con.prepareStatement("SELECT prod_quantity FROM products where prod_id = ?");

			pt1.setInt(1, id);
			ResultSet rs1 = pt1.executeQuery();

			while (rs1.next()) {
				System.out.println("Product Quantity : " + rs1.getInt("prod_quantity"));
				break;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
