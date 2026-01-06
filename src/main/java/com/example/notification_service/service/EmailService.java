package com.example.notification_service.service;

import com.example.notification_service.model.MailConfig;
import com.example.notification_service.repository.MailConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private MailConfigRepository repository;

    public void sendDynamicEmail(String to, String subject, String body) throws Exception {
        System.out.println("DEBUG: Fetching config from MongoDB...");
        
        MailConfig config = repository.findFirstBy()
                .orElseThrow(() -> new RuntimeException("DB Configuration Missing"));

        System.out.println("DEBUG: Found Config for User: " + config.getMailUser());
        // ... rest of your code
        // Fetch from MongoDB
        // MailConfig config = repository.findFirstBy()
        //         .orElseThrow(() -> new RuntimeException("DB Configuration Missing"));

        // Create Mailer at Runtime
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(config.getSmtpHost());
        mailSender.setPort(config.getSmtpPort());
        mailSender.setUsername(config.getMailUser());
        mailSender.setPassword(config.getMailPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(config.getMailUser());

        mailSender.send(message);
    }
}