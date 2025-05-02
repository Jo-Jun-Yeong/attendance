package com.dev.attendance.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EmpDTO{
    private Long id;
    private String name; 
    private String teamName;
    private String role; 
    private LocalDateTime birthday;
    private LocalDateTime workStartDate;

    public EmpDTO(Long id, 
        String name, String 
        teamName, 
        String role, 
        LocalDateTime birthday, 
        LocalDateTime workStartDate){

            this.id=id;
            this.name=name;
            this.teamName=teamName;
            this.role=role;
            this.birthday=birthday;
            this.workStartDate=workStartDate;
    }

    public EmpDTO(){
        
    }
}

