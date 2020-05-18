package com.revature.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Batch;
import com.revature.beans.Reservation;
import com.revature.beans.User;
import com.revature.services.CarService;
import com.revature.services.ReservationService;
import com.revature.services.UserService;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper om;
	
	@MockBean
	private ReservationService rs;
	
	@MockBean
	private UserService us;
	
	@MockBean
	private CarService cs;
	
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetReservationByDriverId() throws Exception {
		User user = new User(1, "userName", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(2)).thenReturn(driver);
		List<Reservation> reservations = new ArrayList<>();
		reservations.add(new Reservation(1, "07-07-2020", driver, user, 1));
		reservations.add(new Reservation(2, "07-08-2020", driver, user, 1));
		when(rs.getReservationByDriverId(2)).thenReturn(reservations);
		mvc.perform(get("/reservations/driver")
			.param("id", "2"))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$", hasSize(2)));
	}

	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetReservationByDriverIdAndTravelDate() throws Exception {
		User user = new User(1, "userName", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation reservation = new Reservation(1, "07-07-2020", driver, user, 1);
		when(rs.getReservationByDriverIdAndTravelDate(2, "07-07-2020")).thenReturn(reservation);
		mvc.perform(get("/reservations/travel")
			.param("id", "2")
			.param("token", "07-07-2020"))
		   .andExpect(status().isOk());
	}
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGetReservationByRiderId() throws Exception {
		User user = new User(1, "userName", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(2)).thenReturn(driver);
		List<Reservation> reservations = new ArrayList<>();
		reservations.add(new Reservation(1, "07-07-2020", driver, user, 1));
		reservations.add(new Reservation(2, "07-08-2020", driver, user, 1));
		when(rs.getReservationByRiderId(1)).thenReturn(reservations);
		mvc.perform(get("/reservations/rider")
			.param("id", "1"))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$", hasSize(2)));
	}
	
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testAddReservation() throws Exception {
		User user = new User(1, "userName", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "umpa", "lumpa", "umpalumpahu@gmail.com", "847-555-1247", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation reservation = new Reservation(1, "07-07-2020", driver, user, 1);		
		when(rs.addReservation(new Reservation(1, "07-07-2020", driver, user, 1))).thenReturn(reservation);
		
		mvc.perform(post("/reservations").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reservation)))
		   .andExpect(status().isCreated())
		   .andExpect(jsonPath("$.status").value("1"));
	}
}
