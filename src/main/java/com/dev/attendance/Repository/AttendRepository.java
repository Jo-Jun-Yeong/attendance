package com.dev.attendance.Repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.attendance.DTO.request.WorkTimeRequest;
import com.dev.attendance.DTO.response.AttendResponse;
import com.dev.attendance.DTO.response.WorkTimeResponse;
import com.dev.attendance.domain.Attend;
import java.util.List;
import java.util.Optional;


@Repository
public interface AttendRepository extends JpaRepository<Attend, Long> {

    @Query("SELECT a FROM Attend a WHERE a.employeeId = :employeeId AND a.offWork IS NULL")
    Optional<Attend> addOffWorkById(@Param("employeeId") Long employeeId);

    

    // Optional<List<Attend>> findAllByEmployeeIdAndGoToWork
    // (@Param("employee_id")Long employee_id, @Param("month")LocalDateTime month);

    @Query("SELECT a FROM Attend a WHERE a.employeeId = :employeeId AND a.goToWork BETWEEN :startDate AND :endDate")
    Optional<List<Attend>> findAllByEmployeeIdAndGoToWork(@Param("employeeId") Long employeeId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
