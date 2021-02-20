package com.app.menu;

import java.util.Scanner;

import com.app.dao.LoginVerificationDAO;
import com.app.dao.impl.LoginVerificationDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface LoginVerificationMenu {

	Customer c = new Customer();

	@SuppressWarnings("resource")
	static void LoginVerification() throws BusinessException {

		int checker = 0;
		int counter = 5;
		LoginVerificationDAO dao = new LoginVerificationDAOImpl();

		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("\nWelcome to Login Verification\n");
			System.out.println("Remaining Login Attempts: " + counter);
			System.out.println("\nPlease Enter Your Username: ");
			String un = sc.next();
			System.out.println("\nPlease Enter Your Password: ");
			String pass = sc.next();

			// determines what menu they goto by returning userId as int checker
			checker = dao.verifyUserId(un, pass);

			if (checker <= 99 && checker > 0) {
				System.out.println("\nWelcome to CarLot Employee App!\n");
				counter = 0;
				EmployeeMenu.EmployeeMenuView();
			} else if (checker > 99) {
				System.out.println("\nWelcome to Our Car Lot Client App!\n");
				counter = 0;
				CustomerMenu.CustomerMenuDisplay(checker);
			} else {
				System.out.println("\nInvalid Username/Password Combination.\n");
				--counter;
			}

		} while (counter != 0);

		return;
	}
}/******************** End of Interface ****************/
