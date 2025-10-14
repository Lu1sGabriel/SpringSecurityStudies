package springSecurityStudies.configuration;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
}
