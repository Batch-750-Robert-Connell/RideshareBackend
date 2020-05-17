package com.revature.controllers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import com.revature.controllers.LoginController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Batch;
import com.revature.beans.Reservation;
import com.revature.beans.User;
import com.revature.services.BatchService;
import com.revature.services.DistanceService;
import com.revature.services.EmailSenderService;
import com.revature.services.ReservationService;
import com.revature.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	public void approveEmail() throws Exception {

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
	public void declineEmail() throws Exception {

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
	
//	@Test 
//	public void requestEmail() throws Exception {
//
//		User user = new User(1, "userName", new Batch(), "jordan", "morgan", "youcanthavemyemail@gmail.com", "867-506-789", true);
//		when(us.getUserById(1)).thenReturn(user);
//		User driver = new User(2, "userName2", new Batch(), "jordan", "morgan", "youcanthavemyemail2@gmail.com", "867-506-789", true);
//		when(us.getUserById(2)).thenReturn(driver);
//
//
//		mvc.perform(get("/email")
//			.param("Driver_Id", "2")
//			.param("User_Id", "1")
//			.andExpect(status().isOk());
//	}
}
