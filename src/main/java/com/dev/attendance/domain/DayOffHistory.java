package com.dev.attendance.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class DayOffHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name="employee_id")
    private Emp employee;
    private String reason;
    private LocalDate useDate = LocalDate.now();
    private LocalDate applyDate;

    public DayOffHistory(Emp employee, String reason, LocalDate applyDate){
        this.employee = employee;
        this.reason = reason;
        this.useDate = LocalDate.now();
        this.applyDate = applyDate;
    }

    public DayOffHistory(Emp employee, String reason){
        this.employee = employee;
        this.reason = reason;
        this.useDate = LocalDate.now();
    }

    

    public DayOffHistory(){}
    
}
