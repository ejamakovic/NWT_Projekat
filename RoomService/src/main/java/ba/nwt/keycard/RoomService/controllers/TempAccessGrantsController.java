package ba.nwt.keycard.RoomService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrantDTO;
import ba.nwt.keycard.RoomService.services.TempAccessGrantsService;

@RestController
@RequestMapping("/rs_api/tempAccessGrants")
public class TempAccessGrantsController {
    private final TempAccessGrantsService tempAccessGrantsService;

    @Autowired
    public TempAccessGrantsController(TempAccessGrantsService tempAccessGrantsService) {
        this.tempAccessGrantsService = tempAccessGrantsService;
    }

    @PostMapping
    public ResponseEntity<TempAccessGrantDTO> addTempAccessGrant(@RequestBody TempAccessGrantDTO tempAccessGrantDTO) {
        System.out.println("TempAccessGrantsController.addTempAccessGrant");
        return ResponseEntity.ok().body(tempAccessGrantsService.addTempAccessGrant(tempAccessGrantDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TempAccessGrantDTO> getTempAccessGrantById(@RequestBody Long id) {
        return ResponseEntity.ok().body(tempAccessGrantsService.getTempAccessGrantById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TempAccessGrantDTO>> getTempAccessGrantsByUserId(@RequestBody Long userId) {
        return ResponseEntity.ok().body(tempAccessGrantsService.getTempAccessGrantsByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTempAccessGrant(@RequestBody Long id) {
        tempAccessGrantsService.deleteTempAccessGrant(id);
        return ResponseEntity.ok().body("Temp access grant with id " + id + " has been deleted successfully.");
    }

    /*
     * public TempAccessGrantDTO addTempAccessGrant(TempAccessGrant tempAccessGrant)
     * {
     * return tempAccessGrantMapper.toDTO(repository.save(tempAccessGrant));
     * }
     * 
     * public TempAccessGrantDTO getTempAccessGrantById(Long id) {
     * return tempAccessGrantMapper.toDTO(repository.findById(id).orElse(null));
     * }
     * 
     * public List<TempAccessGrantDTO> getTempAccessGrantsByUserId(Long userId) {
     * return tempAccessGrantMapper.toDTOList(repository.findByUserId(userId));
     * }
     * 
     * public void deleteTempAccessGrant(Long id) {
     * repository.deleteById(id);
     * }
     */
}
