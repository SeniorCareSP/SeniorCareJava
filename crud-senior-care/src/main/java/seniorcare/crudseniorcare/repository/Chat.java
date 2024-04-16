package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Chat extends JpaRepository<Chat, UUID> {
}
