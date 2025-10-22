package springSecurityStudies.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import springSecurityStudies.entity.User;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfiguration {
    private final String secret = "secret";

    public String generateToken(User user) {

        final Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("userId", user.getId())
                .withClaim("roles", user.getRoles().stream()
                        .map(Enum::name)
                        .toList())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {
        final Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData.builder()
                    .userId(decodedJWT.getClaim("userId").asLong())
                    .email(decodedJWT.getSubject())
                    .roles(decodedJWT.getClaim("roles").asList(String.class))
                    .build());
        } catch (JWTVerificationException exception) {
            return Optional.empty();
        }
    }

}