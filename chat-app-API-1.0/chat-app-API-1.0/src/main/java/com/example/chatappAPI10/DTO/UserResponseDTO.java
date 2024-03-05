package com.example.chatappAPI10.DTO;

import com.example.chatappAPI10.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    String responseCode;
    String responseMessage;
    List<Users> responseData;
}
