package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Caracteristica;

import java.util.UUID;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, UUID> {

}
