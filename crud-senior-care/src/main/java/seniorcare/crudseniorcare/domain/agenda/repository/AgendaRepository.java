package seniorcare.crudseniorcare.domain.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seniorcare.crudseniorcare.domain.agenda.Agenda;

import java.util.UUID;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, UUID> {

}
