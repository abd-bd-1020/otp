package com.example.otp.otp;

import com.example.otp.sms.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private EmailService emailService;
    public String createOtp(String email){

        OtpModel otpModel = new OtpModel();

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentTime.format(formatter);
        otpModel.setSendTime(formattedDateTime);

        Random r = new Random();
        String randomNumber = String.format("%04d", (Object) r.nextInt(1001));
        otpModel.setValue(randomNumber);
        String otpId = otpRepository.createOtp(otpModel);
        emailService.sendEmail(email,"Verification Code",otpModel.getValue());

        return otpId;

    }

    public boolean validateOtp(OtpDto otpDto){
        OtpModel otpModel = otpRepository.getOtpById(otpDto.getId());

        if(otpModel != null){
            if(Objects.equals(otpModel.getValue(), otpDto.getValue())){

                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime parsedDateTime = LocalDateTime.parse(otpModel.getSendTime(), formatter);
                LocalDateTime parsedDateTimePlus5Minutes = parsedDateTime.plusMinutes(5);

                return parsedDateTimePlus5Minutes.isAfter(currentTime);




            }
        }
        return false;

    }
}
