package com.blue_economy.dto;

import lombok.Data;

@Data
public class DtoRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthDate;
    private String gender;
    private String role;

}