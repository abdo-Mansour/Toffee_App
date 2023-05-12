package Customer.CustomerAuthentication;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

public class SendMail {
    public static void main(String[] args) {

        // Recipient's email address
        String to = "abdulrahmanmfam2003@gmail.com";
  
        // Sender's email address
        String from = "abdfawzy2008@gmail.com";
  
        // Sender's email password
        String password = "password";
  
        // SMTP server URL
        String host = "smtp.example.com";
  
        // Create properties object
        Properties properties = System.getProperties();
  
        // Set SMTP server URL
        properties.setProperty("mail.smtp.host", host);
  
        // Set email authentication
        properties.setProperty("mail.smtp.auth", "true");
  
        // Create session object
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(from, password);
           }
        });
  
        try {
           // Create MimeMessage object
           MimeMessage message = new MimeMessage(session);
  
           // Set From field
           message.setFrom(new InternetAddress(from));
  
           // Set To field
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
  
           // Set email subject
           message.setSubject("Test Email");
  
           // Set email message
           message.setText("This is a test email message.");
  
           // Send email message
           Transport.send(message);
  
           System.out.println("Email sent successfully.");
        } catch (MessagingException mex) {
           mex.printStackTrace();
        }
    }
    
}
