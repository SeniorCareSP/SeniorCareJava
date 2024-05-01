package seniorcare.crudseniorcare.repository.arquivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import seniorcare.crudseniorcare.domain.Arquivo;

import java.util.Optional;

public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {
    Optional<Arquivo> findByNomeArquivoOriginal(String nome);
}
