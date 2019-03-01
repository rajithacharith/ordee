package com.charith.ordee.services;

import com.charith.ordee.beans.User.Customer;
import com.charith.ordee.beans.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String text, String subject){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setText(text);
        mailMessage.setSubject(subject);
        javaMailSender.send(mailMessage);
    }

    public void sendConfirmation(User user){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Confirmation");
        mailMessage.setText("localhost/api/confirmation?userId"+user.getAccountId()+"?confirmation="+user.getConfirmation());
        javaMailSender.send(mailMessage);
    }

}
