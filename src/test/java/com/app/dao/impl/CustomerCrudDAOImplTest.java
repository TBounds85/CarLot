package com.app.dao.impl;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CustomerCrudDAOImplTest {

	@InjectMocks
	private static CustomerCrudDAOImpl CustCrud;

	@Mock
	private static CustomerCrudDAOImpl customerCrudDAOImpl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CustCrud = new CustomerCrudDAOImpl();	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testViewCar() {
		int carId = 0;
		Mockito.when(customerCrudDAOImpl.viewCar(carId)).thenReturn(false);
		assertFalse(CustCrud.viewCar(carId));
	}
		
		
	

}
