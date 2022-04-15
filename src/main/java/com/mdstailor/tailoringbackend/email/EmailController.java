package com.mdstailor.tailoringbackend.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailSenderService emailSenderService;
    @PostMapping("/sendMail")
    public ResponseEntity<?> triggerMail(@RequestBody Email email) throws MessagingException {
        emailSenderService.sendEmailWithAttachment(email.getToEmail(),  email.getSubject(), email.getBody());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
