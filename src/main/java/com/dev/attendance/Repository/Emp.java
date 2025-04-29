package com.dev.attendance.Repository;

import org.springframework.data.annotation.Id;

import lombok.Data;

public class Emp {
    public Emp(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 형식의 이름입니다: %s",name));
        }
        this.name=name;
    }
    @Id
    private Long id;
    private String pwd;
    private String name;
    private String email;
    private String tel;
    private String role;  // Role을 String으로 받음
    
}
