package com.dev.attendance.DTO;

import java.time.LocalDate;
import lombok.Data;

@Data
public class EmpDTO{
    private Long id;
    private String name; 
    private String teamName;
    private String role; 
    private LocalDate birthday;
    private LocalDate workStartDate;

    public EmpDTO(Long id, 
        String name, String 
        teamName, 
        String role, 
        LocalDate birthday, 
        LocalDate workStartDate){

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

