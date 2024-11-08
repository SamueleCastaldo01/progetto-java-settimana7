package castaldosamuele.progetto_java_settimana7.Payloads;

import jakarta.validation.constraints.NotNull;

public record NewPrenotazioneDTO(
        @NotNull(message = "L' id dell' utente è obbligatorio")
        long id_utente,
        @NotNull(message = "L' id dell' evento è obbligatorio")
        long id_evento
) {
}
