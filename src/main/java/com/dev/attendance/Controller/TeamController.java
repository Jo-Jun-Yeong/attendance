package com.dev.attendance.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.dev.attendance.DTO.request.TeamCreateRequest;
import com.dev.attendance.DTO.request.TeamUpdateRequest;
import com.dev.attendance.Repository.TeamRepository;
import com.dev.attendance.Service.TeamService;
import com.dev.attendance.domain.Team;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/team")
public class TeamController {
    


    private final TeamService teamService;

    public TeamController(TeamService teamService){

        this.teamService= teamService;
        
    }

    @PostMapping("/regist")
    public ResponseEntity<Team> regisTeam(@RequestBody TeamCreateRequest request) {

        Team redgTeam = teamService.redgTeam(request);
        
        return ResponseEntity.status(HttpStatus.OK).body(redgTeam);
    }
    

    @GetMapping("/getTeam")
    public ResponseEntity<Team> getTeam(@RequestParam Long id) {

        Team team = teamService.findTeam(id);

        return ResponseEntity.status(HttpStatus.OK).body(team);
    }
    

    @GetMapping("/getAll")
    public ResponseEntity<List<Team>> getAllTeam() {

        List<Team> teamList = teamService.getAllTeam();

        return ResponseEntity.status(HttpStatus.OK).body(teamList);
    }
    
    @PutMapping("/update/{team}")
    public ResponseEntity<Team> TeamManagerUpdate(@RequestBody TeamUpdateRequest request) {
        Team updatedTeam = teamService.updateTeam(request);
        
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeam);
    }

    @DeleteMapping("/delete/{teamName}")
    public ResponseEntity<Void> deleteTeam(@RequestParam String teamName){
        teamService.deleteTeam(teamName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
