package ba.nwt.keycard.RequestService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ROOMSERVICE")
public interface RoomClient {
    @PostMapping("/rs_api/rooms/user")
    List<?> fetchRoomsByIds(@RequestBody List<Long> roomsIds);
}
