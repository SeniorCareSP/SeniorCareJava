package seniorcare.crudseniorcare.domain.visita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.visita.Visita;

import java.time.LocalDate;

public interface VisitaRepository extends JpaRepository<Visita, Integer> {
    long countByData(LocalDate data);

}
