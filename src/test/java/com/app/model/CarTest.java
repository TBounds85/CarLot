package com.app.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarTest {

	@Test
	void testGetCarId() {
		Car car = new Car();
		car.setCarId(4);
		assertEquals(4,car.getCarId());		
	}
	
	@Test
	void testGetMake() {
		Car car = new Car();
		car.setMake("Ford");
		assertEquals("Ford",car.getMake());		
	}
	
	@Test
	void testGetModel() {
		Car car = new Car();
		car.setModel("F-150");
		assertEquals("F-150",car.getModel());		
	}
	
	@Test
	void testGetYear() {
		Car car = new Car();
		car.setYear(1981);
		assertEquals(1981,car.getYear());		
	}
	
	@Test
	void testGetColor() {
		Car car = new Car();
		car.setColor("Green");
		assertEquals("Green",car.getColor());		
	}
	
	@Test
	void testGetStickerPrice() {
		Car car = new Car();
		car.setStickerPrice(25999.01);
		assertEquals(25999.01,car.getStickerPrice());		
	}
	
	@Test
	void testGetStatus() {
		Car car = new Car();
		car.setStatus("For Sale");
		assertEquals("For Sale",car.getStatus());		
	}

}
