package com.dev.attendance.DTO.request;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class UseDayOffRequest {
    Long id;
    LocalDate applyDate;//사용일
    String reason;      //사유

    public UseDayOffRequest(Long id, String reason){
        this.id = id;
        this.applyDate = LocalDate.now().plusDays(1);
        this.reason = reason;
    }

    public UseDayOffRequest(Long id, String reason, LocalDate applyDate){
        this.id = id;
        this.applyDate = applyDate;
        this.reason = reason;
    }

    public UseDayOffRequest(){}

    public LocalDate setApplyDate(LocalDate applyDate){
        return this.applyDate = applyDate;
    }

}
