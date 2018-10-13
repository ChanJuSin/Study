package com.board.util.authEmail;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendAuthEmail {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendAuthCode(AuthEmail authEmail) throws Exception {
		MimeMessage msg = mailSender.createMimeMessage();
		msg.setSubject(authEmail.getSubject());
		msg.setText(authEmail.getContent(), "utf-8", "html");
		msg.setRecipient(RecipientType.TO, new InternetAddress(authEmail.getReceiver()));
		mailSender.send(msg);
	}
	
}
