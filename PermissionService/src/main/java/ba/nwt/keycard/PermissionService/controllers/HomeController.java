package ba.nwt.keycard.PermissionService.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Assuming "index.html" exists in your templates directory
    }
}
