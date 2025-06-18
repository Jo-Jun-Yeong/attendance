package com.dev.attendance.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dev.attendance.DTO.request.AttendRequest;
import com.dev.attendance.DTO.request.WorkTimeRequest;
import com.dev.attendance.DTO.response.AttendResponse;
import com.dev.attendance.DTO.response.WorkTimeResponse;
import com.dev.attendance.Service.AttendService;
import com.dev.attendance.domain.Attend;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/api/attendance")
public class AttendanceController {
    

    private final AttendService attendService;
    public AttendanceController(AttendService attendService){
        this.attendService = attendService;
    }

    //출근
    @PostMapping("/goToWork")
    public ResponseEntity<AttendResponse> goToWork(@RequestBody AttendRequest request) {
        System.out.println("start attendance Controller");
        
        Attend attend = attendService.goToWork(request.getEmployeeId());
        AttendResponse response = new AttendResponse(attend.getEmployeeId(), attend.getGoToWork(), attend.getOffWork());
            
        System.out.println("end  출근 attendance Controller : ");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //퇴근
    @PostMapping("/offTheWork")
    public ResponseEntity<AttendResponse> offTheWork(@RequestBody AttendRequest request) {
        System.out.println("start attendance Controller");
        
        Attend attend = attendService.offWork(request.getEmployeeId());

        //퇴근 로직
        AttendResponse response = new AttendResponse(attend.getEmployeeId(), attend.getGoToWork(), attend.getOffWork());

        System.out.println("end  퇴근 attendance Controller : ");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    

    @GetMapping("/workMinute")
    public ResponseEntity<WorkTimeResponse> getWorkTimeMonthlyMinute(@RequestParam Long employee_id, @RequestParam int month) {
        System.out.println("[Controller] getWorkTimeMonthlyMinute 시작");

        WorkTimeRequest workTimeRequest = new WorkTimeRequest(employee_id, month);

        WorkTimeResponse workTimeResponse = attendService.getAllWorkTime(workTimeRequest);
        System.out.println("[Controller] getWorkTimeMonthlyMinute 끝");
        return ResponseEntity.status(HttpStatus.FOUND).body(workTimeResponse);
    }
    
}
