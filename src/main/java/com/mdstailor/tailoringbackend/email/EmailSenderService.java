package com.mdstailor.tailoringbackend.email;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service

public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendSimpleEmail(String toEmail, String body,
                                String subject) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("np01cp4a190177@islingtoncollege.edu.np");
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Mail Sent");

    }
    public void sendEmailWithAttachment(String toEmail,
                                        String body,
                                        String subject) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("np01cp4a190177@islingtoncollge.edu.np");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);
//        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
//        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
//                fileSystemResource);
        javaMailSender.send(mimeMessage);
        System.out.println("Mail sent with Attachment");

    }
}
