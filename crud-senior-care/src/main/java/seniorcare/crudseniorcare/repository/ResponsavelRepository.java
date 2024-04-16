package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.usuario.Responsavel;

import java.util.UUID;

public interface ResponsavelRepository extends JpaRepository<Responsavel, UUID> {

}
