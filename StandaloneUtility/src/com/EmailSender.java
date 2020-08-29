package com;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/*
 * This class sends an email message
 * Reference article below to resolve javax.mail.AuthenticationFailedException
 * https://stackoverflow.com/questions/37938103/how-javax-mail-authenticationfailedexception-failed-to-connect-in-sending-mail
 * 
 */
public class EmailSender
{
	
	static final String FROM_ADDRESS = "rmaitipe@yahoo.com";
	static final String DUMMY_PWD = "yahoo5%";
	static final String TO_ADDRESS = "brmaitipe@gmail.com";
	static final String HOST = "smtp.mail.yahoo.com";
	   
	public static void main(String [] args)
	{    
	   // Setup mail server
	   Properties properties = System.getProperties();
	   properties.put("mail.smtp.starttls.enable", "true");
	   properties.put("mail.smtp.port", "587");
	   properties.put("mail.smtp.auth", "true");
	
	   // Get the default Session object.
	   Session session = Session.getDefaultInstance(properties);
	
	   try{
	      // Create a default MimeMessage object.
	      MimeMessage message = new MimeMessage(session);
	      message.setFrom(new InternetAddress(FROM_ADDRESS));
	      message.addRecipient(Message.RecipientType.TO,new InternetAddress(TO_ADDRESS));
	      message.setSubject("This is the Subject Line");
	      message.setText("This is the message body");
	      
	      Transport transport = session.getTransport("smtp");
	      transport.connect(HOST, FROM_ADDRESS, DUMMY_PWD);
	      transport.sendMessage(message, message.getAllRecipients());
	      transport.close();
	      System.out.println("Message sent successfully....");
	   }catch (MessagingException mex) {
	      mex.printStackTrace();
	   }
	}
	
}