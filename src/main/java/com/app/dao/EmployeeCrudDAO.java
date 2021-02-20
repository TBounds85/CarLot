package com.app.dao;

import com.app.exception.BusinessException;

public interface EmployeeCrudDAO {

	void getCustomerByName(String customerName) throws BusinessException;

	void showAllAccounts(int UserId);

	void getAllTransactions(int carId);
	
	void removeCarFromLot(int carId);
	
	void processPayment(int carId, double paymentAmount) throws BusinessException;
	
	void viewPendingOffers();

	void addCarToLot(int carId, int year, String make, String model, String color, double stickerprice, String status);

	void viewCarLot();

	void approveOffer(int offerId);
	
	void rejectOffer(int offerId);
}
