package com.example.otp.otp;

import com.example.otp.sms.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {
    @Autowired
    private OtpService otpService;
    @GetMapping("/send")
    public String sendSMS(@RequestParam String email) {
        if(email == null) return null;
        return otpService.createOtp(email);
    }

    @PostMapping("/verify")
    public Boolean verifyOtp(@RequestBody OtpDto otpDto){
        return otpService.validateOtp(otpDto);
    }
}
