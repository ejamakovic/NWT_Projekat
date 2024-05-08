package ba.nwt.keycard.RequestService.controllers;

import ba.nwt.keycard.RequestService.models.Team;
import ba.nwt.keycard.RequestService.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(){
        return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Team> getTeamById(@PathVariable Long id){
        Team team = teamService.getTeamById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@Valid @RequestBody Team team) {
        return new ResponseEntity<>(teamService.createTeam(team), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        boolean deleted = teamService.deleteTeamById(id);
        if (deleted) {
            return ResponseEntity.ok("Team with id " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found with id: " + id);
        }
    }


}
