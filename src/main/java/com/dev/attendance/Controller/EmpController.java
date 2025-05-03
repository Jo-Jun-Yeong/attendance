package com.dev.attendance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dev.attendance.DTO.request.EmpCreateRequest;
import com.dev.attendance.Repository.EmpRepository;
import com.dev.attendance.Service.EmpService;
import com.dev.attendance.domain.Emp;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {

    private final EmpRepository empRepository;

    private final EmpService empService;
    
    public EmpController(EmpService empService, EmpRepository empRepository){
        this.empService = empService;
        this.empRepository = empRepository;

    }

    //사원입력
    @PostMapping("/api/emp/registEmp")
    public ResponseEntity<Emp> redgEmp(@RequestBody EmpCreateRequest request) {
        System.out.println("Before");
        Emp redgEmp = empService.redgEmp(request);
        System.out.println("After");

        return ResponseEntity.status(HttpStatus.OK).body(redgEmp);
        
    }
    
    //사원 전체 검색
    @GetMapping("/api/emp/getAllEmp")
    public ResponseEntity<List<Emp>> getAllEmp(){
        
        List<Emp> empList = empService.getAllEmp();
        
        return ResponseEntity.status(HttpStatus.FOUND).body(empList);
    }

    @DeleteMapping("/api/emp/delete/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable Long id){
        empService.deleteEmp(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
