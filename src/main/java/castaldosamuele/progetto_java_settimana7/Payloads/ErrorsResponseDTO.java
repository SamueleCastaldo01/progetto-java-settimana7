package castaldosamuele.progetto_java_settimana7.Payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timestamp) {
}
