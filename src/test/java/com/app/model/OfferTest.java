package com.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OfferTest {

	@Test
	void testGetOfferId() {
		Offer offer = new Offer();
		offer.setOfferId(12);
		assertEquals(12, offer.getOfferId());
	}

	@Test
	void testGetCarId() {
		Offer offer = new Offer();
		offer.setCarId(11);
		assertEquals(11, offer.getCarId());
	}

	@Test
	void testGetUserId() {
		Offer offer = new Offer();
		offer.setUserId(100);
		assertEquals(100, offer.getUserId());
	}

	@Test
	void testGetAskingPrice() {
		Offer offer = new Offer();
		offer.setAskingPrice(25000);
		assertEquals(25000, offer.getAskingPrice());
	}

	@Test
	void testGetOfferMade() {
		Offer offer = new Offer();
		offer.setOfferMade(12345.22);
		assertEquals(12345.22, offer.getOfferMade());
	}

	@Test
	void testGetApr() {
		Offer offer = new Offer();
		offer.setApr(.17);
		assertEquals(.17, offer.getApr());
	}

	@Test
	void testGetYears() {
		Offer offer = new Offer();
		offer.setYears(4);
		assertEquals(4, offer.getYears());
	}

	@Test
	void testGetTotalInterest() {
		Offer offer = new Offer();
		offer.setTotalInterest(2356.12);
		assertEquals(2356.12, offer.getTotalInterest());
	}

	@Test
	void testGetTotalProfit() {
		Offer offer = new Offer();
		offer.setTotalProfit(1456.32);
		assertEquals(1456.32, offer.getTotalProfit());
	}

	@Test
	void testGetMonthlyPayment() {
		Offer offer = new Offer();
		offer.setMonthlyPayment(523.12);
		assertEquals(523.12, offer.getMonthlyPayment());
	}
}
