package com.homescreen;

import java.util.Scanner;

import com.logindetails.AdminLogin;
import com.logindetails.Login;
import com.logindetails.Register;

public class HomePage {

	public void selectLogin() {

		Scanner sc = new Scanner(System.in);

		System.out.println("    Welcome to Ecommerce website.   ");

		System.out.println("--------------------------------");
		System.out.println("Please Login or Register");
		System.out.println("1 - Login");
		System.out.println("2 - Register");
		System.out.println("3 - Admin Login");
		int choice = sc.nextInt();
		switch (choice) {

		case 1:
			// System.out.println("Login");
			Login login = new Login();
			login.addLogin();
			break;
		case 2:
			// System.out.println("Please Register");
			Register.registeruser();
			break;

		case 3:
			AdminLogin.adminLogin();
			break;
		}
	}

	public static void main(String[] args) {

		HomePage obj = new HomePage();
		// obj.listOfProduct();
		obj.selectLogin();
	}

}
