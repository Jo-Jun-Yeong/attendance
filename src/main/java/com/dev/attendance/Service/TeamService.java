package com.dev.attendance.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.attendance.DTO.request.TeamCreateRequest;
import com.dev.attendance.Repository.TeamRepository;
import com.dev.attendance.domain.Team;

@Service
public class TeamService {


    private final TeamRepository teamRepository;
    @Autowired
    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Team redgTeam(TeamCreateRequest request){
        
        System.out.println("Team 등록을 시작합니다.");
        Team team = new Team(
            request.getTeamName(),
            request.getManager()
        );

        System.out.printf("팀 이블: %s \n 매니저: %s \n",request.getTeamName(), request.getManager());
        teamRepository.save(team);
        System.out.println("Team 등록을 종료합니다.");

        return team;
    }


    @Transactional
    public Team findTeam(Long id){
        
        System.out.println("팀을 검색합니다.");

        Team team = teamRepository.findById(id).orElseThrow( () -> new RuntimeException("해당하는 팀이 없습니다."));
        


        return team;
    }

    @Transactional
    public List<Team> getAllTeam() {
        System.out.println("모든 Team을 불러옵니다.");
        List<Team> teamList = teamRepository.findAll();
        return teamList;
    }
    @Transactional
    public void deleteTeam(Long id){
        System.out.println("팀을 삭제합니다.");
        Team team =  teamRepository.findById(id).orElseThrow(() -> new RuntimeException("ID가"+ id+"인 팀을 찾을 수 없습니다."));

        try {
            
            teamRepository.deleteById(team.getId());
        } catch (Exception e) {
            System.err.println("오류: 팀 삭제중 알 수 없는 오류 발생. ID: "+team.getId());
            System.err.println("오류 메시지: "+ e.getMessage());
            throw new RuntimeException("팀 삭제중 오류 발생", e);
        }

    }


}
