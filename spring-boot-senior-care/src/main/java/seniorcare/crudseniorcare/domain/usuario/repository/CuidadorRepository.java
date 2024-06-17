package seniorcare.crudseniorcare.domain.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CuidadorRepository extends JpaRepository<Cuidador, Integer> {
    @Query("SELECT c.sexoBiologico, COUNT(c) FROM Cuidador c GROUP BY c.sexoBiologico")
    List<Object[]> countByGeneroGroupByGenero();
}
