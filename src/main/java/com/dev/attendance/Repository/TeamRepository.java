package com.dev.attendance.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.attendance.domain.Team;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{

    @Query("SELECT COUNT(e) FROM Emp e WHERE e.teamName = :teamName")
    int getMemberCountByTeamName(@Param("teamName") String teamName);

    Optional<Team> findByTeamName(@Param("teamName") String teamName);

    void deleteByTeamName(@Param("teamName") String teamName);
}
