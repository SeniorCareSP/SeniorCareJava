package seniorcare.crudseniorcare.domain.ajuda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;

import java.util.List;
import java.util.UUID;

public interface AjudaRepository extends JpaRepository<Ajuda, Integer> {
    boolean existsByNome(String nome);

    @Query("SELECT a.nome, COUNT(a) FROM Ajuda a GROUP BY a.nome")
    List<Object[]> countByNomeGroupByNome();

    @Query("SELECT a FROM Ajuda a WHERE a.cuidador = :cuidador")
    List<Ajuda> findByCuidador(@Param("cuidador") Cuidador cuidador);
}
