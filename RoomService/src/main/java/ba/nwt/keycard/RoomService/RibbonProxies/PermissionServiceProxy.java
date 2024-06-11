package ba.nwt.keycard.RoomService.RibbonProxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import ba.nwt.keycard.RoomService.models.PermissionDTOs.PermissionDTO;

@FeignClient(name = "PermissionService")
public interface PermissionServiceProxy {
    @GetMapping("/api/keycardpermissions/keycard/{id}")
    public List<PermissionDTO> getKeycardPermissions(@PathVariable("id") Long id);
}
