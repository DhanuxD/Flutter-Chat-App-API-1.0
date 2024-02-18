package com.example.chatappAPI10.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.chatappAPI10.DTO.OTPDetails;
import com.example.chatappAPI10.Service.SendOTPNumber;

@RestController
@RequestMapping(value = "api/v1/otp")
public class OTPController {
    @Autowired
    private SendOTPNumber sendOTPNumber;

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/sendotp")
    public ResponseEntity sendOTP(@RequestBody OTPDetails otpDetails) {
        return sendOTPNumber.sendOTPNumber(otpDetails.getToMail());
    }

}
