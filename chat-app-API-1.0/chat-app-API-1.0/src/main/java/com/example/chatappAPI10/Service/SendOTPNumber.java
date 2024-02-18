package com.example.chatappAPI10.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.example.chatappAPI10.DTO.OTPDetails;
import com.example.chatappAPI10.DTO.ResponseDTO;

import java.util.Random;

@Component
public class SendOTPNumber {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    private OTPDetails otpDetails;

    private void setRandomOTP() {
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        otpDetails.setOtpNumber(String.valueOf(randomNumber));
    }

    private void setFromEmail() {
        otpDetails.setFromEmail("ketawalagedhanusha@gmail.com");
    }

    private void setToMail(String userEmail){
        otpDetails.setToMail(userEmail);}

    private void setSubject() {
        String subjectOfEmail = "Hello Chat up! OTP verification";
        otpDetails.setMailSubject(subjectOfEmail);
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity sendOTPNumber(String userEmail) {

        try {
            SimpleMailMessage otpSimpleMailMessage = new SimpleMailMessage();
            setFromEmail();
            setRandomOTP();
            setSubject();
            setToMail(userEmail);

            otpSimpleMailMessage.setFrom(otpDetails.getFromEmail());
            otpSimpleMailMessage.setSubject(otpDetails.getMailSubject());
            otpSimpleMailMessage.setText(otpDetails.getOtpNumber());
            otpSimpleMailMessage.setTo(otpDetails.getToMail());
            javaMailSender.send(otpSimpleMailMessage);
            responseDTO.setCode("200");
            responseDTO.setMessage("Success");
            responseDTO.setOtpNumber(otpDetails.getOtpNumber());
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode("400");
            responseDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

}
