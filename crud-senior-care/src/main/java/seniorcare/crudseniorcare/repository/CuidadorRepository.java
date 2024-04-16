package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.usuario.Cuidador;

import java.util.UUID;

public interface CuidadorRepository extends JpaRepository<Cuidador, UUID> {

}
