package com.dev.attendance.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.attendance.domain.DayOffHistory;

public interface DayOffHistoryRepository extends JpaRepository<DayOffHistory, Long> {
    
}
