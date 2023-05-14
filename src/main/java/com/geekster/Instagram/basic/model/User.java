package com.geekster.Instagram.basic.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String userPassword;
    private String phoneNumber;
    public  User(String firstName,String lastName,Integer age,String email,String userPassword,String phoneNumber){
       this.firstName=firstName;
       this.lastName=lastName;
       this.age=age;
       this.email=email;
       this.userPassword=userPassword;
       this.phoneNumber=phoneNumber;
    }
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Post> post;
}
