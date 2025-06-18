package com.dev.attendance.Service;
import com.dev.attendance.domain.Attend;
import com.dev.attendance.domain.Emp;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.attendance.DTO.request.WorkTimeRequest;
import com.dev.attendance.DTO.response.WorkTimeMinuteDTO;
import com.dev.attendance.DTO.response.WorkTimeResponse;
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
    public Attend goToWork(Long id){
        System.out.println("start goToWork Service.");
        
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

        
        System.out.println("end goToWork Service.");
        return saveAttend;

    }

    @Transactional
    public Attend offWork(Long id){
        System.out.println("start offWork Service.");

        Emp attendemp = empRepository.findById(id).orElseThrow(()-> new RuntimeException("해당 직원은 없습니다."));

        Attend offWork = attendRepository.addOffWorkById(attendemp.getId()).orElseThrow(()-> new RuntimeException("출근하지 않습니다."));

        offWork.setOffWork(getYMDHD());

        attendRepository.save(offWork);
        
        System.out.println("end offWork Service.");
        return offWork;
    }

    @Transactional
    public LocalDateTime getYMDHD(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime result = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute());;

    

        
        return result;
    }


    public WorkTimeResponse getAllWorkTime(WorkTimeRequest param) {
        int getMonthlyWorkTimeMinute = 0;
        int getDailyWorkTimeMinute = 0;
        Attend attended = null;
        List<WorkTimeMinuteDTO> wtmDTO = new ArrayList<>();

        LocalDateTime startOfMonth = LocalDateTime.of(LocalDate.now().getYear(), param.getMonth(), 1, 0, 0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusSeconds(1); // 해당 월의 마지막 날까지 설정

        List<Attend> workTimeByIdAndMonth = 
            attendRepository.findAllByEmployeeIdAndGoToWork(param.getEmployee_id(), startOfMonth, endOfMonth)
            .orElseThrow(() -> new RuntimeException("해당 id의 직원 혹은 날짜의 기록이 없습니다."));


        Iterator<Attend> it = workTimeByIdAndMonth.iterator();
        
        while(it.hasNext()){

            attended = it.next();

            if(attended.getOffWork()!=null){

                getDailyWorkTimeMinute = (int) ChronoUnit.MINUTES.between(attended.getGoToWork(), attended.getOffWork());
                
                wtmDTO.add(new WorkTimeMinuteDTO(attended.getGoToWork().toLocalDate(), getDailyWorkTimeMinute));
                
                getMonthlyWorkTimeMinute += (int) ChronoUnit.MINUTES.between(attended.getGoToWork(), attended.getOffWork());
            }
            
        }

        

        System.out.println("ID: "+attended.getEmployeeId()+" response.size(): "+ wtmDTO.size());
        //workTimeByIdAndMonth = AttendResponse의List, getMinuteTime = 일 한 날들 분단위 근무시간
        WorkTimeResponse monthlyWorkTime = new WorkTimeResponse(wtmDTO, getMonthlyWorkTimeMinute);

        return monthlyWorkTime;
    }

}
