package ba.nwt.keycard.RequestService.clients;

import ba.nwt.keycard.RequestService.models.KeycardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "PERMISSIONSERVICE")
public interface KeycardClient {

    @GetMapping("/api/keycardpermissions/keycard/{keycardId}")
    List<?> getAllPermissionsByKeycardId(@PathVariable("keycardId") Integer keycardId);

    @GetMapping("/api/keycards/{keycardId}")
    Optional<KeycardDTO> getKeycard(@PathVariable("keycardId") Integer keycardId);
}