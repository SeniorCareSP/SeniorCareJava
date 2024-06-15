package seniorcare.crudseniorcare.domain.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByIdUsuario(Integer id);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<Usuario> findByEmailIgnoreCase(String email);

}
