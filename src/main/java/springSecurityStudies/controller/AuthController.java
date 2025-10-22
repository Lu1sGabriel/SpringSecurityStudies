package springSecurityStudies.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springSecurityStudies.configuration.TokenConfiguration;
import springSecurityStudies.dto.request.LoginRequest;
import springSecurityStudies.dto.request.RegisterUserRequest;
import springSecurityStudies.dto.response.LoginResponse;
import springSecurityStudies.dto.response.RegisterUserResponse;
import springSecurityStudies.entity.Role;
import springSecurityStudies.entity.User;
import springSecurityStudies.exception.types.DataConflictException;
import springSecurityStudies.repository.UserRepository;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfiguration tokenConfiguration;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        User user = (User) authentication.getPrincipal();
        String token = tokenConfiguration.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        validadeUniqueEmail(request.email());

        var role = (request.role() != null) ? request.role() : Role.ROLE_USER;

        User user = new User(request.name(), request.email(), passwordEncoder.encode(request.password()), role);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(
                user.getName(),
                user.getEmail()
        ));
    }

    private void validadeUniqueEmail(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new DataConflictException("Já existe um usuário com esse email.");
        }
    }

}