package seniorcare.crudseniorcare.domain.favorito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seniorcare.crudseniorcare.domain.favorito.Favorito;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByUsuarioIdUsuario(Integer idUsuario);
//    Optional<Favorito> findByUsuarioIdAndIdUsuario(Integer idUsuario, Integer id);
}
