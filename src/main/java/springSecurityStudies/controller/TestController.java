package springSecurityStudies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teste")
public class TestController {

    @GetMapping
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok().body("Testando a segurança.");
    }

}