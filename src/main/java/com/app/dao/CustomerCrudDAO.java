package com.app.dao;

import com.app.exception.BusinessException;

public interface CustomerCrudDAO {

	void viewCarLot();

	void changePassword(int userId, String newPassword) throws BusinessException;

	boolean viewCar(int carId);

	void viewYourVehicles(int UserId);

	void viewRemainingBalance(int UserId, int carId);

	void makeOffer(int carId, int UserId, double offerMade, double apr, int years);
}
