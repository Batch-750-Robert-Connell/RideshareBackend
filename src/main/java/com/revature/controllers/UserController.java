package com.revature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.maps.errors.ApiException;
import com.revature.beans.User;
import com.revature.services.BatchService;
import com.revature.services.DistanceService;
import com.revature.services.EmailSenderService;
import com.revature.services.MD5Service;
import com.revature.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * UserController takes care of handling our requests to /users.
 * It provides methods that can perform tasks like all users, user by role (true or false), user by username,
 * user by role and location, add user, update user and delete user by id. 
 * 
 * @author Adonis Cabreja
 *
 */

@RestController
@RequestMapping("/users")
@CrossOrigin
@Api(tags= {"User"})
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService us;
	
	@Autowired
	private BatchService bs;
	
	@Autowired
	private DistanceService ds;

	@Autowired
	private EmailSenderService emailService;
	
	@Autowired
	private MD5Service md5;
	

	/**
	 * HTTP GET method (/users)
	 * 
	 * @param isDriver represents if the user is a driver or rider.
	 * @param username represents the user's username.
	 * @param location represents the batch's location.
	 * @return A list of all the users, users by is-driver, user by username and users by is-driver and location.
	 */
	
	

	/**
	 * Method below has been deprecated. For complexity and the requirement of the google api key.
	 */
   
//	@ApiOperation(value="Returns user drivers", tags= {"User"})
//	@GetMapping("/driver/{address}")
//	public List <User> getTopFiveDrivers(@PathVariable("address")String address) throws ApiException, InterruptedException, IOException {
//		//List<User> aps =  new ArrayList<User>();
//		List<String> destinationList = new ArrayList<String>();
//		String [] origins = {address};
//	    Map<String, User> topfive = new HashMap<String, User>();
//		for(User d : us.getActiveDrivers()) {
//			String add = d.gethAddress();
//			String city = d.gethCity();
//			String state = d.gethState();
//			String fullAdd = add + ", " + city + ", " + state;
//			destinationList.add(fullAdd);
//			topfive.put(fullAdd, d);
//	}	
//		String [] destinations = new String[destinationList.size()];
//	destinations = destinationList.toArray(destinations);
//	return	ds.distanceMatrix(origins, destinations);
//	
//	}
	
	/**
	 * HTTP GET method (/users)
	 * 
	 * @param isDriver represents if the user is a driver or rider.
	 * @param username represents the user's username.
	 * @param location represents the batch's location.
	 * @return A list of all the users, users by is-driver, user by username and users by is-driver and location.
	 */
	
	@ApiOperation(value="Returns all users", tags= {"User"}, notes="Can also filter by is-driver, location and username")
	@GetMapping
	public List<User> getUsers(@RequestParam(name="is-driver",required=false)Boolean isDriver,
							   @RequestParam(name="username",required=false)String username,
							   @RequestParam(name="location", required=false)String location) {
		
		if (isDriver != null && location != null) {
			return us.getUserByRoleAndLocation(isDriver.booleanValue(), location);
		} else if (isDriver != null) {
			return us.getUserByRole(isDriver.booleanValue());
		} else if (username != null) {
			return us.getUserByUsername(username);
		}
		
		return us.getUsers();
	}
	
	/**
	 * HTTP GET (users/{id})
	 * 
	 * @param id represents the user's id.
	 * @return A user that matches the id.
	 */
	
	@ApiOperation(value="Returns user by id", tags= {"User"})
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id")int id) {
		
		return us.getUserById(id);
	}
	
	
	/**
	 * Update emailVerified field
	 * @param id represents the user's id.
	 */
	
	@ApiOperation(value="Update emailVerified for user by id", tags= {"User"})
	@GetMapping("/verify-email")

	public RedirectView updateEmailUserById(@RequestParam("id")int id,@RequestParam("token") String token) {

		User user = us.getUserById(id);
		user.setEmailVerified(true);
		us.updateUser(user);
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("http://34.238.165.243/rideshare-frontend/");
	    return redirectView;
		
	}
	
	
	/**
	 * HTTP POST method (/users)
	 * 
	 * @param user represents the new User object being sent.
	 * @return The newly created object with a 201 code.
	 * 
	 * Sends custom error messages when incorrect input is used
	 * @throws MessagingException 
	 */
	
	@ApiOperation(value="Adds a new user", tags= {"User"})
	@PostMapping
	public String addUser(@Valid @RequestBody User user, BindingResult bindingResult) throws MessagingException {
		
		if (bindingResult.hasErrors()) {
			return bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage();
		} else {
				log.info("Email sending to verify email");
				user.setBatch(bs.getBatchByNumber(user.getBatch().getBatchNumber()));
		 		us.addUser(user);
		 				 				 		
		 		String token = MD5Service.getMd5(new Date().toString());
		 		
		 		// Send email verification when new user registers
		 		this.emailService.sendVerifyEmail(user, user.getEmail(), token);
		        log.info("Email send");		 		
		 	
		    return "{}";
		}	
		
	}
	
	/**
	 * HTTP PUT method (/users)
	 * 
	 * @param user represents the updated User object being sent.
	 * @return The newly updated object.
	 * @throws MessagingException 
	 */
	
	@ApiOperation(value="Updates user by id", tags= {"User"})
	@PutMapping("/{id}")
	public User updateUser(@Valid @RequestBody User user) throws MessagingException {
		int id = user.getUserId();
		User oldUser = us.getUserById(id);
		String oldEmail = oldUser.getEmail();
		us.updateUser(user);
		String newEmail = user.getEmail();
		if(!(oldEmail.equals(newEmail))) {
			user.setEmailVerified(false);
			String token = MD5Service.getMd5(new Date().toString());
			this.emailService.verifyUpdatedEmail(user, user.getEmail(), token);
		}
		return us.updateUser(user);
	}
	
	/**
	 * HTTP DELETE method (/users)
	 * 
	 * @param id represents the user's id.
	 * @return A string that says which user was deleted.
	 */
	
	@ApiOperation(value="Deletes user by id", tags= {"User"})
	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable("id")int id) {
		
		return us.deleteUserById(id);
	}
	

	
	
}
