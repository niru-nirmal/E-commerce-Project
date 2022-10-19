package com.cartdeatils;

import java.sql.Connection;

import com.databaseconnection.Connectivity;

public class BuyProduct {

	// buy the product.
	public void buyprod() {

		System.out.println("Buy your product.");

		Connection con = Connectivity.getConnection();

	}
}
