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
    public Emp redgEmp(EmpCreateRequest request){

        Emp emp = new Emp(
            request.getName(),
            request.getTeamName(),
            request.getRole(),
            request.getBirthday()
        );

        Emp redgEmp = empRepository.saveAndFlush(emp);

        return redgEmp;
    }

    //사원 전체검색
    public List<Emp> getAllEmp(){
        List<Emp> resultList = empRepository.findAll();
        // --- ★★★ 이 부분들을 추가하여 실제 반환값을 확인합니다 ★★★ ---
        System.out.println("--- Debugging getAllEmp ---");
        System.out.println("Repository에서 가져온 raw 결과: " + resultList); // List 객체 자체 출력
        System.out.println("결과 리스트의 타입: " + resultList.getClass().getName()); // 리스트 타입 확인
        System.out.println("결과 리스트의 크기: " + resultList.size()); // 리스트 크기 확인
        if (!resultList.isEmpty()) {
            System.out.println("결과 리스트의 첫 번째 요소 타입: " + resultList.get(0).getClass().getName()); // 첫 요소 타입 확인
            System.out.println("결과 리스트의 첫 번째 요소: " + resultList.get(0)); // 첫 요소 객체 출력
        }
        System.out.println("--- End Debugging ---");
        // -------------------------------------------------------
        return resultList;
    }
}
