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
import com.revature.beans.User;
import com.revature.services.BatchService;
import com.revature.services.DistanceService;
import com.revature.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

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
	

	
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void failedLogin() throws Exception {
		mvc.perform(get("/login/?userName=gpichmann0&passWord=password"))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("userNotFound[0]").value("User not found!"));
	}
    
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void successfulLogin() throws Exception {
		 
		User user = new User(1, "userName", new Batch(), "jordan", "morgan", "youcanthavemyemail@gmail.com", "867-506-789", true);
		List<User> test = new ArrayList<>();
		test.add(user);
		when(us.getUserByUsername("userName")).thenReturn(test);

		mvc.perform(get("/login/?userName=userName&passWord="))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("name[0]").value("jordan morgan"));
	}
	
}
