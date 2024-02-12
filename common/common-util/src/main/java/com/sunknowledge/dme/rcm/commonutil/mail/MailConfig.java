package com.sunknowledge.dme.rcm.commonutil.mail;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender getMailSender() throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        String fromEmail = "fax.inbox@sunknowledgeinc.com";
        String password = "fmbn siwi drcn zfkq";
        // Using gmail.
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(fromEmail);
        mailSender.setPassword(password);

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");

        mailSender.setJavaMailProperties(javaMailProperties);
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(javaMailProperties, authenticator);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        return mailSender;
    }

    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty("resource.loader", "class");
        engine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return engine;
    }
}
