package com.dev.attendance.DTO.request;

import java.time.LocalDateTime;

import lombok.Getter;


@Getter
public class WorkTimeRequest {
    Long employee_id;
    int month;

    public WorkTimeRequest(Long employee_id, int month){
        this.employee_id = employee_id;
        this.month = month;
    }

}
