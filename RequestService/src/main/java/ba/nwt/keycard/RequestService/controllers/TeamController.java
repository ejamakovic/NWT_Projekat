package ba.nwt.keycard.RequestService.controllers;

import ba.nwt.keycard.RequestService.models.Team;
import ba.nwt.keycard.RequestService.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/")
    public ResponseEntity<List<Team>> getAllTeams(){
        return new ResponseEntity<List<Team>>(teamService.getAllTeams(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Team> getTeamById(@PathVariable Long id){
        Team team = teamService.getTeamById(id);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Team> createUser(@RequestBody Team team) {
        Team team1 = teamService.createTeam(team);
        return new ResponseEntity<Team>(team1, HttpStatus.OK);
    }
}
