package app.Tasks.QuartzUtil;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import app.Utils.ConfigUtils;

public class NotificationEmail {
	private static final String SMTP_HOST_NAME = ConfigUtils.getAppProperty("task.email.mailServer");
	private static final int SMTP_HOST_PORT = Integer.parseInt(ConfigUtils.getAppProperty("task.email.mailServerPort", "465"));
	private static final String SMTP_AUTH_USER = ConfigUtils.getAppProperty("task.email.user");
	private static final String SMTP_AUTH_PWD  = ConfigUtils.getAppProperty("task.email.password");
	private static final String SEND_TO = ConfigUtils.getAppProperty("task.email.sendTo");


	private static  void addAtachments(String[] attachments, Multipart multipart) throws MessagingException, AddressException 
	{ 
		for(int i = 0; i<= attachments.length -1; i++) 
		{ 
			String filename = attachments[i];
			MimeBodyPart attachmentBodyPart = new MimeBodyPart(); 

//			use a JAF FileDataSource as it does MIME type detection 
			DataSource source = new FileDataSource(filename); 
			attachmentBodyPart.setDataHandler(new DataHandler(source)); 

//			assume that the filename you want to send is the same as the 
//			actual file name - could alter this to remove the file path 
			attachmentBodyPart.setFileName(filename); 

//			add the attachment 
			multipart.addBodyPart(attachmentBodyPart); 
		} 
	} 

	public static void sendTaskNotificationMail (String subject, String messageBody, String[] attachments) throws 
	MessagingException, AddressException {
		sendMail(SEND_TO, subject, messageBody, attachments);
	}
	
	
	public static void sendMail(String to, String subject, String messageBody, String[] attachments) throws 
	MessagingException, AddressException {
//		Setup mail server 
		Properties properties = System.getProperties(); 
		properties.put("mail.transport.protocol", "smtps");
		properties.put("mail.smtps.host", SMTP_HOST_NAME);
		properties.put("mail.smtps.auth", "true");

//		Get a mail session 
		Session session = Session.getDefaultInstance(properties, null);
		Transport transport = session.getTransport();

//  	Define a new mail message 
    	Message message = new MimeMessage(session); 
//    	message.setFrom(new InternetAddress(SMTP_AUTH_USER)); 
    	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
    	message.setSubject(subject); 

//  	Create a message part to represent the body text 
    	BodyPart messageBodyPart = new MimeBodyPart(); 
    	messageBodyPart.setText(messageBody); 

//  	use a MimeMultipart as we need to handle the file attachments 
    	Multipart multipart = new MimeMultipart(); 

//  	add the message body to the mime message 
    	multipart.addBodyPart(messageBodyPart); 

    	if (attachments != null) {
//  		add any file attachments to the message 
    		addAtachments(attachments, multipart);
    	}
    	
//  	Put all message parts in the message 
    	message.setContent(multipart); 

    	transport.connect (SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

    	transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
    	transport.close();

    } 

}
