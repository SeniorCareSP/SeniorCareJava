package seniorcare.crudseniorcare.domain.caracteristica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;

import java.util.UUID;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, UUID> {

}
