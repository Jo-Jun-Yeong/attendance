package com.dev.attendance.Repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.attendance.domain.Attend;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Long> {
    
}
