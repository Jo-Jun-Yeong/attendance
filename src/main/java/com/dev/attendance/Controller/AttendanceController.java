package com.dev.attendance.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dev.attendance.DTO.request.AttendRequest;
import com.dev.attendance.DTO.response.AttendResponse;
import com.dev.attendance.Service.AttendService;
import com.dev.attendance.domain.Attend;


@Controller
public class AttendanceController {
    

    private final AttendService attendService;
    public AttendanceController(AttendService attendService){
        this.attendService = attendService;
    }

    //출근
    @PostMapping("/api/attendance/goToWork")
    public ResponseEntity<AttendResponse> goToWork(@RequestBody AttendRequest request) {
        System.out.println("start attendance Controller");
        
        Attend attend = attendService.goToWork(request.getEmployeeId());
        AttendResponse response = new AttendResponse(attend.getEmployeeId(), attend.getGoToWork(), attend.getOffWork());
            
        System.out.println("end  출근 attendance Controller : ");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //퇴근
    @PostMapping("/api/attendance/offTheWork")
    public ResponseEntity<AttendResponse> offTheWork(@RequestBody AttendRequest request) {
        System.out.println("start attendance Controller");
        
        Attend attend = attendService.offWork(request.getEmployeeId());

        //퇴근 로직
        AttendResponse response = new AttendResponse(attend.getEmployeeId(), attend.getGoToWork(), attend.getOffWork());

        System.out.println("end  퇴근 attendance Controller : ");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
