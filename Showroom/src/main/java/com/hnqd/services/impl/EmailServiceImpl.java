/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

import com.hnqd.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String email, String messageSend) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("ANNOUNMENT FROM SHOWROOM");
        message.setText(messageSend);

        javaMailSender.send(message);
    }
    
}
