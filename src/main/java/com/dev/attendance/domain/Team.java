package com.dev.attendance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=true)
    private String teamName;
    @Column(nullable=true)
    private String manager;
    @Column(nullable=true, length=100)
    private int memberCount;


    public Team(String teamName, String manager){
        this.teamName = teamName;
        this.manager = manager;
    }

    public Team(){

    }
}
