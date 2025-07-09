package com.dev.attendance.DTO.response;

import com.dev.attendance.domain.Emp;

import lombok.Getter;

@Getter
public class PlusDayOffResponse {
    Emp emp;
    int plusDayOff;
    String reason;

    public PlusDayOffResponse(Emp emp, int plusDayOff, String reason){
        this.emp = emp;
        this.plusDayOff = plusDayOff;
        this.reason = reason;
    }

    public PlusDayOffResponse(){

    }
}
