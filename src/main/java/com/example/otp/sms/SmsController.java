package com.example.otp.sms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SmsController {

    @Autowired
    private EmailService emailService;
    @GetMapping("/sendSMS")
    public void sendSMS() {

         emailService.sendEmail("nahid.hasan@dsinnovators.com","test","success");
    }
}
