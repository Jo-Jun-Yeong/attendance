package com.dev.attendance.DTO.response;

import com.dev.attendance.domain.DayOff;
import com.dev.attendance.domain.Emp;

import lombok.Getter;

@Getter
public class DayOffResponse {
    public Long id;
    public Emp emp;
    public int dayOffCount;

    public DayOffResponse(DayOff dayOff){
        this.emp = dayOff.getEmployee_id();
        this.dayOffCount = dayOff.getDayOff();
        
    }
}
