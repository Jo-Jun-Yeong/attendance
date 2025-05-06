package com.dev.attendance.DTO.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.attendance.DTO.EmpDTO;
import com.dev.attendance.domain.Emp;

import lombok.Getter;

@Getter
public class EmpResponse {
    
    private long id;
    private String name;
    private String teamName;    //varchar(25)null
    private String role;        //varchar(25) not null
    private LocalDate birthday;     //datetime null
    private LocalDate workStartDate; //not null/

    public EmpResponse(){

    }

    public EmpResponse(EmpDTO dto){
        this.id=dto.getId();
        this.name=dto.getName();
        this.teamName=dto.getTeamName();
        this.role=dto.getRole();
        this.birthday=dto.getBirthday();
        this.workStartDate=dto.getWorkStartDate();
    }

    public EmpResponse(long id, String name){
        this.id=id;
        this.name=name;
    }

    
    
}
