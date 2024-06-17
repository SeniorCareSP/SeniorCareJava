package seniorcare.crudseniorcare.domain.ajuda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;

import java.util.List;
import java.util.UUID;

public interface AjudaRepository extends JpaRepository<Ajuda, Integer> {
    boolean existsByNome(String nome);

    @Query("SELECT a.nome, COUNT(a) FROM Ajuda a GROUP BY a.nome")
    List<Object[]> countByNomeGroupByNome();
}
