package com.dev.attendance.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dev.attendance.DTO.request.EmpCreateRequest;
import com.dev.attendance.DTO.request.EmpUpdateRequest;
import com.dev.attendance.Repository.EmpRepository;
import com.dev.attendance.Service.EmpService;
import com.dev.attendance.domain.Emp;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/emp")
public class EmpController {

    private final EmpRepository empRepository;

    private final EmpService empService;
    
    public EmpController(EmpService empService, EmpRepository empRepository){
        this.empService = empService;
        this.empRepository = empRepository;

    }

    //사원입력
    @PostMapping("/regist")
    public ResponseEntity<Emp> registEmp(@RequestBody EmpCreateRequest request) {
        System.out.println("Controller -> 사원 생성 시작 ");
        Emp redgEmp = empService.redgEmp(request);
        
        System.out.println("Controller -> 사원 생성 종료");

        return ResponseEntity.status(HttpStatus.OK).body(redgEmp);
        
    }
    
    //사원 1명 정보 가져오기
    @GetMapping("/getEmp")
    public ResponseEntity<Emp> getEmp(@RequestParam Long id) {

        Emp emp = empService.getEmp(id);

        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }
    

    //사원 전체 검색
    @GetMapping("/getAll")
    public ResponseEntity<List<Emp>> getAllEmp(){
        
        List<Emp> empList = empService.getAllEmp();
        
        return ResponseEntity.status(HttpStatus.FOUND).body(empList);
    }

    
    //사원 정보 업데이트
    @PutMapping("/update/{id}")
    public ResponseEntity<Emp> updateEmp(@PathVariable Long id, @RequestBody EmpUpdateRequest request) {
        Emp updatedEmp = empService.updateEmp(id, request);
        
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmp);
    }
    
    //사원 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable Long id){
        empService.deleteEmp(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    //사원의 id로 
}
