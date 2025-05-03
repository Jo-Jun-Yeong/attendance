package com.dev.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.attendance.domain.Team;

import jakarta.persistence.Column;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{


}
