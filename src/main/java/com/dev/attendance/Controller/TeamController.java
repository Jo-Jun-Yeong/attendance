package com.dev.attendance.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.dev.attendance.DTO.request.TeamCreateRequest;
import com.dev.attendance.DTO.request.TeamUpdateRequest;
import com.dev.attendance.Repository.TeamRepository;
import com.dev.attendance.Service.TeamService;
import com.dev.attendance.domain.Team;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class TeamController {
    
    private final TeamRepository teamRepository;

    private final TeamService teamService;

    public TeamController(TeamRepository teamRepository, TeamService teamService){
        this.teamRepository= teamRepository;
        this.teamService= teamService;
        
    }

    @PostMapping("/api/tema/regist")
    public ResponseEntity<Team> regisTeam(@RequestBody TeamCreateRequest request) {

        Team redgTeam = teamService.redgTeam(request);
        
        return ResponseEntity.status(HttpStatus.OK).body(redgTeam);
    }
    

    @GetMapping("/api/team/getTeam")
    public ResponseEntity<Team> getTeam(@RequestParam Long id) {

        Team team = teamService.findTeam(id);

        return ResponseEntity.status(HttpStatus.OK).body(team);
    }
    

    @GetMapping("/api/team/getAll")
    public ResponseEntity<List<Team>> getAllTeam() {

        List<Team> teamList = teamService.getAllTeam();

        return ResponseEntity.status(HttpStatus.OK).body(teamList);
    }
    
    @PutMapping("api/team/update/{id}")
    public ResponseEntity<Team> TeamUpdate(@PathVariable Long id, @RequestBody TeamUpdateRequest request) {
        Team updatedTeam = teamService.updateTeam(id, request);
        
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeam);
    }

    @DeleteMapping("/api/team/delete/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        teamRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
