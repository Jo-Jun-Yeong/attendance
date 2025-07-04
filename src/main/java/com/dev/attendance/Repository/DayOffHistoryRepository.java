package com.dev.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.attendance.domain.DayOffHistory;
import com.dev.attendance.domain.Emp;

import java.util.List;
import java.util.Optional;


public interface DayOffHistoryRepository extends JpaRepository<DayOffHistory, Long> {
    
    // @Query("")
    // Optional<List<DayOffHistory>> findAllByEmployeeIdAndYearAndMonth(@@Param("employeeId") Long employeeId, @Param("yearMonth") LocalDateTime yearMonth);

    //모든 직원 년, 월 연차 검색
    @Query("SELECT D FROM DayOffHistory D WHERE YEAR(D.applyDate) = :year AND MONTH(D.applyDate)= :month")
    List<DayOffHistory> findAllByYearAndMonth(@Param("year") int year, @Param("month") int month);


    //직원별 년, 월 연차 검색
    @Query("SELECT D FROM DayOffHistory D WHERE D.employee = :employee AND YEAR(D.applyDate) = :year AND MONTH(D.applyDate)= :month")
    List<DayOffHistory> findByEmployeeAndYearAndMonth(@Param("employee")Emp employee,@Param("year") int year, @Param("month") int month);
}
