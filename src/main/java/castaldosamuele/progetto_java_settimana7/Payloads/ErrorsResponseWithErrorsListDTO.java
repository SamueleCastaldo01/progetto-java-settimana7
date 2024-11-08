package castaldosamuele.progetto_java_settimana7.Payloads;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorsResponseWithErrorsListDTO(String message, LocalDateTime timestamp, Map<String, String> errors) {

}
