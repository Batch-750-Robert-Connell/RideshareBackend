package com.revature.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.revature.beans.Mail;
import com.revature.beans.User;

@Service
public class EmailSenderService {
	private static final String EMAIL_SIMPLE_TEMPLATE_NAME = "email-template.html";
	private static final String EMAIL_VERIFY_TEMPLATE_NAME = "email-verify.html";
	
	@Autowired
	private JavaMailSender emailsender;
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	
	public void sendSimpleMail(User userReq, final String recipientEmail)
	        throws MessagingException {

	        // Prepare the evaluation context
	        final Context ctx = new Context();
	        User user = userReq;
	        ctx.setVariable("user", user);	       
	        ctx.setVariable("subscriptionDate", new Date());	        

	        // Prepare message using a Spring helper
	        final MimeMessage mimeMessage = this.emailsender.createMimeMessage();
	        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
	        message.setSubject("Example HTML email (simple)");
	        message.setFrom("revaturerideshareapp@gmail.com");
	        message.setTo(recipientEmail);

	        // Create the HTML body using Thymeleaf
	        final String htmlContent = this.templateEngine.process(EMAIL_SIMPLE_TEMPLATE_NAME, ctx);
	        message.setText(htmlContent, true /* isHtml */);

	        // Send email
	        this.emailsender.send(mimeMessage);
	    }
	
	/*
	 * Send email to user to verify his/her email
	 */
	public void sendVerifyEmail(User userRegister, final String recipientEmail)
	        throws MessagingException {

	        // Prepare the evaluation context
	        final Context ctx = new Context();
	        User user = userRegister;
	        ctx.setVariable("user", user);	       
	        ctx.setVariable("subscriptionDate", new Date());	        

	        // Prepare message using a Spring helper
	        final MimeMessage mimeMessage = this.emailsender.createMimeMessage();
	        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
	        message.setSubject("Verify your Rideshare email");
	        message.setFrom("revaturerideshareapp@gmail.com");
	        message.setTo(recipientEmail);

	        // Create the HTML body using Thymeleaf
	        final String htmlContent = this.templateEngine.process(EMAIL_VERIFY_TEMPLATE_NAME, ctx);
	        message.setText(htmlContent, true /* isHtml */);

	        // Send email
	        this.emailsender.send(mimeMessage);
	    }
	
	


}
