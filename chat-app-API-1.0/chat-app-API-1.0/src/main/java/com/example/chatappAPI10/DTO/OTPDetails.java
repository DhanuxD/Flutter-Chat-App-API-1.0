package com.example.chatappAPI10.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPDetails {

    private String toMail;
    private String otpNumber;
    private String mailSubject;
    private String fromEmail;

}