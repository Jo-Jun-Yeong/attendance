package com.dev.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.attendance.domain.DayOff;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long>{

    
} 
