package seniorcare.crudseniorcare.domain.caracteristica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;

import java.util.List;
import java.util.UUID;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer> {
    @Query("SELECT c.nome, COUNT(c) FROM Caracteristica c GROUP BY c.nome")
    List<Object[]> countByNomeGroupByNome();
}
