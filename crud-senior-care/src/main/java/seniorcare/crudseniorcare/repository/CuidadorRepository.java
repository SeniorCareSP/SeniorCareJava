package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Cuidador;

import java.util.List;
import java.util.UUID;

public interface CuidadorRepository extends JpaRepository<Cuidador, UUID> {

}
