package ba.nwt.keycard.RequestService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("PERMISSIONSERVICE")
public interface PermissionServiceClient {

    @GetMapping("/api/keycardpermissions/keycard/{keycardId}")
    public ResponseEntity<?> getPermissionsByKeycardId(@PathVariable("keycardId") Integer keycard);
}