package springSecurityStudies.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(
        @NotEmpty(message = "O nome é obrigatório.") String name,
        @NotEmpty(message = "O email é obrigatório.") String email,
        @NotEmpty(message = "A senha é obrigatória.") String password
) {
}
