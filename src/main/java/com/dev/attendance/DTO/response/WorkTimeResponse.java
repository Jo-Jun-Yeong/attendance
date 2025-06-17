package com.dev.attendance.DTO.response;

import java.util.List;


import lombok.Getter;

@Getter
public class WorkTimeResponse {
    
    List<WorkTimeMinuteDTO> detail;
    int monthlyWorkTime;

    public WorkTimeResponse(List<WorkTimeMinuteDTO> detail, int monthlyWorkTime){
        this.detail = detail;
        this.monthlyWorkTime = monthlyWorkTime;
    }

    WorkTimeResponse(){

    }
    

}
