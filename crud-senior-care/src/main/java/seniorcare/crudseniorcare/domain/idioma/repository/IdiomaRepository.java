package seniorcare.crudseniorcare.domain.idioma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.idioma.Idioma;

import java.util.UUID;

public interface IdiomaRepository extends JpaRepository<Idioma, UUID> {
}
