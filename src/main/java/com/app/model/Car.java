package com.app.model;

public class Car {
	private int carId;
	private String make;
	private String model;
	private int year;
	private String color;
	private double stickerPrice;
	private String status;

	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Car(int carId, String make, String model, int year, String color, double stickerPrice, String status) {
		super();
		this.carId = carId;
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.stickerPrice = stickerPrice;
		this.status = status;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getStickerPrice() {
		return stickerPrice;
	}

	public void setStickerPrice(double stickerPrice) {
		this.stickerPrice = stickerPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Car Id#= " + carId + ", Year= "+ year + ", Make= " + make + ", Model= " + model + ", color=" + color
				+ ", Sticker Price= " + stickerPrice + ", Status= " + status;
	}

}
