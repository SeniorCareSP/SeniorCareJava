package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seniorcare.crudseniorcare.model.Mensagem;

import java.util.UUID;
@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {
//Como que muda a porta? Mais fácil a gente usar a 80 em vez da 8080 kkk
}
