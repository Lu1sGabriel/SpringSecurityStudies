package springSecurityStudies.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "O email é obrigatório.") String email,
        @NotEmpty(message = "A senha é obrigatória.") String password
) {
}