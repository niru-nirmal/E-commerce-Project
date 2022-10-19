package com.cartdeatils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.databaseconnection.Connectivity;

public class Cart {

	// Take input from user.
	Scanner sc = new Scanner(System.in);

	// establish the connection.
	Connection con = Connectivity.getConnection();

	public void addtoCart(int user_id) {

		// take input from user.
		Scanner sc = new Scanner(System.in);

		boolean check = true;
		while (check) {
			System.out.println("                        ");
			System.out.println("SELECT THE BELOW OPTION ");
			System.out.println(" 1- Add product to cart");
			System.out.println(" 2- Exit");
			System.out.println("SELECT EXIT TO BUY PRODUCT .");

			System.out.println("                  ");
			System.out.println("Choose the option.");
			int value = sc.nextInt();

			switch (value) {
			case 1:
				addmoreitem(user_id);
				break;

			case 2:
				System.out.println("Exit");
				check = false;
				// break;

			}
		}

	}

	public void addmoreitem(int user_id) {

		// connection
		PreparedStatement pt;
		PreparedStatement pt1;
		PreparedStatement pt2;

		Connection con = Connectivity.getConnection();

		// Take product ID and Quantity from User.

		System.out.println("Please enter the product ID to add to cart :");
		int id = sc.nextInt();

		System.out.println("Enter the Quantity of product :");
		int quantity = sc.nextInt();

		try {
			pt = con.prepareStatement("select prod_id from products");
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {

				// To check entered product id present into Database.
				int prod_id = rs.getInt(1);
				if (prod_id == id) { // 2 == 1

					String query = "insert into cart(user_id,prod_id,quantity) values(?,?,?) ON DUPLICATE KEY UPDATE quantity=quantity+VALUES(quantity)";
					pt1 = con.prepareStatement(query);

					pt1.setInt(1, user_id);
					pt1.setInt(2, prod_id);
					pt1.setInt(3, quantity);

					pt1.executeUpdate();
					System.out.println("Product added into cart.");

					// update the quantity in product table after added to the cart.

					String q2 = "update products SET prod_quantity = prod_quantity - ? where prod_id =?";
					pt2 = con.prepareStatement(q2);
					pt2.setInt(1, quantity);
					pt2.setInt(2, prod_id);
					pt2.executeUpdate();
					System.out.println("Quantity update successfully.");

				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void buyproduct(int id) {

		System.out.println("Select the option.");

		System.out.println("1- Buy the product added in your cart.");
		System.out.println("2- Exit.");
		int choice = sc.nextInt();
		switch (choice) {

		case 1:
			// System.out.println("you are to buy product.");
			calculatebill(id);
			break;

		case 2:

			System.out.println("Exit");
			break;

		}
	}

	public void calculatebill(int id) {

		System.out.println("User id :" + id);
		int amount = 0;
		int cart_quantity = 0;
		int price = 0;
		int total;
		PreparedStatement pt;
		try {

//			// check if the cart is empty or not.
//			String q2 = "select cart_id from cart where user id=?";
//
//			PreparedStatement pst = con.prepareStatement(q2);
//
//			pst.setInt(1, id);
//			int i = pst.executeUpdate();
//			if (i < 0) {
//				System.err.println("Your cart is empty.Please add product to cart.");
			
			
//			}

			String join_table = "select cart.prod_id, cart.quantity, products.prod_price from products join cart on cart.prod_id= products.prod_id where user_id=?";
			pt = con.prepareStatement(join_table);
			pt.setInt(1, id);

			ResultSet rs = pt.executeQuery();

			System.out.println("---------------------");
			System.out.println("Product of your cart.");
			System.out.println("---------------------");

			while (rs.next()) {

				System.out.println("Id   Qty    Price ");
				System.out.println(
						rs.getInt("prod_id") + "      " + rs.getInt("quantity") + "   " + rs.getInt("prod_price"));

				cart_quantity = rs.getInt("quantity");
				price = rs.getInt("prod_price");

				total = cart_quantity * price;

				amount = amount + total;

				System.out.println("Total price of product :" + total);
				System.out.println("                                 ");

			}

			System.out.println("-------------------------------");
			System.out.println("Total amount payable :" + amount);
			System.out.println("-------------------------------");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
