package com.dev.attendance.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dev.attendance.Repository.EmpDTO;
import com.dev.attendance.Repository.request.EmpCreateRequest;

@RestController
public class RedgitEmpController {

    private final List<EmpDTO> emp = new ArrayList<>();

    @PostMapping("/empCreate")
    public void reggitEmp(@RequestBody EmpCreateRequest request) {
        emp.add(); 


    }

}
