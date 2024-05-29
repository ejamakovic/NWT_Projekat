package ba.nwt.keycard.RequestService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PERMISSIONSERVICE")
public interface KeycardPermissionClient {

    @GetMapping("/api/keycardpermissions/keycard/{keycardId}")
    ResponseEntity<List<?>> getAllPermissionsByKeycardId(@PathVariable("keycardId") Integer keycardId);
}