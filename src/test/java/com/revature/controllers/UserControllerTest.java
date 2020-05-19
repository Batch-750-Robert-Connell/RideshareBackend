package com.revature.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Batch;
import com.revature.beans.User;
import com.revature.services.BatchService;
import com.revature.services.DistanceService;
import com.revature.services.EmailSenderService;
import com.revature.services.MD5Service;
import com.revature.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper om;
		
	@MockBean
	private UserService us;
	
	@MockBean
	private BatchService bs;
	
	@MockBean
	private DistanceService ds;
	
	@MockBean
	private EmailSenderService ess;
	
	@MockBean
	private MD5Service md5;
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGettingUsers() throws Exception {
		
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		when(us.getUsers()).thenReturn(users);
		
		mvc.perform(get("/users"))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$", hasSize(2)));
	}
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGettingUserById() throws Exception {
		
		User user = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789", true);
		user.setDriver(true);
		user.setActive(true);
		user.setAcceptingRides(true);
		when(us.getUserById(1)).thenReturn(user);
		
		mvc.perform(get("/users/{id}", 1))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$.userId").value(1));
	}
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGettingUserByUsername() throws Exception {
		
		List<User> users = new ArrayList<>();
		users.add(new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789", true));
		when(us.getUserByUsername("userName")).thenReturn(users);
		
		mvc.perform(get("/users?username=userName"))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$[0].userName").value("userName"));
	}
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGettingUserByRole() throws Exception {
		
		List<User> users = new ArrayList<>();
		User user = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789", true);
		user.setDriver(true);
		user.setActive(true);
		user.setAcceptingRides(true);
		users.add(user);
		when(us.getUserByRole(true)).thenReturn(users);
		mvc.perform(get("/users?is-driver=true"))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$[0].driver").value("true"));
	}
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testGettingUserByRoleAndLocation() throws Exception {		
		List<User> users = new ArrayList<>();
		User user = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789", true);
		user.setDriver(true);
		user.setActive(true);
		user.setAcceptingRides(true);
		users.add(user);
		when(us.getUserByRoleAndLocation(true, "location")).thenReturn(users);		
		mvc.perform(get("/users?is-driver=true&location=location"))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$[0].driver").value("true"));
	}
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testAddingUser() throws Exception {		
		Batch batch = new Batch(111, "address");
		User user = new User(1, "userName", batch, "adonis", "cabreja", "adonis@gmail.com", "123-456-789", true);
		user.setDriver(true);
		user.setActive(true);
		user.setAcceptingRides(true);		
		when(us.addUser(user)).thenReturn(user);		
		mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(user)))
		   .andExpect(status().isOk());
	}
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testAddingUser_2() throws Exception {		
		Batch batch = new Batch(111, "address");
		User user = new User(1, "userName", batch, "adonis", "cabreja", "gpichmann0@artisteer.com", "123-456-7899", true, true, true, "5 Carpenter Plaza", "New York City", "10277", "NY", "30401 Esker Point", "Des Moines", "50347", "IA", true);		
		when(us.addUser(user)).thenReturn(user);	
		mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(user)))
		   .andExpect(status().isOk());
	}
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testUpdatingUser() throws Exception {
		Batch batch = new Batch(111, "address");
		User user = new User(1, "userName", batch, "adonis", "cabreja", "gpichmann0@artisteer.com", "123-456-7899", true, true, true, "5 Carpenter Plaza", "New York City", "10277", "NY", "30401 Esker Point", "Des Moines", "50347", "IA", true);
		when(us.updateUser(user)).thenReturn(user);		
		when(us.getUserById(1)).thenReturn(user);		
		mvc.perform(put("/users/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(user)))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$.userName").value("userName"));
	}
	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testDeletingUser() throws Exception {		
		User user = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789", true);
		String returnedStr = "User with id: " + user.getUserId() + " was deleted.";
		when(us.deleteUserById(1)).thenReturn(returnedStr);		
		mvc.perform(delete("/users/{id}", 1))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$").value(returnedStr));
	}
}
