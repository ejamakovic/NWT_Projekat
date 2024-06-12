package ba.nwt.keycard.RoomService.RibbonProxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ba.nwt.keycard.RoomService.models.PermissionServiceDTOs.PermissionDTO;

import java.util.List;

@FeignClient(name = "PermissionService")
public interface PermissionServiceProxy {
    @GetMapping("/api/keycardpermissions/keycard/{id}")
    public List<PermissionDTO> getKeycardPermissions(@PathVariable("id") Long id);

    @GetMapping("/api/keycardpermissions/checkPermission/{keycardId}")
    public Boolean checkPermission(@PathVariable("keycardId") Long keycardId,
            @RequestParam("buildingId") Long buildingId,
            @RequestParam("floorId") Long floorId, @RequestParam("roomId") Long roomId);
}
