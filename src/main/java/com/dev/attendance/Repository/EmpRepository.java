package com.dev.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.attendance.domain.Emp;

public interface EmpRepository extends JpaRepository<Emp, Long> {

    @Query("SELECT MAX(e.id) FROM Emp e")
    Long findMaxId();


}
