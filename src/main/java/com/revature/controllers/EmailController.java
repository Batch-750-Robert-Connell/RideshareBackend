package com.revature.controllers;


import java.util.Date;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
	
	
	/** 
	 * @param requestDriverEmail(
	 * @return String
	 * @throws MessagingException
	 */
	@GetMapping("/email")
	 public String requestDriverEmail(
			 		@RequestParam("Driver_Id") final int driverId,
			 		@RequestParam("User_Id") final int userId)
			 				throws MessagingException {
		
		log.info("Sending Email to request driver");
		User driver = us.getUserById(driverId);
		User user = us.getUserById(userId);
		Reservation reservation = new Reservation(0, new Date().toString(), driver, user, 1);
		reservation = rs.addReservation(reservation);
		String recipientEmail = driver.getEmail();
		
    this.emailService.sendRequestHtmlEmail(user,driver, reservation, recipientEmail);
    log.info("Email send");
    return "Sent email to driver";
}
	
	
	/** 
	 * @param @RequestParam("Driver_Id"
	 * @return RedirectView
	 */
	@GetMapping("/approve")
	public RedirectView approvedEmail(@RequestParam("Driver_Id") final int driverId,
	 		@RequestParam("User_Id") final int userId, @RequestParam("Reservation_Id") int reservationId) throws MessagingException {
		
		log.info("sending approved request message to user");
		User driver = us.getUserById(driverId);
		User user = us.getUserById(userId);
		Reservation reservation = rs.getReservationById(reservationId);
		reservation.setStatus(2);
		reservation = rs.updateReservation(reservation);
		String recipientEmail = user.getEmail();
		this.emailService.sendApprovedHtmlEmail(user, driver, reservation, recipientEmail);
		log.info("message sent");
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("http://34.238.165.243/");
	    return redirectView;

	}
	
	/**
	 * This method is for the dashboard approval process. When A driver wants to approve a request.
	 * @param driverId
	 * @param userId
	 * @param reservationId
	 * @return
	 * @throws MessagingException
	 */
	@GetMapping("/approveDash")
	public String approvedRequestDash(@RequestParam("Driver_Id") final int driverId,
	 		@RequestParam("User_Id") final int userId, @RequestParam("Reservation_Id") int reservationId) throws MessagingException {
		
		log.info("sending approved request message to user");
		User driver = us.getUserById(driverId);
		User user = us.getUserById(userId);
		Reservation reservation = rs.getReservationById(reservationId);
		reservation.setStatus(2);
		reservation = rs.updateReservation(reservation);
		String recipientEmail = user.getEmail();
		this.emailService.sendApprovedHtmlEmail(user, driver, reservation, recipientEmail);
		log.info("message sent");
	    return "approved";

	}
	
	
	/** 
	 * @param userId
	 * @param reservationId
	 * @return RedirectView
	 * @throws MessagingException
	 */
	@GetMapping("/decline")
	public RedirectView declineEmail(@RequestParam("id") int userId, @RequestParam("Reservation_Id") int reservationId)throws MessagingException{
    log.info("sending denied request message to user");
		User user = us.getUserById(userId);
		String recipientEmail = user.getEmail();
		Reservation reservation = rs.getReservationById(reservationId);
		reservation.setStatus(3);
		reservation = rs.updateReservation(reservation);
		this.emailService.sendDeclineEmail(user, reservation, recipientEmail);
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("http://34.238.165.243/");
	    return redirectView;
	}
	
	/**
	 * This method is for the dashboard denied process. When A driver wants to deny a request.
	 * @param userId
	 * @param reservationId
	 * @return
	 * @throws MessagingException
	 */
	@GetMapping("/declineDash")
	public String declineRequestDash(@RequestParam("id") int userId, @RequestParam("Reservation_Id") int reservationId)throws MessagingException{
    log.info("sending denied request message to user");
		User user = us.getUserById(userId);
		String recipientEmail = user.getEmail();
		Reservation reservation = rs.getReservationById(reservationId);
		reservation.setStatus(3);
		reservation = rs.updateReservation(reservation);
		this.emailService.sendDeclineEmail(user, reservation, recipientEmail);
	
	    return "denied";
	}
	
	
}
