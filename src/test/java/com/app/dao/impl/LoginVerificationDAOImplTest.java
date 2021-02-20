package com.app.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class LoginVerificationDAOImplTest {

	@InjectMocks
	private static LoginVerificationDAOImpl LV;

	@Mock
	private static LoginVerificationDAOImpl loginVerificationDAOImpl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		LV = new LoginVerificationDAOImpl();
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testVerifyUserId() throws Exception {
		String username = "customer";
		String password = "password";
		Mockito.when(loginVerificationDAOImpl.verifyUserId(username, password)).thenReturn(100);
		assertEquals(100, LV.verifyUserId(username, password));
	}

}
