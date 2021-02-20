package com.app.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class GeneralServiceImplTest {

	@InjectMocks
	private static GeneralServiceImpl GS;

	@Mock
	private static GeneralServiceImpl generalServiceImpl;

	@BeforeAll
	public static void setUpBeforeAll() { 
		generalServiceImpl = new GeneralServiceImpl();
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCheckUsernameAvailability() throws Exception {
		String username = "TestPerson";
		ArrayList<String> al = new ArrayList<String>();
		al.add("person1");
		al.add("person2");
		al.add("person3");
		Mockito.when(generalServiceImpl.CheckUsernameAvailability(username)).thenReturn(false);
		assertFalse(GS.CheckUsernameAvailability(username));
	}

	@Test
	void testGetNextTransactionId() throws Exception {
		ArrayList<Integer> al = new ArrayList<>();
		al.add(1000);
		al.add(1001);
		al.add(1002);
		al.add(1003);
		Mockito.when(generalServiceImpl.getNextTransactionId()).thenReturn((al.get(al.size() - 1)) + 1);
		assertEquals(1004, GS.getNextTransactionId());
		
	}

	@Test
	void testCheckForCarIdInUse() {
		int carId = 1;
		ArrayList<Integer> al = new ArrayList<>();
		al.add(1);
		al.add(2);
		al.add(3);
		Mockito.when(generalServiceImpl.CheckForCarIdInUse(carId)).thenReturn(true);
		assertTrue(GS.CheckForCarIdInUse(carId));

	}
}
