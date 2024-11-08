package castaldosamuele.progetto_java_settimana7.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewEventoDTO(
        @NotEmpty(message = "Il titolo è obbligatorio")
        String titolo,
        @NotEmpty(message = "La descrizione è obbligatoria")
        String descrizione,
        @NotNull(message = "La data di prenotazione è obbligatorio!")
        LocalDate date,
        @NotEmpty(message = "Il luogo è obbligatorio")
        String luogo,
        @NotNull(message = "Il numero di posti è obbligatorio!")
        int posti
) {

}
