package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.Team;
import ba.nwt.keycard.RequestService.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams(){ return teamRepository.findAll();}

    public Team getTeamById(Long id){
        Optional<Team> team = teamRepository.findById(id);
        return team.orElse(null);
    }

    public Team createTeam(Team team){
        teamRepository.save(team);
        return team;
    }
}
