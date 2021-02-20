package com.app.model;

public class Transaction {
	int transid;
	double oldBalance;
	double newBalance;
	double paymentAmount;
	int carId;
	String timeStamp;

	public Transaction(int transid, double oldBalance, double newBalance, double paymentAmount, int carId, String timeStamp) {
		super();
		this.transid = transid;
		this.oldBalance = oldBalance;
		this.newBalance = newBalance;
		this.paymentAmount = paymentAmount;
		this.carId = carId;
		this.timeStamp = timeStamp;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTransid() {
		return transid;
	}

	public void setTransid(int transid) {
		this.transid = transid;
	}

	public double getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(double d) {
		this.oldBalance = d;
	}

	public double getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String string) {
		this.timeStamp = string;
	}

	@Override
	public String toString() {
		return "Transaction [Transaction ID= " + transid + ", Old Balance= $" + oldBalance + ", Payment Amount= $" + paymentAmount +", New Balance= $" + newBalance +  ", CarID= " + carId + ", timeStamp= " + timeStamp + "]";
	}

}