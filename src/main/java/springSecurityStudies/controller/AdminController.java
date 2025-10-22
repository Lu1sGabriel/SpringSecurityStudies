package springSecurityStudies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping(value = "/panel")
    public ResponseEntity<String> adminPanel() {
        return ResponseEntity.ok().body("Apenas admin pode acessar essa rota.");
    }

}