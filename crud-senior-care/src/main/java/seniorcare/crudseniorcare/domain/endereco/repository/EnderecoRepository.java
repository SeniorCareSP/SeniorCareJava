package seniorcare.crudseniorcare.domain.endereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.endereco.Endereco;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
}
