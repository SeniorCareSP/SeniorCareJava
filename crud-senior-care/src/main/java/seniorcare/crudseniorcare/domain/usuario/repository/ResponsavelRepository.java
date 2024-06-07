package seniorcare.crudseniorcare.domain.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
    @Query("SELECT r FROM Responsavel r LEFT JOIN FETCH r.favoritos")
    List<Responsavel> findAllWithFavoritos();
}
