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
public static void main(String [] args)
{    
   String from = "rmaitipe@yahoo.com";
   String pass = "yahoo5%";
   String to = "brmaitipe@gmail.com";
   String host = "smtp.mail.yahoo.com";
   
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
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
      message.setSubject("This is the Subject Line");
      message.setText("This is the message body");
      
      Transport transport = session.getTransport("smtp");
      transport.connect(host, from, pass);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
      System.out.println("Message sent successfully....");
   }catch (MessagingException mex) {
      mex.printStackTrace();
   }
}
}