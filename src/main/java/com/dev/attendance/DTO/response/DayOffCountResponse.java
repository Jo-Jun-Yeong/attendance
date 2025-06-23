package com.dev.attendance.DTO.response;

import com.dev.attendance.domain.DayOff;
import com.dev.attendance.domain.Emp;

import lombok.Getter;

@Getter
public class DayOffCountResponse {
    public Long employee_id;
    public int dayOff_count;

    public DayOffCountResponse(DayOff dayOff){
        this.employee_id = dayOff.getId();
        dayOff_count = dayOff.getDayOff();
    }
}
