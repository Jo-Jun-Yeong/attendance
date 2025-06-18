package com.dev.attendance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DayOff {
    @Id
    Long id;

    @Column(nullable = false, name="dayoff")
    private int dayOff;
}
