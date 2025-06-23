package com.dev.attendance.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.dev.attendance.DTO.response.DayOffResponse;
import com.dev.attendance.Service.DayOffService;
import com.dev.attendance.Service.EmpService;
import com.dev.attendance.domain.DayOff;
import com.dev.attendance.domain.Emp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api/DayOff")
public class DayOffController {
    
    private final DayOffService dayOffService;
    private final EmpService empService;
    
    DayOffController(DayOffService dayOffService, EmpService empService){
        this.dayOffService = dayOffService;
        this.empService = empService;
    }

    @GetMapping("/creatDayOff")
    public ResponseEntity<DayOffResponse> createDayOff(@RequestParam Long id) {
        Emp findEmp = empService.getEmp(id);
        DayOff dayOff = dayOffService.createDayOffResponse(findEmp);

        return ResponseEntity.status(HttpStatus.CREATED).body(new DayOffResponse(dayOff));
    }
    

    @GetMapping("/getDayOffCount")
    public ResponseEntity<DayOffResponse> getDayOffCount(@RequestParam Long id) {
        
        DayOff dayOff = dayOffService.getDayOffCount(id);

        DayOffResponse response = new DayOffResponse(dayOff);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    // //연차 사용 / 1일
    // @PostMapping("path")
    // public ResponseEntity<UseDayOffResponse> useDayOff(@RequestParam Long id) {

        

    // }

    // //연차 등록
    // @PostMapping("path")
    // public ResponseEntity<registDayOffResponse> registDayOff(@RequestParam Long id) {
    //     //신입11개, 그 외 15개
        

    // }

    //날짜별 근무 조회에서 연차 사용일 = 근무일이면 연차 사용유무 표시
    
    

}
