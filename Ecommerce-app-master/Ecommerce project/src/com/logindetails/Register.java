package com.logindetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.databaseconnection.Connectivity;

public class Register {

	public static void registeruser() {

		System.out.println("Please Register yourself.");

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter your username :");

		String username = sc.nextLine();

		System.out.println("Enter your Mobile Number :");
		String MobileNum = sc.nextLine();

		System.out.println("Enter your password :");
		String password = sc.nextLine();

		Connection con = Connectivity.getConnection();

		PreparedStatement pst;
		try {
			pst = con.prepareStatement("Insert into register (user_name,mobile_num,password) values(?,?,?)");
			pst.setString(1, username);
			pst.setString(2, MobileNum);
			pst.setString(3, password);

			pst.executeUpdate();
			System.out.println("User Registered successfully.");
			System.out.println("Please Login now.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
