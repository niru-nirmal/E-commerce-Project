package com.logindetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.adminactivity.AdminPanel;
import com.databaseconnection.Connectivity;

public class AdminLogin {

	public static void adminLogin() {

		// Estanlish the connection.
		Connection con = Connectivity.getConnection();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the username :");
		String username = sc.nextLine();

		System.out.println("Enter the password :");
		String password = sc.nextLine();

		try {
			String query = "select * from adminlogin";
			PreparedStatement pt = con.prepareStatement(query);

			ResultSet rs = pt.executeQuery();

			while (rs.next()) {

				if (rs.getString(1).equals(username) && rs.getString(2).equals(password)) {

					System.out.println("Welcome to admin Login.");
					AdminPanel.adminActivity();

				} else
					System.err.println("Please check username & password.");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
