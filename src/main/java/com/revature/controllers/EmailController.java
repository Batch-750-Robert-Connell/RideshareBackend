package com.revature.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        @RequestParam("User_Id") final int UserId,
        final Locale locale)
        throws MessagingException {
		log.info("Sending Email to request driver");
		User driver = us.getUserById(driverId);
		User user = us.getUserById(UserId);
		String recipientName = user.getFirstName() + " " + user.getLastName();
		String recipientEmail = driver.getEmail();
		

        this.emailService.sendSimpleMail(recipientName, recipientEmail, locale);
        log.info("Email send");
        return "redirect:sent.html";


}
}
