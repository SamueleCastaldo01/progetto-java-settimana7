package castaldosamuele.progetto_java_settimana7.services;

import castaldosamuele.progetto_java_settimana7.Exceptions.NotFoundException;
import castaldosamuele.progetto_java_settimana7.Payloads.NewEventoDTO;
import castaldosamuele.progetto_java_settimana7.Repositories.EventoRepository;
import castaldosamuele.progetto_java_settimana7.entities.Evento;
import castaldosamuele.progetto_java_settimana7.entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UtenteService utenteService;

    public List<Evento> findAll() {
        return this.eventoRepository.findAll();
    }

    public Evento findById(long id) {
        return this.eventoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    //POST --------------------------------------------
    public Evento save(NewEventoDTO body) {
        Utente utente = utenteService.findById(body.id_utente());
        Evento newEvento = new Evento(body.titolo(), body.descrizione(), body.date(), body.luogo(), body.posti(), utente);
        return this.eventoRepository.save(newEvento);
    }

    //PUT --------------------------------------------
    public Evento findByIdAndUpdate(long id, NewEventoDTO body) {
        Evento found = this.findById(id);

        found.setTitolo(body.titolo());
        found.setDescrizione(body.descrizione());
        found.setDate(body.date());
        found.setLuogo(body.luogo());
        found.setPost(body.posti());

        return this.eventoRepository.save(found);
    }

    //DELETE --------------------------------------------
    public void findByIdAndDelete(long id) {
        Evento found = this.findById(id);
        this.eventoRepository.delete(found);
    }

}
