package ba.nwt.keycard.RoomService.RibbonProxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// RibbonClient is automatically included with the @FeignClient annotation
@FeignClient(name = "RequestService")
public interface RequestServiceProxy {
    @GetMapping("/user/id/{id}")
    public String getUserById(@PathVariable("id") Long id);

    @GetMapping("/user/testFeign/{id}")
    public String testFeign(@PathVariable("id") Long id);
}