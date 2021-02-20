package com.app.model;

public class Account {
	private int userId;
	private int carId;
	private double balance;
	private int remainingMonths;
	private double monthlyPayment;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int userId, int carId, double balance, int remainingMonths) {
		super();
		this.userId = userId;
		this.carId = carId;
		this.balance = balance;
		this.remainingMonths = remainingMonths;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getRemainingMonths() {
		return remainingMonths;
	}

	public void setRemainingMonths(int remainingMonths) {
		this.remainingMonths = remainingMonths;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + carId;
		result = prime * result + remainingMonths;
		result = prime * result + userId;
		return result;
	}

	@Override
	public String toString() {
		return "Account [carId=" + carId + ", balance= $" + balance + ", Remaining Months= " + remainingMonths
				+ ", Monthly Payment= $" + monthlyPayment + "]";
	}

}
