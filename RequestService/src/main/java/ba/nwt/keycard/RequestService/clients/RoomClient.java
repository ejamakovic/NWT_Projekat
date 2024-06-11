package ba.nwt.keycard.RequestService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ROOMSERVICE")
public interface RoomClient {
    @PostMapping("/rs_api/rooms/user")
    List<?> fetchRoomsByIds(@RequestBody List<Long> roomsIds);

    @GetMapping("/rs_api/rooms/{id}")
    Optional<?> getRoomById(@PathVariable("id") Long roomId);
}
