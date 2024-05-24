package seniorcare.crudseniorcare.domain.notificacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seniorcare.crudseniorcare.domain.notificacao.Notificacao;

import java.util.UUID;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, UUID> {
}