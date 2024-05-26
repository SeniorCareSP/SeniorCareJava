package seniorcare.crudseniorcare.domain.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {

}
