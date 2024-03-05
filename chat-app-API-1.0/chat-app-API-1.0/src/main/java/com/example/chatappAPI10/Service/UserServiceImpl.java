package com.example.chatappAPI10.Service;

import com.example.chatappAPI10.DTO.UserResponseDTO;
import com.example.chatappAPI10.model.Users;
import com.example.chatappAPI10.repo.UserRepo;
import java.util.ArrayList;
import java.util.List;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserResponseDTO userResponseDTO;

    @Override
    public ResponseEntity save(Users users) {

        try {

            if (users.getEmail()!=null) {
                if (userRepo.findByEmail(users.getEmail()) == null || !userRepo.findByEmail(users.getEmail()).getEmail().equals(users.getEmail())) {
                    userRepo.save(users);
                }
                return getUserByEmail(users.getEmail());
            } else {
                userResponseDTO.setResponseCode("400");
                userResponseDTO.setResponseData(null);
                userResponseDTO.setResponseMessage("Email cannot be empty");
                return new ResponseEntity<>(userResponseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            userResponseDTO.setResponseCode("500");
            userResponseDTO.setResponseData(null);
            userResponseDTO.setResponseMessage("Internal server error");
            return new ResponseEntity<>(userResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity getAlUsers() {
        try {
            userResponseDTO.setResponseCode("200");
            userResponseDTO.setResponseData(userRepo.findAll());
            userResponseDTO.setResponseMessage("Success");
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            userResponseDTO.setResponseCode("500");
            userResponseDTO.setResponseData(null);
            userResponseDTO.setResponseMessage("Internal server error");
            return new ResponseEntity<>(userResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 @Override
    public ResponseEntity update(Users users) {
     try {
         if (userRepo.findByEmail(users.getEmail())!=null) {
             users.setId(userRepo.findByEmail(users.getEmail()).getId());
             userRepo.save(users);
             userResponseDTO.setResponseCode("202");
             userResponseDTO.setResponseMessage("Successful user updated");
             return new ResponseEntity<>(userResponseDTO, HttpStatus.ACCEPTED);
         }
         else {
             userResponseDTO.setResponseCode("404");
             userResponseDTO.setResponseMessage("Can not find this user by given credentials");
             userResponseDTO.setResponseData(null);
             return new ResponseEntity<>(userResponseDTO, HttpStatus.BAD_REQUEST);
         }
     } catch (Exception e) {
         System.out.println(e.getMessage());
         userResponseDTO.setResponseCode("500");
         userResponseDTO.setResponseData(null);
         userResponseDTO.setResponseMessage("Internal server error");
         return new ResponseEntity<>(userResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }

    @Override
    public ResponseEntity getUserByEmail(String email) {

         try {
             if (userRepo.findByEmail(email)!=null) {
                 List<Users> userList = new ArrayList<>();
                 userList.add(userRepo.findByEmail(email));
                 userResponseDTO.setResponseCode("200");
                 userResponseDTO.setResponseData(userList);
                 userResponseDTO.setResponseMessage("Success");
             }else {
                 userResponseDTO.setResponseCode("404");
                 userResponseDTO.setResponseData(null);
                 userResponseDTO.setResponseMessage("Can not find any user by given credentials");
             }
             return  new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
         }catch (Exception e){
             System.out.println(e.getMessage());
             userResponseDTO.setResponseCode("500");
             userResponseDTO.setResponseData(null);
             userResponseDTO.setResponseMessage("Internal server error");
             return new ResponseEntity<>(userResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @Override
    public ResponseEntity findByUserEmailContaining(String requestInputs) {

        try {
            System.out.println(requestInputs);
            userResponseDTO.setResponseCode("200");
            userResponseDTO.setResponseData(userRepo.findByUserEmailContaining(requestInputs));
            userResponseDTO.setResponseMessage("Success");
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            userResponseDTO.setResponseCode("500");
            userResponseDTO.setResponseData(null);
            userResponseDTO.setResponseMessage("Internal server error");
            return new ResponseEntity<>(userResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @Override
//    public ResponseEntity getUserByContaining(String containLetters) {
//
//        try {
//
//
//
//
//            userResponseDTO.setResponseCode("200");
//            //userResponseDTO.setResponseData();
//            userResponseDTO.setResponseMessage("Success");
//            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            userResponseDTO.setResponseCode("500");
//            userResponseDTO.setResponseData(null);
//            userResponseDTO.setResponseMessage("Internal server error");
//            return new ResponseEntity<>(userResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
////        try {
////            userResponseDTO.setResponseCode("200");
////            userResponseDTO.setResponseData(userRepo.findByUserEmailContaining(containLetters));
////            userResponseDTO.setResponseMessage("Success");
////            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////            userResponseDTO.setResponseCode("500");
////            userResponseDTO.setResponseData(null);
////            userResponseDTO.setResponseMessage("Internal server error");
////            return new ResponseEntity<>(userResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
//    }
}
