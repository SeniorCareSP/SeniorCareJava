package seniorcare.crudseniorcare.domain.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByNome(String nome);

    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail (String email);

    List<Usuario> findByDtNascimentoOrderByDtNascimentoDesc(LocalDate dtNascimento);
    boolean existsByCpf(String cpf);

}
