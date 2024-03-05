package com.example.chatappAPI10.Controllers;

import com.example.chatappAPI10.Service.UserService;
import com.example.chatappAPI10.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/saveuser")
    public ResponseEntity saveUser(@RequestBody Users users) {
        return userService.save(users);
    }
    @GetMapping(value = "/getallusers")
    public ResponseEntity getUsers(){
        return userService.getAlUsers();
    }
    @PostMapping(value = "/updateuser")
    public ResponseEntity updateUser(@RequestBody Users users) {return userService.update(users);}
    @GetMapping(value = "/getuserbyemail/{useremail}")
    public ResponseEntity getUserByEmail(@PathVariable("useremail") String userEmail) {return userService.getUserByEmail(userEmail);}

    @GetMapping(value = "/getuserbycontaining")
    public ResponseEntity getUserByContaining(@RequestParam("requestinputs") String requestInputs) {
        System.out.println("OK");
        return userService.findByUserEmailContaining(requestInputs);}

}
