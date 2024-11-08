package castaldosamuele.progetto_java_settimana7.services;

import castaldosamuele.progetto_java_settimana7.Exceptions.NotFoundException;
import castaldosamuele.progetto_java_settimana7.Repositories.UtenteRepository;
import castaldosamuele.progetto_java_settimana7.entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public Utente findById(long id) {
        return this.utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

}
