package springSecurityStudies.dto.response;

import java.time.Instant;

public record ErrorResponse(
        String error,
        Instant instant
) {
}