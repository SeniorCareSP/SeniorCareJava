package seniorcare.crudseniorcare.domain.ajuda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;

import java.util.UUID;

public interface AjudaRepository extends JpaRepository<Ajuda, Integer> {
    boolean existsByNome(String nome);
}
