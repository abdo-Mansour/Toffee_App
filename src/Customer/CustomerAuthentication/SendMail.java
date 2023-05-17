package Customer.CustomerAuthentication;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
   private String email;
   private int code;


   SendMail(String email, int code){
       this.email = email;
       this.code = code;
   }

   public void send() {

      String to = email;

      String from = "abdfawzy2008@gmail.com";

      String host = "smtp.gmail.com";

      Properties properties = System.getProperties();

      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "465");
      properties.put("mail.smtp.ssl.enable", "true");
      properties.put("mail.smtp.auth", "true");

      Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

         protected PasswordAuthentication getPasswordAuthentication() {

               return new PasswordAuthentication("abdfawzy2008@gmail.com", "wmtccpuszmulhkki");

         }

      });

      

      try {
         
         MimeMessage message = new MimeMessage(session);

         
         message.setFrom(new InternetAddress(from));

         
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         
         message.setSubject("OTP for Toffee App");

         
         message.setText("Your OTP is: " + code);

         System.out.println("sending...");
         
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }

   }

}
