package com.dev.attendance.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class DayOff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Emp employee_id;

    @Column(nullable = false, name="dayoff")
    //name = "JPA가 생성할 컬럼(FK)명"
    private int dayOff=11;

    public DayOff(Emp emp){
        this.employee_id = emp;
    }
}
