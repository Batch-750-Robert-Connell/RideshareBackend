package com.revature.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.revature.services.EmailService;

@Component
public class EmailServiceImpl implements EmailService {
  
    @Autowired
    public JavaMailSender emailSender;
 
  public void sendSimpleMessage(String to, String subject, String text) {
        
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
       
    }

@Override
public void sendSimpleMessageUsingTemplate(String to, String subject, String... templateModel) {
	// TODO Auto-generated method stub
	
}
}