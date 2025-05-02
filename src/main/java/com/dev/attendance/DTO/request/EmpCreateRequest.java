package com.dev.attendance.DTO.request;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Getter;

@Getter
public class EmpCreateRequest {
    private String name;
    private String teamName; 
    private String role;
    private LocalDateTime birthday;
    
}

