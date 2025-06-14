package com.dev.attendance.DTO.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AttendRequest {
    //출석체크

    private Long employeeId;

    public AttendRequest(Long employeeId, String type){
        this.employeeId = employeeId;
    }

    public AttendRequest(){

    }
}
