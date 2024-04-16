package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Mensagem extends JpaRepository<Mensagem, UUID> {


}
