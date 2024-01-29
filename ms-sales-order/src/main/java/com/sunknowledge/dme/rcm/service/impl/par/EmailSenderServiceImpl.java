package com.sunknowledge.dme.rcm.service.impl.par;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.service.par.EmailSenderService;

import reactor.core.publisher.Mono;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	@Override
	public Mono<String> sendParMail() throws MessagingException {
		// TODO Auto-generated method stub

		String fromEmail = "fax.inbox@sunknowledgeinc.com";
		String password = "fmbn siwi drcn zfkq";
		String toEmail = "Arijit.Sharma@sunknowledge.com";
		String subject = "Test Email with PAR Attachment";
		String outCome = "Mail was not sent";

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(properties, authenticator);

		try {
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(subject);

			BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Testing Testing PAR Mail Testing");
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            
            String attachmentFilePath = "D:\\Project Data\\Doc_Path\\tmpDocumentsPAR_80_27.pdf";
            
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentFilePath);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(source.getName());

            // Add the attachment to the multipart message
            multipart.addBodyPart(attachmentBodyPart);
            
            message.setContent(multipart);

			Transport.send(message);

			System.out.println("Email sent successfully!");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return Mono.just(outCome);
	}

}
