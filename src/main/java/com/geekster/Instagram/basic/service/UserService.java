package com.geekster.Instagram.basic.service;

import com.geekster.Instagram.basic.dto.SignInInput;
import com.geekster.Instagram.basic.dto.SignInOutput;
import com.geekster.Instagram.basic.dto.SignUpInput;
import com.geekster.Instagram.basic.dto.SignUpOutput;
import com.geekster.Instagram.basic.model.AuthenticationToken;
import com.geekster.Instagram.basic.model.User;
import com.geekster.Instagram.basic.repository.IUserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo iUserRepo;
    @Autowired
    AuthenticationService authenticationService;
    public SignUpOutput signup(SignUpInput signDto) {
        User user=iUserRepo.findFirstByEmail(signDto.getEmail());
        if(user!=null){
           throw new IllegalStateException("User Already exist!!!...");
        }
         String encryptedPassword = null;
        try{
            encryptedPassword = encryptPassword(signDto.getUserPassword());
        }catch(Exception e){
            e.printStackTrace();
        }
         user=new User(signDto.getFirstName(), signDto.getLastName(), signDto.getAge(), signDto.getEmail(), encryptedPassword, signDto.getPhoneNumber());
        iUserRepo.save(user);
        AuthenticationToken token = new AuthenticationToken(user);
        authenticationService.saveToken(token);
        return new SignUpOutput("User registered","User created successfully");
    }
    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested =  md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signin(SignInInput signInDto) {
        User user=iUserRepo.findFirstByEmail(signInDto.getEmail());
        if(user==null){
            throw new IllegalStateException("User is Invalid!!!...");
        }
        String encryptedPassword = null;
        try{
            encryptedPassword = encryptPassword(signInDto.getUserPassword());
        }catch(Exception e){
            e.printStackTrace();
        }
        boolean isPasswordValid = encryptedPassword.equals(user.getUserPassword());
        if(!isPasswordValid){
            throw  new IllegalStateException("Password is in Valid or sign up");
        }
        AuthenticationToken authToken = authenticationService.getToken(user);
        return  new SignInOutput("Authentication Successfull !!!",authToken.getToken());
    }


    public void updateUser(SignUpInput user) {
       User u=iUserRepo.findFirstByEmail(user.getEmail());
       if(u==null){
           throw  new IllegalStateException("Invalid user!!!");
       }
        String encryptedPassword = null;
        if(user.getEmail() != null)
        {
            try {
                encryptedPassword = encryptPassword(user.getUserPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        User u1=new User(user.getFirstName(), user.getLastName(), user.getAge(), user.getEmail(), encryptedPassword,user.getPhoneNumber());
        iUserRepo.save(u1);
    }
}
