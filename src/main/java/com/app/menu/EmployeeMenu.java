package com.app.menu;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.app.dao.CustomerCrudDAO;
import com.app.dao.EmployeeCrudDAO;
import com.app.dao.impl.CustomerCrudDAOImpl;
import com.app.dao.impl.EmployeeCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.service.GeneralService;
import com.app.service.impl.GeneralServiceImpl;

public interface EmployeeMenu {

	@SuppressWarnings("resource")
	static void EmployeeMenuView() throws BusinessException {
		int customerUserId = 0;
		EmployeeCrudDAO dao = new EmployeeCrudDAOImpl();
		CustomerCrudDAO dao2 = new CustomerCrudDAOImpl();
		GeneralService dao3= new GeneralServiceImpl();
		Scanner sc = new Scanner(System.in);
		int echoice = 0;

		do {
			System.out.println("\nCurrect Assigned Customer ID# : " + customerUserId);
			System.out.println("\n   	Employee Menu");
			System.out.println("====================================");
			System.out.println("1)  Set Current Client 	**Perform Before Customer Transactions**");
			System.out.println("2)  View Customer Account Balance(s)");
			System.out.println("3)  View Customer Transaction History");
			System.out.println("4)  Process Customer Payment");
			System.out.println("5)  Clear Current Customer	**Perform When Client Transactions Complete**");
			System.out.println("6)  Manage Offers");
			System.out.println("7)  Add Car To Lot");
			System.out.println("8)  Remove Car From Lot");
			System.out.println("9)  Log Out");

			System.out.println("Please enter appropriate choice between 1-9");
			try {
				echoice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException | NoSuchElementException e) {
				System.out.println("\nInvalid Option Chosen.\n");
			}

			switch (echoice) {

			case 1:// assign current client

				System.out.println("Hello, Im {Say your name here}, How May I have your last name to get you started today?\n");
				System.out.println("Hit ENTER to See All Customers");
				String customerName = sc.nextLine();
				dao.getCustomerByName(customerName);
				System.out.println("\n***Verify With Gov. Issued ID Card***");
				System.out.println("\nEnter Userid Associated with the Customer:");
				System.out.println(" If No Customers Listed assign ID to Zero (0)");
				try {
					customerUserId = Integer.parseInt(sc.nextLine());
					// can adjust when needing more USERID# above 1,000
					if (customerUserId != 0 && customerUserId < 99 || customerUserId > 1000) {

						System.out.println("No Customer With that ID #.");
						customerUserId = 0;
					}
				} catch (NumberFormatException e) {
					System.out.println("Enter Valid Customer ID #\n");
					break;
				}
				break;

			case 2:// View Customer Account Balance(s)

				if (customerUserId > 99) {
					dao.showAllAccounts(customerUserId);
				} else {
					System.out.println("No Customer # Assigned\n");
				}
				break;

			case 3:// View Customer Transaction History

				if (customerUserId > 99) {
					dao.showAllAccounts(customerUserId);
					System.out.println("\nEnter Car ID # the Customer Wants Transactions for:");
					int carId = 0;
					try {
						carId = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Car ID # Doesn't Exist\n");
						break;
					}
					dao.getAllTransactions(carId);
				} else {
					System.out.println("No Customer # Assigned\n");
				}
				break;

			case 4:// Process Customer Payment

				if (customerUserId > 99) {
					dao.showAllAccounts(customerUserId);
					System.out.println("Enter Car ID # Customer Wants to Make Payment On:");
					int carId;
					double paymentAmount;
					try {
						carId = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Car ID # Doesn't Exist\n");
						System.out.println("Returning To Menu");
						break;
					}
					System.out.println("\nEnter Payment Recieved From Customer:");
					try {
						paymentAmount = Double.parseDouble(sc.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Not A Valid $ Amount");
						System.out.println("Returning To Menu");
						break;
					}
					dao.processPayment(carId, paymentAmount);

				} else {
					System.out.println("No Customer # Assigned\n");
					System.out.println("Returning To Menu");
					break;
				}

				break;

			case 5:// Clear Current Customer
				customerUserId = 0;
				break;

			case 6:// Manage Offers
				dao.viewPendingOffers();
				System.out.println("Enter Offer Id Number You Want To Approve/Reject");
				int offerId = 0;
				try {
					offerId = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Not a Valid Offer ID #\n");
					System.out.println("Returning To Menu");
					break;
				}
				System.out.println("\n************You Have Selected Offer #: "+offerId+"************");
				System.out.println("If Offer Approved System Will Reject all Other Offers for that Vehicle");
				System.out.println("If Multiple Offers for same Vehicle. Choose Offer With Highest Profit For Vehicle");
				System.out.println("\nChoose Option:");
				System.out.println("1.) Approve Offer");
				System.out.println("2.) Reject Offer");
				System.out.println("3.) Return to Main Menu\n");
				String Choice=sc.nextLine();
				if(Choice.equals("1")){
					dao.approveOffer(offerId);
				}
				else if(Choice.equals("2")){
					dao.rejectOffer(offerId);
				}
				break;

			case 7:// Add Car To Lot
				dao.viewCarLot(); //view car lot in EmployeeCrudDAO (like customer but sees all vehicles even ones owned by someone
				System.out.println("Enter Car Id Number You want to use (Numbers Only):");
				int carId = 0;
				boolean checker=true;
				do {
					try {
						carId = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Not a Valid Car ID #\n");
						System.out.println("Returning To Menu");
						break;
					}
					checker = dao3.CheckForCarIdInUse(carId);
					if(checker==true) {
						System.out.println("Vehicle ID # is in use Please Enter Different #");
					}
				}while(checker==true);
				
				System.out.println("Valid Vehicle ID Entered\n");
				
				System.out.println("Enter Vehicle Data:");
				System.out.println("\nEnter Year");
				int year;
				try {
					year = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid Entry, Returning To Menu");
					break;
				}
				System.out.println("\nEnter Vehicle Make");
				String make = sc.nextLine();
				System.out.println("\nEnter Vehicle Model");
				String model = sc.nextLine();
				System.out.println("\nEnter Vehicle Color");
				String color = sc.nextLine();
				System.out.println("\nEnter Sticker Price for Vehicle ($)");
				double stickerPrice;
				stickerPrice = Double.parseDouble(sc.nextLine());				
				String status = "For Sale";
				dao.addCarToLot(carId, year, make, model, color, stickerPrice, status);
				break;

			case 8:// Remove Car From Lot
				dao2.viewCarLot();
				System.out.println("\nChoose Vehicle ID # to Remove From Car Lot");
				try {
					carId = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Car ID # Doesn't Exist\n");
					break;
				}
				System.out.println("Vehicle Pending Removal:");
				dao2.viewCar(carId);
				System.out.println("\nAre You Sure You Want to Remove Vehicle? (yes/no)");
				String yesOrNo = sc.nextLine();
				if (yesOrNo.equals("yes")) {
					dao.removeCarFromLot(carId);
					System.out.println("Vehicle Removed Successfully");
					break;
				}else {
					System.out.println("Vehicle Removal Canceled");
					break;
				}

			case 9:// Logout
				break;

			default:
				System.out.println("Invalid menu choice. Please Enter Valid Menu Option.");
				break;
			}// end of Switch

		} while (echoice != 9);

		System.out.println("See you next time you are scheduled to work!!");

	}// end of method
}/******************** End of Interface ****************/