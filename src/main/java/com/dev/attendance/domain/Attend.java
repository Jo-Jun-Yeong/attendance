package com.dev.attendance.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Attend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, name="employee_id")
    private Long employeeId;
    @Column(nullable = false, name = "go_to_work")
    private LocalDateTime goToWork;
    @Column(nullable = true, name= "off_work")
    private LocalDateTime offWork;
    
    @Column(nullable = false, name="usingDayOff")
    private boolean usingDayOff;


    public Attend(Long id){
        this.id = id;
        this.usingDayOff = false;

    }

    public Attend(){}
}
