package com.app.service;

import com.app.exception.BusinessException;

public interface GeneralService {
	
	
	public boolean CheckUsernameAvailability(String username) throws BusinessException;
	public int getNextTransactionId() throws BusinessException;
	public void GenerateTransactionRecord(double oldBalance, double transAmount, double newBalance, int carId) throws BusinessException;
	public void CreateNewUser(String firstName, String lastName, String address, long contact, String username, String password) throws BusinessException;
	public boolean CheckForCarIdInUse(int carId);
	
}/********************End of Interface****************/
