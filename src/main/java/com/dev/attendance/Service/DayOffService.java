package com.dev.attendance.Service;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.attendance.DTO.request.UseDayOffRequest;
import com.dev.attendance.DTO.response.DayOffCountResponse;
import com.dev.attendance.DTO.response.DayOffResponse;
import com.dev.attendance.DTO.response.PlusDayOffResponse;
import com.dev.attendance.DTO.response.UseDayOffResponse;
import com.dev.attendance.Repository.DayOffHistoryRepository;
import com.dev.attendance.Repository.DayOffRepository;
import com.dev.attendance.Repository.EmpRepository;
import com.dev.attendance.domain.DayOff;
import com.dev.attendance.domain.DayOffHistory;
import com.dev.attendance.domain.Emp;

@Service
public class DayOffService {

    private final EmpRepository empRepository;
    
    private final DayOffRepository dayOffRepository;
    private final EmpService empService;
    private final DayOffHistoryRepository dayOffHistoryRepository;

    public DayOffService(DayOffRepository dayOffRepository, EmpService empService, EmpRepository empRepository, DayOffHistoryRepository dayOffHistoryRepository){
        this.dayOffRepository = dayOffRepository;
        this.empService=empService;
        this.empRepository = empRepository;
        this.dayOffHistoryRepository = dayOffHistoryRepository;
    }

    public DayOff createDayOffResponse(Emp emp){

        DayOff response = new DayOff(emp);
        dayOffRepository.save(response);
        return response;
    }

    public DayOff getDayOffCount(Long id){
        empService.getEmp(id);
         
        return  dayOffRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 id인 사원은 없습니다."));
        
    }

    public List<DayOffCountResponse> getAllDayOff(){
        List<DayOffCountResponse> response = new ArrayList<DayOffCountResponse>();
        Iterator<DayOff> it = dayOffRepository.findAll().iterator();
        DayOff dayOff = null;
        while(it.hasNext()){
            dayOff = it.next();
            response.add(new DayOffCountResponse(dayOff));
        }
        return response;
    }

    public UseDayOffResponse useDayOff(UseDayOffRequest request){
        
        //request: reason, [usedate], id

        Emp emp = empRepository.findById(request.getId())
            .orElseThrow(()-> new RuntimeException("해당 id("+request.getId()+")의 사원은 존재하지 않습니다"));

        DayOff dayOff = dayOffRepository.findByEmployee(emp)
            .orElseThrow(()-> new RuntimeException("해당 id("+request.getId()+")의 사원은 존재하지 않습니다"));

        
        int count = dayOff.getDayOff();
        if(count < 0 || count ==0){
            throw new IllegalStateException("남은 연차가 없습니다. (현재: " + count + "일)");
        }
        dayOff.setDayOff(count-1);

        System.out.println("        연차 차감시작");
        dayOffRepository.saveAndFlush(dayOff);
        System.out.println("        연차 차감완료");
        //연차 히스토리도 있어야하나? -> DayOffHistory
        System.out.println("        기록 입력 시작");
        dayOffHistoryRepository.saveAndFlush(new DayOffHistory(emp, request.getReason(), request.getApplyDate()));
        System.out.println("        기록 입력 종료");

        
        return new UseDayOffResponse(request.getReason(), request.getApplyDate());
    }

    //연차 추가
    public PlusDayOffResponse plusDayOff(Long id, int plusDayOff, String reason){
        Emp emp = empRepository.findById(id).orElseThrow(() -> new RuntimeException("id: "+id+"- 해당 id의 사원은 없습니다."));
        DayOff dayoff = dayOffRepository.findByEmployee(emp).orElseThrow(()-> new RuntimeException(emp.getId()+"- 사원 불러오기 오류"));

        int dayoffCount = dayoff.getDayOff();
        //1. dayOff 가져와서 plus만큼 추가해주고 save
        //2. 기록 테이블에 기록
        dayoff.setDayOff(dayoffCount+plusDayOff);
        dayOffRepository.saveAndFlush(dayoff);

        

        //연차 추가 한 기록 추가
        dayOffHistoryRepository.saveAndFlush(new DayOffHistory(emp, reason));



        PlusDayOffResponse response = new PlusDayOffResponse(emp, plusDayOff, reason);

        return response;

    }
    

    //모든 연차 기록 조회
    public List<DayOffHistory> getAllHistory() {
       
        return dayOffHistoryRepository.findAll();
    }


    //연, 월 별 모든 연차 기록 조회
    public List<DayOffHistory> getHistoryAllByYearAndMonth(int year, int month){
        List<DayOffHistory> response = new ArrayList<DayOffHistory>();        

        response = dayOffHistoryRepository.findAllByYearAndMonth(year, month);

        return response;
    }

    //직원별 연, 월 별 연차 기록 조회
    public List<DayOffHistory> getHistoryEmpByYearAndMonth(Long id, int year, int month){
        List<DayOffHistory> response = new ArrayList<DayOffHistory>();        

        Emp employee = empRepository.findById(id).get();
            

        response = dayOffHistoryRepository.findByEmployeeAndYearAndMonth(employee, year, month);

        return response;
    }

}
