package com.revature.controllers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Batch;
import com.revature.beans.Reservation;
import com.revature.beans.User;
import com.revature.services.EmailSenderService;
import com.revature.services.ReservationService;
import com.revature.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmailController.class)
public class EmailControllerTest {
	
	@Autowired
	private ObjectMapper om;
	
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService us;
	
	@MockBean
	private ReservationService rs;
	
	@MockBean
	private EmailSenderService ess; 
	
	
	/** 
	 * @throws Exception
	 */
	@Test 
	public void testApproveEmail() throws Exception {

		User user = new User(1, "userName", new Batch(), "jordan", "morgan", "youcanthavemyemail@gmail.com", "867-506-789", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "jordan", "morgan", "youcanthavemyemail2@gmail.com", "867-506-789", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation reservation =  new Reservation(1, "07-07-2020", driver, user, 1);
		when(rs.getReservationById(1)).thenReturn(reservation);

		mvc.perform(get("/approve")
			.param("Driver_Id", "2")
			.param("User_Id", "1")
			.param("Reservation_Id", "1"))
			.andExpect(status().is3xxRedirection());
	}
	
	
	
	/** 
	 * @throws Exception
	 */
	@Test 
	public void testDeclineEmail() throws Exception {

		User user = new User(1, "userName", new Batch(), "jordan", "morgan", "youcanthavemyemail@gmail.com", "867-506-789", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "jordan", "morgan", "youcanthavemyemail2@gmail.com", "867-506-789", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation reservation =  new Reservation(1, "07-07-2020", driver, user, 1);
		when(rs.getReservationById(1)).thenReturn(reservation);

		mvc.perform(get("/decline")
			.param("id", "1")
			.param("Reservation_Id", "1"))
			.andExpect(status().is3xxRedirection());
	}
	
	
	/** 
	 * @throws Exception
	 */
	@Test 
	public void testDeclineRequestDash() throws Exception {

		User user = new User(1, "userName", new Batch(), "jordan", "morgan", "youcanthavemyemail@gmail.com", "867-506-789", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "jordan", "morgan", "youcanthavemyemail2@gmail.com", "867-506-789", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation reservation =  new Reservation(1, "07-07-2020", driver, user, 1);
		when(rs.getReservationById(1)).thenReturn(reservation);

		mvc.perform(get("/declineDash")
			.param("id", "1")
			.param("Reservation_Id", "1"))
			.andExpect(status().isOk());
	}
	
	/** 
	 * @throws Exception
	 */
	@Test 
	public void testApproveRequestDash() throws Exception {

		User user = new User(1, "userName", new Batch(), "jordan", "morgan", "youcanthavemyemail@gmail.com", "867-506-789", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "jordan", "morgan", "youcanthavemyemail2@gmail.com", "867-506-789", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation reservation =  new Reservation(1, "07-07-2020", driver, user, 1);
		when(rs.getReservationById(1)).thenReturn(reservation);

		mvc.perform(get("/approveDash")
			.param("Driver_Id", "2")
			.param("User_Id", "1")
			.param("Reservation_Id", "1"))
			.andExpect(status().isOk());
	}
	

	/** 
	 * @throws Exception
	 */
	@Test 
	public void testRequestDriverEmail() throws Exception {
		User user = new User(1, "userName", new Batch(), "jordan", "morgan", "youcanthavemyemail@gmail.com", "867-506-789", true);
		when(us.getUserById(1)).thenReturn(user);
		User driver = new User(2, "userName2", new Batch(), "jordan", "morgan", "youcanthavemyemail2@gmail.com", "867-506-789", true);
		when(us.getUserById(2)).thenReturn(driver);
		Reservation reservation =  new Reservation(1, "07-07-2020", driver, user, 1);
		when(rs.getReservationById(1)).thenReturn(reservation);
		mvc.perform(get("/email")
			.param("Driver_Id", "2")
			.param("User_Id", "1")
			.param("Reservation_Id", "1"))
			.andExpect(status().isOk());
	}
	
	
	
}
