package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Ajuda;

import java.util.UUID;

public interface AjudaRepository extends JpaRepository<Ajuda, UUID> {
    boolean existsByNome(String nome);
}
