package seniorcare.crudseniorcare.domain.favorito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seniorcare.crudseniorcare.domain.favorito.Favorito;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
}
