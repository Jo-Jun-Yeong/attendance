package com.dev.attendance.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.attendance.domain.DayOff;
import com.dev.attendance.domain.Emp;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long>{

    void deleteByEmployee(Emp emp);

    Optional<DayOff> findByEmployee(Emp emp);
    
} 
