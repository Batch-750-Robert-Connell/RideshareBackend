package com.revature.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.beans.Batch;
import com.revature.beans.Reservation;
import com.revature.beans.User;
import com.revature.repositories.ReservationRepository;
import com.revature.services.MD5Service;
import com.revature.services.UserService;

public class MD5ServiceTest {
	
	
	
	
	@Test
	public void testGetMD5() {
		String mockInput = "testString";
		String actual = MD5Service.getMd5(mockInput);
		String expected = "536788f4dbdffeecfbb8f350a941eea3";
		assertEquals(expected, actual);
	}

}
