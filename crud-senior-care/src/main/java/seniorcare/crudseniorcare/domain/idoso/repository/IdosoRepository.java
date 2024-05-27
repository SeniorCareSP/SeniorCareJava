package seniorcare.crudseniorcare.domain.idoso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.idoso.Idoso;

import java.util.UUID;

public interface IdosoRepository extends JpaRepository<Idoso, Integer> {
}
