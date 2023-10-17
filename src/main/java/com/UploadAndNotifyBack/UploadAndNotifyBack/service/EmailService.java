package com.UploadAndNotifyBack.UploadAndNotifyBack.service;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.MyFile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;


@Service
public class EmailService {
    private static JavaMailSender mailSender;
    public EmailService (JavaMailSender mailSender){ EmailService.mailSender = mailSender;}
    public static void sendEmail(String to, List<MyFile> body) throws MalformedURLException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("matheyd@gmx.fr");
        message.setTo(to);
        message.setSubject("Nouveaux liens envoyés par UploadAndNotify");
        for (MyFile item : body) {
            String link = message.getText() != null ?  message.getText() : "";
            message.setText(item.getLink()+"\n"+ link);
        }
        mailSender.send(message);
    }
}

