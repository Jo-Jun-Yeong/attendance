package com.dev.attendance.DTO.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AttendRequest {
    //출석체크

    private Long employeeId;
    private String type;

    public AttendRequest(Long employeeId, String type){
        //LocalDateTime now = LocalDateTime.now();
        this.employeeId = employeeId;
        this.type = type;
        //this.goToWork = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute());
    }

    public AttendRequest(){

    }
}
