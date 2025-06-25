package com.dev.attendance.DTO.request;

import java.time.LocalDate;


import lombok.Getter;

@Getter
public class EmpCreateRequest {
    private String name;
    private String teamName; 
    private String role;
    private LocalDate birthday;

    public EmpCreateRequest(String name, String teamName, String role, LocalDate birthday){
        this.name = name;
        this.teamName = teamName;
        this.role = role;
        this.birthday = birthday;
    }

    public EmpCreateRequest(String name,String role ,LocalDate birthday){
        this.name = name;
        this.role = role;
        this.birthday = birthday;
    }
    
    public EmpCreateRequest(){}
}

