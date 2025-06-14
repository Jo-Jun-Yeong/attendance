package com.dev.attendance.DTO.response;

import java.time.LocalDateTime;


import lombok.Data;

@Data
public class AttendResponse {
    private Long employeeId;
    private LocalDateTime goToWork;
    private LocalDateTime offWork;

    public AttendResponse(Long employeeId, LocalDateTime goTowork, LocalDateTime offWork){
        this.employeeId = employeeId;
        this.goToWork = goTowork;
        this.offWork = offWork;
    }

    public AttendResponse(){

    }
}
