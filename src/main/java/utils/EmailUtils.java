package utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

	public static void sendTestReport(String reportPath) {

		final String senderEmail = "onkeshtestautomation@gmail.com";
		final String appPassword = "qquljmcoduyfqmrw";
		final String recipientEmail = "onkeshtestautomation@gmail.com";

		// SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");

		// create session with authentication
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, appPassword);
			}
		});
		session.setDebug(true);

		try {
			// create Email Message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Test Email From QA Automation");
			// message.setText("Hello \n This is a test email from Java \n Regards, \nQA
			// Team");

			// email body part
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Hello \n\n This is a test email from Java \n\n Regards, \nQA Team");

			// attachment part
			MimeBodyPart attachmentPart = new MimeBodyPart();
			// String filePath = System.getProperty("user.dir") +
			// "/reports/ExtentReport.html";
			attachmentPart.attachFile(new File(reportPath));

			// combine body and attachment parts

			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);

			// Send Email
			Transport.send(message);
			System.out.println("Email sent successfully ***");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
