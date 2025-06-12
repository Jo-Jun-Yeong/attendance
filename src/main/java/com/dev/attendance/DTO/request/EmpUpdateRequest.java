package com.dev.attendance.DTO.request;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class EmpUpdateRequest {

    private String name;
    private String teamName; 
    private String role;
    
}
