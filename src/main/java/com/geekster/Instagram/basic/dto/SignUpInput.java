package com.geekster.Instagram.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpInput {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private  String  userPassword;
    private String phoneNumber;
}
