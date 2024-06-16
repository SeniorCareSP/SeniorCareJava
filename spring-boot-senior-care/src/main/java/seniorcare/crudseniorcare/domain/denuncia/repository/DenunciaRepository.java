package seniorcare.crudseniorcare.domain.denuncia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import seniorcare.crudseniorcare.domain.denuncia.Denuncia;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {

    @Query("SELECT COUNT(d) FROM Denuncia d WHERE d.status = false")
    Integer countByStatusFalse();
}
