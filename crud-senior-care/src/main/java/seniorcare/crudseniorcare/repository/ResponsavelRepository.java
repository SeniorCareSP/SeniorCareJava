package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Responsavel;

import java.util.List;
import java.util.UUID;

public interface ResponsavelRepository extends JpaRepository<Responsavel, UUID> {

}
