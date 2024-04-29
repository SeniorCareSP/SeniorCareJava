package seniorcare.crudseniorcare.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seniorcare.crudseniorcare.domain.chat.Chat;

import java.util.UUID;
@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {
}
