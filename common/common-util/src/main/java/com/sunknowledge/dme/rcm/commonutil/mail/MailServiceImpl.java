package com.sunknowledge.dme.rcm.commonutil.mail;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.StringWriter;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    public JavaMailSender getMailSender() {
        return mailSender;
    }
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Override
    public ServiceOutcome<String> sendPurchaseOrderDocumentToVendor(String email, String attachmentFilePath) {
        try {
            log.info("=================Mail ID====================="+email);
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            String mailSubject = "Send mail purchase order document to vendor";
            message.setFrom("fax.inbox@sunknowledgeinc.com");
            message.setTo(email);
            message.setSubject(mailSubject);
            Template template = velocityEngine.getTemplate("./templates/vm/" + "velocityTemplatePOToVendor.vm");

            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("firstName", "");
            StringWriter stringWriter = new StringWriter();
            template.merge(velocityContext, stringWriter);

            BodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart.setContent(stringWriter.toString(),"text/html");
            multipart.addBodyPart(messageBodyPart);
            mimeMessage.setContent(multipart, "text/html");

            log.info("===========attachmentFilePath====================="+attachmentFilePath);

            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentFilePath);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(source.getName());

            // Add the attachment to the multipart message
            multipart.addBodyPart(attachmentBodyPart);

            mailSender.send(mimeMessage);
            System.out.println("success");
            return new ServiceOutcome<>("Success", true, "Mail has sent successfully!!!");
        } catch (MessagingException e1) {
            e1.printStackTrace();
            System.err.println("Mail not sent");
            return new ServiceOutcome<>("Failed", false, "Failed to sent Mail!!!");
        }
    }
}
