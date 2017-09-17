package gr.hua.katanemhmena.model;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public  void send2(List<Subscriber> subscribers) {

        final String username = "huatest93@gmail.com";
        final String password = "lolo91qaz";
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,new javax.mail.Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
        	for(Subscriber sub:subscribers){
        		Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("huatest93@gmail.com"));
	            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(sub.getEmail()));
	            message.setSubject("New Auction");
	            message.setText("Dear Subscriber,\n"+"A new auction has been arranged.\n"+"For more information visit our site Greek oil producers!!!");
	            Transport.send(message);
        	}
            System.out.println("Emails have been sent successfully");
        }catch (MessagingException e) {
        	throw new RuntimeException(e);
        }
    }
}