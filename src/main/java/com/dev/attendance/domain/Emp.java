package com.dev.attendance.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class Emp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //bigint primary key

    @Column(nullable=false, length=25)
    private String name;        //varchar(25)

    @Column(nullable=true, length=25)
    private String teamName;    //varchar(25)null

    @Column(nullable=false, length=25)
    private String role;        //varchar(25) not null

    @Column(nullable=true)
    private LocalDate birthday;     //datetime null

    @Column(nullable = false)
    private LocalDate workStartDate; //not null/

    public Emp(String name, String teamName, String role,LocalDate birthday){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 이름입니다: %s", name));
        }
        this.name = name;
        this.teamName = teamName;
        this.role = role;
        this.birthday=birthday;
    }

    @PrePersist
    public void prePersist() {
        if (this.workStartDate == null) {
            this.workStartDate = LocalDate.now();
        }
    }
    public Emp() {

    }
}
