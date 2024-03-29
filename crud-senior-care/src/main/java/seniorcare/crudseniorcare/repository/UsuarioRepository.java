package seniorcare.crudseniorcare.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Cuidador;
import seniorcare.crudseniorcare.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByNome(String nome);

    boolean existsByEmail(String email);

    List<Usuario> findByDtNascimentoOrderByDtNascimentoDesc();
    Optional<Usuario> findByFirstByFaixaEtaria();
    Optional<Usuario> findByIdAgenda(int idAgenda);

    boolean existByIndice(int indice);

    List<Cuidador> cuidadores = findBy
}
