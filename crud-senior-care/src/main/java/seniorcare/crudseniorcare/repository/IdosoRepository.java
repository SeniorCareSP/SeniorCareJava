package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Idoso;

import java.util.UUID;

public interface IdosoRepository extends JpaRepository<Idoso, UUID> {
}
