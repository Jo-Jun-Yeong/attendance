package com.dev.attendance.Service;
import com.dev.attendance.Repository.TeamRepository;
import com.dev.attendance.domain.Attend;
import com.dev.attendance.domain.Emp;

import java.time.LocalDateTime;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.attendance.DTO.request.AttendRequest;
import com.dev.attendance.DTO.response.AttendResponse;
import com.dev.attendance.Repository.AttendRepository;
import com.dev.attendance.Repository.EmpRepository;

@Service
public class AttendService {


    
    private final AttendRepository attendRepository;
    private final EmpRepository empRepository;

    public AttendService(AttendRepository attendRepository, EmpRepository empRepository){
        this.attendRepository = attendRepository;
        this.empRepository = empRepository;
    }


    @Transactional
    public Attend attendance(Long id){
        System.out.println("start attendance Service.");
        
        Emp attendemp = empRepository.findById(id).orElseThrow(()-> new RuntimeException("해당 직원은 없습니다."));

        System.out.println("출석 객체 불러오기 완료");
        Attend attend = new Attend();
        attend.setEmployeeId(attendemp.getId());
        attend.setGoToWork(getYMDHD());
        attend.setOffWork(null);
        Attend saveAttend = null;

        try {

        saveAttend = attendRepository.save(attend);
            
            
        } catch (Exception e) {
            System.err.println("출근처리중 오류가 발생하였습니다.");
            
            throw new RuntimeException("출근 기록 저장 오류 발생: " + e);
        }

        
        System.out.println("end attendance Service.");
        return saveAttend;

    }

    @Transactional
    public LocalDateTime getYMDHD(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime result = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute());

        return result;
    }
}
