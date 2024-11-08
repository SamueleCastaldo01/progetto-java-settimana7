package castaldosamuele.progetto_java_settimana7.services;

import castaldosamuele.progetto_java_settimana7.Exceptions.BadRequestException;
import castaldosamuele.progetto_java_settimana7.Exceptions.NotFoundException;
import castaldosamuele.progetto_java_settimana7.Payloads.NewPrenotazioneDTO;
import castaldosamuele.progetto_java_settimana7.Repositories.PrenotazioneRepository;
import castaldosamuele.progetto_java_settimana7.entities.Evento;
import castaldosamuele.progetto_java_settimana7.entities.Prenotazione;
import castaldosamuele.progetto_java_settimana7.entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private EventoService eventoService;

    //GET --------------------------------------------
    public List<Prenotazione> findAll() {
        return this.prenotazioneRepository.findAll();
    }

    public Prenotazione findById(long id) {
        return this.prenotazioneRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    //POST --------------------------------------------
    public Prenotazione save(long id_evento, Utente utente) {
        Evento evento = eventoService.findById(id_evento);

        //qui devo fare il controllo sul numero di posti
        long numeroPrenotazioni = prenotazioneRepository.countByEvento(evento);

        if (numeroPrenotazioni >= evento.getPost()) {
            throw new BadRequestException("Non ci sono posti disponibili per questo evento.");
        }

        Prenotazione newPrenotazione = new Prenotazione(utente, evento);
        return this.prenotazioneRepository.save(newPrenotazione);
    }

    //DELETE --------------------------------------------
    public void findByIdAndDelete(long id) {
        Prenotazione found = this.findById(id);
        this.prenotazioneRepository.delete(found);
    }

    public List<Prenotazione> findByUtente(Utente utente) {
        return prenotazioneRepository.findByUtente(utente);
    }

}
