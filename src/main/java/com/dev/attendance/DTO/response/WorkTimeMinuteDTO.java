package com.dev.attendance.DTO.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class WorkTimeMinuteDTO {
    LocalDate date;
    int workMinute;
    boolean usingDayOff;
    
    public WorkTimeMinuteDTO(LocalDate date, int workMinute){
        this.date = date;
        this.workMinute = workMinute;
        this.usingDayOff = false;

    }

    public WorkTimeMinuteDTO(){

    }
}
