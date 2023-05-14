package com.geekster.Instagram.basic.controller;

import com.geekster.Instagram.basic.dto.SignInInput;
import com.geekster.Instagram.basic.dto.SignInOutput;
import com.geekster.Instagram.basic.dto.SignUpInput;
import com.geekster.Instagram.basic.dto.SignUpOutput;
import com.geekster.Instagram.basic.model.User;
import com.geekster.Instagram.basic.service.AuthenticationService;
import com.geekster.Instagram.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/signup")
    public SignUpOutput signup(@RequestBody SignUpInput signDto){
        return userService.signup(signDto);
    }
    @PostMapping("/signin")
    public SignInOutput signin(@RequestBody SignInInput signInDto){
        return  userService.signin(signInDto);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam String token,@RequestParam String email,@RequestBody SignUpInput user){
        HttpStatus status;
        String message="";
        if(authenticationService.authenticate(token,email)) {
            userService.updateUser(user);
            message="updated successfully";
            status=HttpStatus.OK;
        }else{
            message="Invalid Details to update";
            status=HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<String>(message,status);
    }
}

