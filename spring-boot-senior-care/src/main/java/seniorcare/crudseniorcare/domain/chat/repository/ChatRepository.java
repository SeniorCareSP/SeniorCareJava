package seniorcare.crudseniorcare.domain.chat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.chat.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
