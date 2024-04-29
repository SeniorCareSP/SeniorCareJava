package seniorcare.crudseniorcare.domain.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;

import java.util.UUID;

public interface ResponsavelRepository extends JpaRepository<Responsavel, UUID> {

}
