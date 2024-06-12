package seniorcare.crudseniorcare.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.chat.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
}
