package com.example.apps;

import java.util.Properties;
import java.util.Date;
import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.MessagingException;
import com.sun.mail.smtp.SMTPMessage;
import org.simplejavamail.email.Email;

public class JakartaMail {
	
	private class MyMessagingException extends MessagingException{}
	
	public static void main(String argv[]) {
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "my-mail-server");
	    Session session = Session.getInstance(props, null);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom("me@example.com");
	        msg.setRecipients(Message.RecipientType.TO, "you@example.com");
	        msg.setSubject("JavaMail hello world example");
	        msg.setSentDate(new Date());
	        msg.setText("Hello, world!\n");
	        Transport.send(msg, "me@example.com", "my-password");
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }
	}
}