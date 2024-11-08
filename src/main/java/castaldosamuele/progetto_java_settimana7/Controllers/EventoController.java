package castaldosamuele.progetto_java_settimana7.Controllers;

import castaldosamuele.progetto_java_settimana7.Exceptions.BadRequestException;
import castaldosamuele.progetto_java_settimana7.Payloads.NewEventoDTO;
import castaldosamuele.progetto_java_settimana7.entities.Evento;
import castaldosamuele.progetto_java_settimana7.entities.Utente;
import castaldosamuele.progetto_java_settimana7.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    EventoService eventoService;

    //Senza permessi per semplicità ma dovrebbe essere un admin
    @GetMapping
    public List<Evento> findAll() {
        return this.eventoService.findAll();
    }

    //Senza permessi per semplicità ma dovrebbe essere un admin
    @GetMapping("/{id}")
    public Evento findById(@PathVariable long id) {
        return this.eventoService.findById(id);
    }


    //qui va messo l'utente con il ruolo ORGANIZER
    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento save(@RequestBody @Validated NewEventoDTO body, BindingResult validationResult, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.eventoService.save(body, currentAuthenticatedUser);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Evento findByIdAndUpdate(@PathVariable long id, @RequestBody @Validated NewEventoDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        // Ovunque ci sia un body bisognerebbe validarlo!
        return this.eventoService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.eventoService.findByIdAndDelete(id);
    }

}
