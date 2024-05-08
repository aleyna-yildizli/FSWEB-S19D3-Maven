package com.workintech.s19d2.dto;

import org.springframework.web.bind.annotation.RestController;

@RestController
public record RegistrationMember(String email, String password){
}
