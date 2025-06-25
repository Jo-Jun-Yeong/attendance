package com.dev.attendance.DTO.response;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class UseDayOffResponse {
    LocalDate usedDate;
    LocalDate applyDate;
    String reason;

    public UseDayOffResponse(String reason, LocalDate applyDate){
        this.usedDate=LocalDate.now();
        this.applyDate = applyDate;
        this.reason = reason;
    }

    public UseDayOffResponse(){}

}
