package ba.nwt.keycard.RequestService.models.Team;

import ba.nwt.keycard.RequestService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    private final UserRepository userRepository;

    @Autowired
    public TeamMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TeamDTO toDTO(Team team) {
        if(team != null) {
            TeamDTO dto = new TeamDTO();
            dto.setName(team.getName());
            if (team.getManager() != null) {
                dto.setManagerId(team.getManager().getId());
            }
            return dto;
        }
        return null;
    }

    public List<TeamDTO> toDTOList(List<Team> teams) {
        return teams.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Team toEntity(TeamDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());
        if (dto.getManagerId() != null) {
            User manager = userRepository.findById(dto.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getManagerId()));
            team.setManager(manager);
        }
        return team;
    }

    public List<Team> toEntityList(List<TeamDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
