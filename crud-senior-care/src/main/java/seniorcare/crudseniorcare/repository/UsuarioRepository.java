package seniorcare.crudseniorcare.repository;

import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Cuidador;
import seniorcare.crudseniorcare.model.Usuario;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByNome(String nome);

    boolean existsByEmail(String email);

    List<Usuario> findByDtNascimentoOrderByDtNascimentoDesc(LocalDate dtNascimento);
    boolean existsByCpf(String cpf);

}
