package springSecurityStudies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/dashboard")
    public ResponseEntity<String> userDashboard() {
        return ResponseEntity.ok().body("Rota que tanto usuários com a role 'USER' ou 'ADMIN' podem acessar");
    }

}