package Email;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailSSL {
	final static String username = "";
	final static String password = "";
	static Properties prop = new Properties();

	
	private static void initProp() {
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	}
	
	public static void send_email(String recipient, String subject, String body) {
		initProp();
		
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(recipient));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			System.out.println("\"" + message.getSubject() + "\" has been sent");
			
			
			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

}//ssalc