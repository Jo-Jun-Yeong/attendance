package com.dev.attendance.Service;



import org.springframework.stereotype.Service;


import com.dev.attendance.Repository.DayOffRepository;
import com.dev.attendance.domain.DayOff;
import com.dev.attendance.domain.Emp;

@Service
public class DayOffService {
    
    private final DayOffRepository dayOffRepository;
    private final EmpService empService;
    public DayOffService(DayOffRepository dayOffRepository, EmpService empService){
        this.dayOffRepository = dayOffRepository;
        this.empService=empService;
    }

    public DayOff createDayOffResponse(Emp emp){

        DayOff response = new DayOff(emp);
        dayOffRepository.save(response);
        return response;
    }

    public DayOff getDayOffCount(Long id){
        Emp emp = null;
        DayOff response = null;
        try {
            emp = empService.getEmp(id);
            
        } catch (RuntimeException e) {
            System.err.println("해당하는 아이디의 직원이 없습니다."+e.getMessage());
        }
        response = dayOffRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 id인 사원은 없습니다."));


        return response;
    }


    // public useDayOffResponse useDayOff(Long id){
        


    //     return 
    // }

    
    
}
