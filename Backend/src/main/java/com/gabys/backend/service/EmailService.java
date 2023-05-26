package com.gabys.backend.service;

import com.gabys.backend.model.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("gabys.dev14@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Account Details");
        message.setText("Your account details have been changed\n" +
                "username: " + user.getUsername() + "\n" +
                "password: " + user.getPassword() + "\n" +
                "email: " + user.getEmail() + "\n" +
                "phone: " + user.getPhone() + "\n"
                );

        mailSender.send(message);
    }
}
