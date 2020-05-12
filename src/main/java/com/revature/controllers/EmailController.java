package com.revature.controllers;


import java.util.Random;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.services.EmailSenderService;
import com.revature.services.UserService;

@RestController
@CrossOrigin
public class EmailController  {
	
	private static Logger log = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private EmailSenderService emailService;
	
	@Autowired
	private UserService us;
	
	@PostMapping("/email")
	 public String requestDriverEmail(
			 		@RequestParam("Driver_Id") final int driverId,
			 		@RequestParam("User_Id") final int userId)
			 				throws MessagingException {
		
		
		log.info("Sending Email to request driver");
		User driver = us.getUserById(driverId);
		User user = us.getUserById(userId);

	
		String recipientEmail = driver.getEmail();
		//valio123@yahoo.com

        this.emailService.sendRequestHtmlEmail(user,driver, recipientEmail);
        log.info("Email sent");
        return "redirect:sent.html";
}
	
	@GetMapping("/approve")
	public String approvedEmail(@RequestParam("Driver_Id") final int driverId,
	 		@RequestParam("User_Id") final int userId) throws MessagingException {
		
		log.info("sending approved request message to user");
		User driver = us.getUserById(driverId);
		User user = us.getUserById(userId);
		String recipientEmail = user.getEmail();
		this.emailService.sendApprovedHtmlEmail(user, driver, recipientEmail);
        log.info("message sent");
		return "redirect:sent.approve";
	}
	
	@GetMapping("/decline")
	public String declineEmail(@RequestParam("id") int userId)throws MessagingException{
		log.info("sending denied request message to user");
		User user = us.getUserById(userId);
		String recipientEmail = user.getEmail();
		this.emailService.sendDeclineEmail(user, recipientEmail);
	    log.info("message sent");
		return "redirect:sent.decline";
	}
	
	public String generateStringPathVariable() { 
		
		 int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (random.nextFloat() * (rightLimit - leftLimit + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String generatedString = buffer.toString();	    
		    
		return generatedString;
	}
	
	
}
