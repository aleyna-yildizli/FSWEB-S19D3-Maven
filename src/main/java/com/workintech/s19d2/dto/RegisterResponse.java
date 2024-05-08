package com.workintech.s19d2.dto;

import org.springframework.web.bind.annotation.RestController;

@RestController
public record RegisterResponse(String email, String message) {
}
