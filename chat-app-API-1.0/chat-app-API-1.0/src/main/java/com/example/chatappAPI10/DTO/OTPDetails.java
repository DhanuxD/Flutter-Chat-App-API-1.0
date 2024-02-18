package com.example.chatappAPI10.DTO;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class OTPDetails {
    private String toMail;
    private String otpNumber;
    private String mailSubject;
    private String fromEmail;
}