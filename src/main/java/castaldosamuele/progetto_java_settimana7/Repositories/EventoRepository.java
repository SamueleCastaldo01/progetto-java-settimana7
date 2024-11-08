package castaldosamuele.progetto_java_settimana7.Repositories;

import castaldosamuele.progetto_java_settimana7.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
