package springSecurityStudies.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import springSecurityStudies.entity.Role;

public record RegisterUserRequest(

        @NotEmpty(message = "O nome é obrigatório.")
        String name,

        @NotEmpty(message = "O email é obrigatório.")
        @Email
        String email,

        @NotEmpty(message = "A senha é obrigatória.")
        String password,

        Role role
) {
}