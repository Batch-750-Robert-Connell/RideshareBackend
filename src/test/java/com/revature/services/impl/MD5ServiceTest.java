package com.revature.services.impl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.revature.services.MD5Service;

public class MD5ServiceTest {
	
	
	
	
	@Test
	public void testGetMD5() {
		String mockInput = "testString";
		String actual = MD5Service.getMd5(mockInput);
		String expected = "536788f4dbdffeecfbb8f350a941eea3";
		assertEquals(expected, actual);
	}

}
