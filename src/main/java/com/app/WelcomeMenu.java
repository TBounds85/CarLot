package com.app;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.app.exception.BusinessException;
import com.app.menu.CreateNewUserMenu;
import com.app.menu.LoginVerificationMenu;

public interface WelcomeMenu {

	public static void main(String[] args) throws BusinessException {

		Scanner sc = new Scanner(System.in);

		int choice = 0;
		do {
			System.out.println("");
			System.out.println("Welcome to our Virtual Car Lot!");
			System.out.println("----------------------------");
			System.out.println("	Welcome Menu");
			System.out.println("----------------------------");
			System.out.println("	1) Login");
			System.out.println("	2) Create New User");
			System.out.println("	3) Close App");
			System.out.println("----------------------------");
			System.out.println("\nPlease enter Your Choice: ");

			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException | NoSuchElementException e) {
				System.out.println("\nInvalid Option Chosen. Please Try Again...\n");
			}

			switch (choice) {

			case 1:
				LoginVerificationMenu.LoginVerification();
				break;

			case 2:
				CreateNewUserMenu.CreateNewUser();
				break;

			case 3:
				System.out.println("Thank You for using our Virtual Car Lot!!");
				System.out.println("\nLooking Forward to Serving you Again! =)");
				break;

			default:
				System.out.println("\nInvalid Option Chosen. Please Try Again...\n");
				break;
			}// End of Switch

		} while (choice != 3);
		sc.close();
	}// End of Switch

}/******************** End of Interface ****************/
