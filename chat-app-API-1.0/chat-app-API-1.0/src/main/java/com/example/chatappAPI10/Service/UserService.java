package com.example.chatappAPI10.Service;

import com.example.chatappAPI10.model.Users;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity save(Users user);
//ss
    ResponseEntity getAlUsers();

   ResponseEntity update(Users user);

   ResponseEntity getUserByEmail(String email);

   ResponseEntity findByUserEmailContaining(String requestInputs);
}
