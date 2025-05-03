package com.dev.attendance.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.attendance.DTO.EmpDTO;
import com.dev.attendance.DTO.request.EmpCreateRequest;
import com.dev.attendance.Repository.EmpRepository;
// import com.dev.attendance.Repository.emp.EmpRepository;
import com.dev.attendance.domain.Emp;

@Service
@Transactional
public class EmpService {
    private final EmpRepository empRepository;

    @Autowired
    public EmpService (EmpRepository empRepository){
        this.empRepository=empRepository;
    }

    //사원 입력
    @Transactional
    public Emp redgEmp(EmpCreateRequest request){

        Emp emp = new Emp(
            request.getName(),
            request.getTeamName(),
            request.getRole(),
            request.getBirthday()
        );
        System.out.println("데이터 입력 '시작'.");
        System.out.printf("%s / %s / %s / %s / ",
                            request.getName(),
                            request.getTeamName() !=null ? request.getTeamName() : "없음",
                            request.getRole(),
                            request.getBirthday().toString());

        Emp redgEmp = empRepository.saveAndFlush(emp);
        System.out.println("데이터 입력 '종료'.");
        return redgEmp;
    }

    //사원 전체검색
    @Transactional
    public List<Emp> getAllEmp(){
        List<Emp> resultList = empRepository.findAll();

        return resultList;
    }
    @Transactional
    public void deleteEmp(Long id){

        Emp deletedemp = null;
        
        try {
            deletedemp = empRepository.findById(id).get();
            
            System.out.printf("%s의 아래의 정보가 삭제됩니다.\n 이름: %s \n 팀: %s \n 직급: %s \n 생일: %s \n 입사일: %s" 
            ,deletedemp.getName()
            ,deletedemp.getName()
            ,deletedemp.getTeamName() != null ? deletedemp.getTeamName() : "없음"
            ,deletedemp.getRole() != null ? deletedemp.getRole() : "없음"
            ,deletedemp.getBirthday()
            ,deletedemp.getWorkStartDate());

            empRepository.deleteById(id);

            System.out.println(id+"번 직원 삭제 완료");
        } catch (IllegalArgumentException e) {
            System.err.println("오류: 해당 ID의 직원을 찾을 수 없습니다: " + id);
        } catch (Exception e){
            System.err.println("오류: 직원 삭제 중 알 수 없는 오류 발생. ID: " + id);
            System.err.println("오류: " + e.getMessage());
            throw new RuntimeException("직원 삭제중 오류가 발생했습니다.", e);
        }

        
    }
}
