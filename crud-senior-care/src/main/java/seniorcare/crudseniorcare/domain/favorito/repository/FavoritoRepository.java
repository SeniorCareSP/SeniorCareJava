package seniorcare.crudseniorcare.domain.favorito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seniorcare.crudseniorcare.domain.favorito.Favorito;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByResponsavelIdUsuario(Integer idUsuario);
//    Optional<Favorito> findByUsuarioIdAndIdUsuario(Integer idUsuario, Integer id);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Favorito f WHERE f.responsavel.idUsuario = :responsavelId AND f.cuidador.idUsuario = :cuidadorId")
    boolean existsByResponsavelIdAndCuidadorId(@Param("responsavelId") Integer responsavelId, @Param("cuidadorId") Integer cuidadorId);

    @Query("SELECT f FROM Favorito f WHERE f.responsavel.id = :usuarioId AND f.cuidador.id = :usuarioFavoritadoId")
    Optional<Favorito> findByUsuarioIdAndUsuarioFavoritadoId(Integer usuarioId, Integer usuarioFavoritadoId);
}
