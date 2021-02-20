package com.app.menu;

import java.util.Scanner;

import com.app.exception.BusinessException;
import com.app.service.GeneralService;
import com.app.service.impl.GeneralServiceImpl;

public interface CreateNewUserMenu {

	Scanner sc = new Scanner(System.in);

	static void CreateNewUser() throws BusinessException {

		String username;
		boolean checker = false;
		GeneralService dao = new GeneralServiceImpl();

		System.out.println("With the Creation of a User Account you");
		System.out.println("    Will have Access to our App.\n");
		System.out.println("Please Enter Your First Name");
		String firstName = sc.nextLine();
		System.out.println("Please Enter Your Last Name");
		String lastName = sc.nextLine();
		System.out.println("Please Enter Your Address");
		String address = sc.nextLine();
		System.out.println("Please Enter Your 10 digit Phone Number. (no dashes (-) please) ");
		Long contact;
		try {
			contact = Long.parseLong(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid Entry: Contact Set to Default 99999999999");
			contact = 9999999999L;
		}

		do {
			System.out.println("Please Enter a New Username (Case Sensitive)");
			username = sc.nextLine();
			checker = dao.CheckUsernameAvailability(username);
			if (checker == true) {
				System.out.println(username + " is Unavailable.\n");
			}
		} while (checker == true);

		System.out.println("\nPlease Enter Your New Password (Case Sensitive)");
		String password = sc.nextLine();

		dao.CreateNewUser(firstName, lastName, address, contact, username, password);

		System.out.println("\nCreated New User Successfully!!");
		System.out.println("	Please Login =)\n");
		return;
	}
}/******************** End of Interface ****************/
