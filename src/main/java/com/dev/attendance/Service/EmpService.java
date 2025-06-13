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
import com.dev.attendance.DTO.request.EmpUpdateRequest;
import com.dev.attendance.Repository.EmpRepository;
import com.dev.attendance.Repository.TeamRepository;
import com.dev.attendance.domain.Emp;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class EmpService {

    private final TeamService teamService;
    private final EmpRepository empRepository;

    @Autowired
    public EmpService (EmpRepository empRepository, TeamService teamService){
        this.empRepository=empRepository;
        this.teamService = teamService;
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

        if(!request.getTeamName().equals(null)){
            this.updateTeamCount(redgEmp.getTeamName());
        }
        
        System.out.println("데이터 입력 '종료'.");
        return redgEmp;
    }

    //사원 검색
    @Transactional
    public Emp getEmp(Long id){
        System.out.println("사원을 찾습니다.");
        Emp emp = empRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 id: "+ id + "의 사원이 없습니다"));
        System.out.println("사원 찾기 종료");

        return emp;
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
        String teamName = "";

        deletedemp = empRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 id: "+ id + "의 사원이 없습니다"));
        teamName = deletedemp.getTeamName();
        
        try {
            
            System.out.printf("%s의 아래의 정보가 삭제됩니다.\n 이름: %s \n 팀: %s \n 직급: %s \n 생일: %s \n 입사일: %s" 
            ,deletedemp.getName()
            ,deletedemp.getName()
            ,deletedemp.getTeamName() != null ? deletedemp.getTeamName() : "없음"
            ,deletedemp.getRole() != null ? deletedemp.getRole() : "없음"
            ,deletedemp.getBirthday()
            ,deletedemp.getWorkStartDate());

            empRepository.deleteById(id);  
            System.out.println(id+"번 직원 삭제 완료");


        } catch (EntityNotFoundException e) {
            System.err.println("오류: 해당 ID의 직원을 찾을 수 없습니다: " + id);
        } catch (Exception e){
            System.err.println("오류: 직원 삭제 중 알 수 없는 오류 발생. ID: " + id);
            System.err.println("오류: " + e.getMessage());
            throw new RuntimeException("직원 삭제중 오류가 발생했습니다.", e);
        }

        //팀 인원수 조정 
        this.updateTeamCount(teamName);
    }

    public Emp updateEmp(Long id, EmpUpdateRequest request){
        Emp findEmp = this.getEmp(id);
        String beforeTeamName = findEmp.getTeamName() == null ? findEmp.getTeamName(): null ;
        String afterTeamName = request.getTeamName() == null ? request.getTeamName() : null ;

        if(!request.getName().isEmpty()){
            System.out.printf("\s 사원의 이름을 \s로 변경하였습니다.",findEmp.getName(), request.getName());
            findEmp.setName(request.getName());
        }

        if(!request.getRole().isBlank()){
            System.out.printf("\s 사원의 직위를 \s -> \s로 변경하였습니다.",findEmp.getName(),findEmp.getRole(), request.getRole());
            findEmp.setRole(request.getRole());
        }

        if(!request.getTeamName().isBlank()){
            System.out.printf("\s 사원의 직위를 \s -> \s로 변경하였습니다.",findEmp.getName(),findEmp.getTeamName(), request.getTeamName());
            findEmp.setTeamName(request.getTeamName());

            //team이 변경될 때 team인원수 조정
            this.updateTeamCount(beforeTeamName);
            this.updateTeamCount(afterTeamName);
            
        }

        return empRepository.save(findEmp);
    }

    //직원 존재 유무 판단
    public boolean isEmp(Long id){

        boolean flag = empRepository.existsById(id);

            if(flag){
                System.out.println("직원이 검색되었습니다.");
                return flag;
            }
            else {
                System.out.println("해당 직원을 찾을 수 없습니다.");
                return flag;

            }
    }

    public void updateTeamCount(String teamName){

        System.out.printf("팀 \" \s \"의 인원 조정 시작\n",teamName);
        int count = teamService.getTeamMemberCount(teamName);
        teamService.updateTeamMemberCount(teamName, count);
        System.out.println("팀 인원수 조정 끝");
    }
}
