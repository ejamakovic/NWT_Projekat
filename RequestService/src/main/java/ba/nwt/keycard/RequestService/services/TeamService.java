package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.Team.Team;
import ba.nwt.keycard.RequestService.models.Team.TeamDTO;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.repositories.TeamRepository;
import ba.nwt.keycard.RequestService.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Team> getAllTeams(){ return teamRepository.findAll();}


    public Team getTeamById(Long id){
        Optional<Team> team = teamRepository.findById(id);
        return team.orElse(null);
    }

    @Transactional
    public Team createTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        if (teamDTO.getManagerId() != null) {
            Optional<User> managerOptional = userRepository.findById(teamDTO.getManagerId());
            User manager = managerOptional.get();
            if (!managerOptional.isPresent()) {
                throw new IllegalArgumentException("Manager with ID " + teamDTO.getManagerId() + " does not exist.");
            }
            team.setManager(manager);
        }
        return teamRepository.save(team);
    }

    @Transactional
    public boolean deleteTeamById(Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            teamRepository.deleteTeamById(id);
            return true;
        }
        return false;
    }
}
