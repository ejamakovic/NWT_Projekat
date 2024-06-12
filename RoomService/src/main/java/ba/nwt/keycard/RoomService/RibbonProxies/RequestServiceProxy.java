package ba.nwt.keycard.RoomService.RibbonProxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ba.nwt.keycard.RoomService.models.RequestServiceDTOs.LogDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

// RibbonClient is automatically included with the @FeignClient annotation
@FeignClient(name = "RequestService")
public interface RequestServiceProxy {
    @GetMapping("/user/id/{id}")
    public String getUserById(@PathVariable("id") Long id);

    @GetMapping("/user/testFeign/{id}")
    public String testFeign(@PathVariable("id") Long id);

    @PostMapping("/user/log")
    public LogDTO addLog(@RequestBody LogDTO logDTO);

    @GetMapping("/user/getUserIdByCardId/{keycardId}")
    public Long getUserIdByCardId(@PathVariable("keycardId") Long keycardId);
}