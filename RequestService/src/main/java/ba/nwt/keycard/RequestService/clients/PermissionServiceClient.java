package ba.nwt.keycard.RequestService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "permissionService")
public interface PermissionServiceClient {

    @GetMapping("/api/keycards/{keycardId}/permissions")
    List<String> getPermissionsByKeycardId(@PathVariable("keycardId") Long keycard);
}