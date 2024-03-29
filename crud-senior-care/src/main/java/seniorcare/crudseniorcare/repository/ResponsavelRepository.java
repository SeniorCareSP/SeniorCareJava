package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Responsavel;

import java.util.List;
import java.util.UUID;

public interface ResponsavelRepository extends JpaRepository<Responsavel, UUID> {
        List<Responsavel> findByIdByIdoso (UUID id); // a intenção aqui é achar a lista de idosos responsaveis pelo ID do responsavel.
}
