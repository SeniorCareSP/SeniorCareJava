package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seniorcare.crudseniorcare.model.Chat;

import java.util.UUID;
@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {
}
