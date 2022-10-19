package com.productlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.databaseconnection.Connectivity;

public class AddProduct {

	public static void addProduct() {

		System.out.println("Add the Product.");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int prod_id = 0;
		String prod_name = null;
		String description = null;
		int price = 0;
		int prod_qty = 0;
		try {
			System.out.println("Enter your Product Id :");

			prod_id = Integer.parseInt(br.readLine());

			System.out.println("Enter your Product name :");
			prod_name = br.readLine();

			System.out.println("Enter your Product Description :");
			description = br.readLine();

			System.out.println("Enter your Product price :");
			price = Integer.parseInt(br.readLine());

			System.out.println("Enter your Product Quantity :");
			prod_qty = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Connection con = Connectivity.getConnection();

		PreparedStatement pst;
		try {
			pst = con.prepareStatement(
					"Insert into products (prod_id,prod_description,prod_price,prod_name,prod_quantity) values(?,?,?,?,?)");
			pst.setInt(1, prod_id);
			pst.setString(2, description);
			pst.setInt(3, price);
			pst.setString(4, prod_name);
			pst.setInt(5, prod_qty);

			pst.executeUpdate();
			System.out.println("Product added successfully.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
