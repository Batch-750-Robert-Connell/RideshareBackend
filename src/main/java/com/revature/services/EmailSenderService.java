package com.revature.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.revature.beans.Mail;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender emailsender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	
	public void sendEmail(Mail mail) throws MessagingException, IOException{
		MimeMessage message = emailsender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariables(mail.getProps());
		
		String html = templateEngine.process("email-template.html", context);
		helper.setTo(mail.getMailTo());
		helper.setText(html,true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());
		
		emailsender.send(message);
		
		
	}
	
	@Bean
    public JavaMailSender javaMailService() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	      
	    mailSender.setUsername("revaturerideshareapp@gmail.com");
	    mailSender.setPassword("revature0302");
	      
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	      
	    return mailSender;
    }


}
