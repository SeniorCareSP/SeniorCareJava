package seniorcare.crudseniorcare.domain.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;

import java.util.UUID;

public interface CuidadorRepository extends JpaRepository<Cuidador, UUID> {

}
