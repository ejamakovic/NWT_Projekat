package ba.nwt.keycard.RequestService.clients;

import ba.nwt.keycard.RequestService.models.dtos.RoomDTO;
import ba.nwt.keycard.RequestService.models.dtos.TempAccessGrantDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ROOMSERVICE")
public interface RoomClient {
    @PostMapping("/rs_api/rooms/user")
    List<RoomDTO> fetchRoomsByIds(@RequestBody List<Long> roomsIds);

    @GetMapping("/rs_api/rooms/{id}")
    Optional<RoomDTO> getRoomById(@PathVariable("id") Long roomId);

    @PostMapping("/rs_api/tempAccessGrants")
    public ResponseEntity<TempAccessGrantDTO> addTempAccessGrant(@RequestBody TempAccessGrantDTO tempAccessGrantDTO);
}
