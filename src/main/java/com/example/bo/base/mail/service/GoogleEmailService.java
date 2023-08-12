package com.example.bo.base.mail.service;

import com.example.bo.base.mail.dto.MailTo;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleEmailService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(MailTo mailTo) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setSubject(mailTo.getTitle());
        simpleMailMessage.setText(mailTo.getMessage());
        simpleMailMessage.setTo(mailTo.getAddress());
        simpleMailMessage.setFrom("bibleApp@bible.com");

        javaMailSender.send(simpleMailMessage);
    }
}
