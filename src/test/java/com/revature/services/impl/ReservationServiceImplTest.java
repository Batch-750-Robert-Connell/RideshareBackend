package com.revature.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.Batch;
import com.revature.beans.Reservation;
import com.revature.beans.User;
import com.revature.repositories.ReservationRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;

@RunWith(SpringRunner.class)
public class ReservationServiceImplTest {
	
	@InjectMocks
	private ReservationServiceImpl rsi;
	
	@Mock
	private ReservationRepository rr;
	
	@Mock
	private UserService us;
	
	
	
	
	@Test
	public void testAddReservation() {
		User user = new User(1, "userName", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation expected = new Reservation(1, "07-07-2020", driver, user, 1);
		when(rr.save(expected)).thenReturn(expected);
		Reservation actual = rsi.addReservation(expected);
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testUpdateReservation() {
		User user = new User(1, "userName", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation expected = new Reservation(1, "07-07-2020", driver, user, 1);
		when(rr.save(expected)).thenReturn(expected);
		Reservation actual = rsi.addReservation(expected);
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testGetReservationById() {
		User user = new User(1, "userName", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation expected = new Reservation(1, "07-07-2020", driver, user, 1);
		when(rr.getOne(1)).thenReturn(expected);
		Reservation actual = rsi.getReservationById(1);
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testGetReservationByRiderId() {
		User user = new User(1, "userName", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation expected = new Reservation(1, "07-07-2020", driver, user, 1);
		when(rr.getReservationByRiderId(1)).thenReturn(expected);
		Reservation actual = rsi.getReservationByRiderId(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetReservationByDriverId() {
		User user = new User(1, "userName", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation expected = new Reservation(1, "07-07-2020", driver, user, 1);
		when(rr.getReservationByDriverId(2)).thenReturn(expected);
		Reservation actual = rsi.getReservationByDriverId(2);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetReservationByRiderIdAndTravelDate() {
		User user = new User(1, "userName", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation expected = new Reservation(1, "07-07-2020", driver, user, 1);
		when(rr.getReservationByDriverIdAndTravelDate(2, "07-07-2020")).thenReturn(expected);
		Reservation actual = rsi.getReservationByDriverIdAndTravelDate(2, "07-07-2020");
		assertEquals(expected, actual);
	}


}
