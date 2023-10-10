package com.UploadAndNotifyBack.UploadAndNotifyBack.service;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.File;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmailService {

    private static JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender){ EmailService.mailSender = mailSender;}
    public static void sendEmail(String to, String subject, String body) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("matheyd@gmx.fr");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
