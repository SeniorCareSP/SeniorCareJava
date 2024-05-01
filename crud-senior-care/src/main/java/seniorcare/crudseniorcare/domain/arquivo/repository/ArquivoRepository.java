package seniorcare.crudseniorcare.domain.arquivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.arquivo.Arquivo;

import java.util.Optional;

public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {
    Optional<Arquivo> findByNomeArquivoOriginal(String nome);
}
