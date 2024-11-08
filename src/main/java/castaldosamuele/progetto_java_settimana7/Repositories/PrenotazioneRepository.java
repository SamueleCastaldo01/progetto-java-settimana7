package castaldosamuele.progetto_java_settimana7.Repositories;


import castaldosamuele.progetto_java_settimana7.entities.Evento;
import castaldosamuele.progetto_java_settimana7.entities.Prenotazione;
import castaldosamuele.progetto_java_settimana7.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtente(Utente utente);
    long countByEvento(Evento evento);
    List<Prenotazione> findByUtenteAndEvento(Utente utente, Evento evento);
}
