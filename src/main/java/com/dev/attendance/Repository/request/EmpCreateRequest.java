package com.dev.attendance.Repository.request;

import lombok.Data;
import lombok.Getter;



@Getter
public class EmpCreateRequest {
    private Long id;
    private String pwd;
    private String name;
    private String email;
    private String tel;
    private String role;


}
