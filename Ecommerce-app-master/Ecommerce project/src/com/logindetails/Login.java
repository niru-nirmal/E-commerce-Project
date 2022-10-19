package com.logindetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.cartdeatils.Cart;
import com.databaseconnection.Connectivity;
import com.homescreen.HomePage;
import com.productlist.ProductList;
import com.userproducthistory.ProductHistory;

public class Login {

	// object of cart class
	Cart cart = new Cart();

	public void addLogin() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the username :");
		String name = sc.nextLine();

		System.out.println("Enter your password :");
		String password = sc.nextLine();

		Connection con = Connectivity.getConnection();

		try {
			PreparedStatement pt = con.prepareStatement("select * from register");

			ResultSet rs = pt.executeQuery();

			int flag = 0;
			int id = 0;

			while (rs.next()) {

				if (name.equals(rs.getString(2)) && password.equals(rs.getString(4))) {

					id = rs.getInt(1);

					flag = 1;
					break;

				}
			}
			if (flag != 1) {
				System.err.println("Please register yourself or check username or password.");
				HomePage obj = new HomePage();
				obj.selectLogin();
			} else {

				System.out.println("Login Successfully.");
				// Main methods.
				ProductList.productList();
				cart.addtoCart(id);
				cart.buyproduct(id);
			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
