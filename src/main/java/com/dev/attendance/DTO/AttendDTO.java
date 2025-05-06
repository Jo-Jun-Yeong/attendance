package com.dev.attendance.DTO;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AttendDTO {
    private Long employeeId;
    private LocalDateTime goToWork;
    private LocalDateTime offWork;
}
