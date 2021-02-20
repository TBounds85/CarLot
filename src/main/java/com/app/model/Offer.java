package com.app.model;

public class Offer {

	private int offerId;
	private int carId;
	private int userId;
	private double askingPrice;
	private double offerMade;
	private double apr = .17;
	private int years;
	private double totalInterest;
	private double totalProfit;
	private double monthlyPayment;

	public Offer() {
		super();
	}

	public Offer(int offerId, int carId, int userId, double askingPrice, double offerMade, double monthlyPayment, double apr, int years,
			double totalProfit, double totalInterest) {
		super();
		this.offerId = offerId;
		this.carId = carId;
		this.userId = userId;
		this.askingPrice = askingPrice;
		this.offerMade = offerMade;
		this.apr = apr;
		this.years = years;
		this.monthlyPayment = monthlyPayment;
		this.totalInterest = totalInterest;
		this.totalProfit = totalProfit;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getAskingPrice() {
		return askingPrice;
	}

	public void setAskingPrice(double askingPrice) {
		this.askingPrice = askingPrice;
	}

	public double getOfferMade() {
		return offerMade;
	}

	public void setOfferMade(double offerMade) {
		this.offerMade = offerMade;
	}

	public double getApr() {
		return apr;
	}

	public void setApr(double apr) {
		this.apr = apr;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public double getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(double totalInterest) {
		this.totalInterest = totalInterest;
	}

	@Override
	public String toString() {
		return "Offer Id= " + offerId + ", Car Id= " + carId + ", User Id= " + userId + 
			 "\nAsking Price= $"	+ askingPrice + ", Offer Made= $" + offerMade + ", Monthly Payment= $"+ monthlyPayment +", Years=" + years + 
			 "\nTotal From Interest= $" + totalInterest + ", total Profit= $" + totalProfit + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(apr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(askingPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + carId;
		temp = Double.doubleToLongBits(monthlyPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + offerId;
		temp = Double.doubleToLongBits(offerMade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalInterest);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalProfit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + userId;
		result = prime * result + years;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (Double.doubleToLongBits(apr) != Double.doubleToLongBits(other.apr))
			return false;
		if (Double.doubleToLongBits(askingPrice) != Double.doubleToLongBits(other.askingPrice))
			return false;
		if (carId != other.carId)
			return false;
		if (Double.doubleToLongBits(monthlyPayment) != Double.doubleToLongBits(other.monthlyPayment))
			return false;
		if (offerId != other.offerId)
			return false;
		if (Double.doubleToLongBits(offerMade) != Double.doubleToLongBits(other.offerMade))
			return false;
		if (Double.doubleToLongBits(totalInterest) != Double.doubleToLongBits(other.totalInterest))
			return false;
		if (Double.doubleToLongBits(totalProfit) != Double.doubleToLongBits(other.totalProfit))
			return false;
		if (userId != other.userId)
			return false;
		if (years != other.years)
			return false;
		return true;
	}

}
