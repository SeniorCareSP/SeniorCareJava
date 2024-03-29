package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Agenda;

import java.util.UUID;

public interface AgendaRepository extends JpaRepository<Agenda, UUID> {

}
