package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Idioma;

import java.util.UUID;

public interface IdiomaRepository extends JpaRepository<Idioma, UUID> {
}
