package com.example.chatappAPI10.DTO;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResponseDTO {
    private String code;
    private String message;
    private String otpNumber;
}
