package com.app.menu;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.app.dao.CustomerCrudDAO;
import com.app.dao.impl.CustomerCrudDAOImpl;
import com.app.exception.BusinessException;

public interface CustomerMenu {

	@SuppressWarnings("resource")
	public static void CustomerMenuDisplay(final int UserId) throws BusinessException {

		Scanner sc = new Scanner(System.in);
		int cchoice = 0;
		CustomerCrudDAO dao = new CustomerCrudDAOImpl();

		do {

			System.out.println("\n    Hello!\n");
			System.out.println("  Client Menu");
			System.out.println("==================");
			System.out.println("1) View Vehicles On Lot");
			System.out.println("2) Make An Offer");
			System.out.println("3) View Your Vehicles");
			System.out.println("4) View Remaining Payments");
			System.out.println("5) Change Login Password");
			System.out.println("6) Logout");
			System.out.println("\nPlease enter appropriate choice between 1-6");
			try {
				cchoice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException | NoSuchElementException e) {
				System.out.println("\nInvalid Option Chosen. Please Try Again...");
			}

			switch (cchoice) {

			case 1:// view vehicles on lot
				dao.viewCarLot();
				System.out.println("\nOur Company has a Standard APR of 17%");
				break;

			case 2:// Make an Offer
				System.out.println("Reminder: Our Company has a Standard APR of 17%");
				double apr = .17;
				int carId;
				double offerMade;
				int years;
				System.out.println("\nEnter Vehicle ID to Make an Offer");
				try {
					carId = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("No Vehicle with that ID #");
					break;
				}
				System.out.println("\nThe Vehicle You have chosen to make an offer on:\n");
				if(dao.viewCar(carId)==false) {
					break;
				}
				System.out.println("\nEnter Your Offer for This Vehicle");
				try {
					offerMade = Double.parseDouble(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("\nNot A Valid Dollar Amount");
					break;
				}
				System.out.println("\nHow Many Years Did You Want to Finance This Vehicle?");
				try{
					years = Integer.parseInt(sc.nextLine());
				}catch (NumberFormatException e) {
					System.out.println("not a Valid Number");
					break;
				}
				
				dao.makeOffer(carId, UserId, offerMade, apr, years);
				
				break;

			case 3:// View Your Vehicles
				System.out.println("Your Vehicles:\n");
				dao.viewYourVehicles(UserId);
				break;

			case 4:// View Remaining Payments
				int carId1;
				System.out.println("List of Vehicles You Own:\n");
				dao.viewYourVehicles(UserId);
				System.out.println("\nEnter Vehicle ID to View Payment Details");
				try {
					carId1=Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Not Valid ID number");
					break;
				}
				dao.viewRemainingBalance(UserId, carId1);
				break;

			case 5:// Change Password
				System.out.println("Please Enter Your New Password!\n");
				String newPassword = sc.nextLine();
				dao.changePassword(UserId, newPassword);
				break;

			case 6:// LogOut
				break;

			default:
				System.out.println("Invalid menu choice. Please Enter Option Again.");
				break;
			}// End of Switch

		} while (cchoice != 6);

		return;
	} // end of method
}/******************** End of Interface ****************/
