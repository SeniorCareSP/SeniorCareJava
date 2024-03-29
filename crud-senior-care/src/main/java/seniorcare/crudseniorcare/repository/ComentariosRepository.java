package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Comentario;

import java.util.UUID;

public interface ComentariosRepository extends JpaRepository<Comentario, UUID> {
}
