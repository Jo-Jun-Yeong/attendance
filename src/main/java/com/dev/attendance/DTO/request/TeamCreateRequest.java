package com.dev.attendance.DTO.request;

import lombok.Getter;

@Getter
public class TeamCreateRequest {
    private String teamName;
    private String manager;

    public TeamCreateRequest(String teamName, String manager){
        this.teamName = teamName;
        this.manager = manager;
    }

    public TeamCreateRequest(){

    }

}
