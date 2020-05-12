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

import com.revature.beans.Reservation;
import com.revature.beans.User;
import com.revature.services.EmailSenderService;
import com.revature.services.ReservationService;
import com.revature.services.UserService;

@RestController
@CrossOrigin
public class EmailController  {
	
	private static Logger log = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private EmailSenderService emailService;
	
	@Autowired
	private UserService us;
	
	@Autowired
	private ReservationService rs;
	
	@GetMapping("/email")
	 public String requestDriverEmail(
			 		@RequestParam("Driver_Id") final int driverId,
			 		@RequestParam("User_Id") final int userId,
			 		@RequestParam("Reservation_Date") final String resDate)
			 				throws MessagingException {
		
		
		log.info("Sending Email to request driver");
		User driver = us.getUserById(driverId);
		User user = us.getUserById(userId);
		Reservation reservation = new Reservation(0, resDate, driver, user, 1);
		reservation = rs.addReservation(reservation);
	
		String recipientEmail = driver.getEmail();
		//valio123@yahoo.com

        this.emailService.sendRequestHtmlEmail(user,driver, reservation, recipientEmail);
        log.info("Email send");
        return "redirect:sent.html";
}
	@GetMapping("/approve")
	public String approvedEmail(@RequestParam("Driver_Id") final int driverId,
	 		@RequestParam("User_Id") final int userId, @RequestParam("Reservation_Id") int reservationId) throws MessagingException {
		
		User driver = us.getUserById(driverId);
		User user = us.getUserById(userId);
		Reservation reservation = rs.getReservationById(reservationId);
		reservation.setStatus(2);
		reservation = rs.updateReservation(reservation);
		String recipientEmail = user.getEmail();
		this.emailService.sendApprovedHtmlEmail(user, driver, reservation, recipientEmail);
		return "redirect:sent.approve";
	}
	
	@GetMapping("/decline")
	public String declineEmail(@RequestParam("id") int userId, @RequestParam("Reservation_Id") int reservationId)throws MessagingException{
		User user = us.getUserById(userId);
		String recipientEmail = user.getEmail();
		Reservation reservation = rs.getReservationById(reservationId);
		reservation.setStatus(3);
		reservation = rs.updateReservation(reservation);
		this.emailService.sendDeclineEmail(user, reservation, recipientEmail);
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
