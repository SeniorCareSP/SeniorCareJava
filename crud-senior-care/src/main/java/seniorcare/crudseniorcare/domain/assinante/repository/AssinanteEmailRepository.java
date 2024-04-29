package seniorcare.crudseniorcare.domain.assinante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import seniorcare.crudseniorcare.domain.assinante.AssinanteEmail;

import java.util.List;
import java.util.UUID;

public interface AssinanteEmailRepository extends JpaRepository<AssinanteEmail, UUID> {
    @Query("SELECT ae FROM AssinanteEmail ae JOIN FETCH ae.newsletter n WHERE n.id = :newsletterId")
    List<AssinanteEmail> findAssinantesByNewsletterId(@Param("newsletterId") UUID newsletterId);
}
