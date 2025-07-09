package com.dev.attendance.Controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.attendance.DTO.request.UseDayOffRequest;
import com.dev.attendance.DTO.response.DayOffCountResponse;
import com.dev.attendance.DTO.response.DayOffResponse;
import com.dev.attendance.DTO.response.PlusDayOffResponse;
import com.dev.attendance.DTO.response.UseDayOffResponse;
import com.dev.attendance.Service.DayOffService;
import com.dev.attendance.Service.EmpService;
import com.dev.attendance.domain.DayOff;
import com.dev.attendance.domain.DayOffHistory;
import com.dev.attendance.domain.Emp;




@Controller
@RequestMapping("/api/dayOff")
public class DayOffController {
    
    private final DayOffService dayOffService;
    private final EmpService empService;
    

    DayOffController(DayOffService dayOffService, EmpService empService){
        this.dayOffService = dayOffService;
        this.empService = empService;
    }

    @GetMapping("/createDayOff/{id}")
    public ResponseEntity<DayOffResponse> createDayOff(@PathVariable Long id) {
        System.out.println("Controller > 연차 생성 시작");
        Emp findEmp = empService.getEmp(id);
        DayOff dayOff = dayOffService.createDayOffResponse(findEmp);
        System.out.println("Controller > 연차 생성 종료");
        return ResponseEntity.status(HttpStatus.CREATED).body(new DayOffResponse(dayOff));
    }
    

    @GetMapping("/getDayOffCount/{id}")
    public ResponseEntity<DayOffCountResponse> getDayOffCount(@PathVariable Long id) {
        //emp아이디를 받아서 찾아야 함
        DayOff dayOff = dayOffService.getDayOffCount(id);

        DayOffCountResponse response = new DayOffCountResponse(dayOff);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    


    @GetMapping("/getAllDayOffCount")
    public ResponseEntity<List<DayOffCountResponse>> getAllDayOffCount() {
        List<DayOffCountResponse> response = dayOffService.getAllDayOff();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    

    //연차 사용 / 1일
    @PostMapping("/useDayOff")
    public ResponseEntity<UseDayOffResponse> useDayOff(@RequestBody UseDayOffRequest request) {
        //request: id, 사유, 날짜(생략시 다음날)

        if(request.getApplyDate()==null){
            request.setApplyDate(LocalDate.now().plusDays(1));
        }

        UseDayOffResponse response = dayOffService.useDayOff(request);
        

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    //모든 연차 기록 조회
    @GetMapping("/getAllHistory")
    public ResponseEntity<List<DayOffHistory>> getAllHistory() {

        List<DayOffHistory> response = dayOffService.getAllHistory();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    //날짜 월 별 연차 기록 조회
    @GetMapping("/getHistoryAllByYearAndMonth")
    public ResponseEntity<List<DayOffHistory>> getHistoryAllByYearAndMonth(@RequestParam int year, @RequestParam int month) {

        List<DayOffHistory> response = dayOffService.getHistoryAllByYearAndMonth(year, month);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    

    //사원+ 월별 연차 기록 조회
    @GetMapping("/getHistoryEmpByYearAndMonth")
    public ResponseEntity<List<DayOffHistory>> getHistoryEmpByYearAndMonth(@RequestParam Long id,@RequestParam int year, @RequestParam int month) {

        List<DayOffHistory> response = dayOffService.getHistoryEmpByYearAndMonth(id, year, month);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    


    //연차 추가
    @PostMapping("/plusDayOff")
    public ResponseEntity<PlusDayOffResponse> plusDayOff(@RequestParam Long id,
        @RequestParam int plusDayOff, @RequestParam String reason) {

        PlusDayOffResponse response = dayOffService.plusDayOff(id, plusDayOff, reason);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    


    // //연차 등록
    // @PostMapping("path")
    // public ResponseEntity<registDayOffResponse> registDayOff(@RequestParam Long id) {
    //     //신입11개, 그 외 15개
        

    // }

    //날짜별 근무 조회에서 연차 사용일 = 근무일이면 연차 사용유무 표시
    
    

}
