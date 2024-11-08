package castaldosamuele.progetto_java_settimana7.Controllers;

import castaldosamuele.progetto_java_settimana7.Exceptions.UnauthorizedException;
import castaldosamuele.progetto_java_settimana7.entities.Prenotazione;
import castaldosamuele.progetto_java_settimana7.entities.Utente;
import castaldosamuele.progetto_java_settimana7.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazione")
public class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService;

    //questo lo dovrebbe vedere solamente l'admin, ma per semplcità lo ho messo generico
    @GetMapping
    public List<Prenotazione> findAll() {
        return this.prenotazioneService.findAll();
    }

    //questo lo dovrebbe vedere solamente l'admin lo ho messo generico
    @GetMapping("/{id}")
    public Prenotazione findById(@PathVariable long id) {
        return this.prenotazioneService.findById(id);
    }

    //crezione dell'evento
    @PostMapping("/{id_evento}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ORGANIZER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione save(@PathVariable long id_evento, @AuthenticationPrincipal Utente currentAuthenticatedUser) {

        return this.prenotazioneService.save(id_evento, currentAuthenticatedUser);
    }


    //questo lo dovrebbe fare solamente l'admin, ma per semplcità lo ho messo generico
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.prenotazioneService.findByIdAndDelete(id);
    }


    //Extra
    // /Me endpoints----------------------------------------------------------------
    @GetMapping("/me")
    public List<Prenotazione> getMyPrenotazioni(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        return prenotazioneService.findByUtente(currentAuthenticatedUser);
    }


    @DeleteMapping("/me/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(
            @PathVariable long id,
            @AuthenticationPrincipal Utente currentAuthenticatedUser) {

            //verifica se l'id è corretto
            Prenotazione prenotazione = prenotazioneService.findById(id);

        if (prenotazione == null || prenotazione.getUtente().getId() != currentAuthenticatedUser.getId()) {
            throw new UnauthorizedException("Non puoi eliminare una prenotazione che non ti appartiene!");
        }

        prenotazioneService.findByIdAndDelete(id);
    }
}
