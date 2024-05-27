package seniorcare.crudseniorcare.service.usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.usuario.Administrador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.AdministradorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdministradorService {
    private final UsuarioRepository usuarioRepository;
    private final AdministradorRepository repository;
    public final PasswordEncoder passwordEncoder;

    public Administrador criar(Administrador novoAdministrador) {

        String senhaCriptografada = passwordEncoder.encode(novoAdministrador.getSenha());
        novoAdministrador.setSenha(senhaCriptografada);

        if (emailJaExiste(novoAdministrador.getEmail())){
            throw new ConflitoException("Email Administrador");
        }

        return repository.save(novoAdministrador);
    }

    public boolean emailJaExiste(String email) {
        Optional<Usuario> emailUsuario = usuarioRepository.findByEmail(email);
        return emailUsuario.isPresent();
    }

    public List<Administrador> list(){ return repository.findAll();}

    public Administrador byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Administrador")
        );
    }


    public void delete(Integer id){
        Optional<Administrador> administrador = repository.findById(id);
        if (administrador.isEmpty()){
            throw new NaoEncontradoException("Administrador");
        }
        repository.delete(administrador.get());
    }

    public Administrador update(Integer id, Administrador administrador){
        Optional<Administrador> administradorOptional = repository.findById(id);

        if (administradorOptional.isEmpty()) {
            throw new NaoEncontradoException("Administrador");
        }
        Administrador administradorUpd = administradorOptional.get();

        administradorUpd.setCpf(administrador.getCpf());
        administradorUpd.setEmail(administrador.getEmail());
        administradorUpd.setApresentacao(administrador.getApresentacao());
        administradorUpd.setNome(administrador.getNome());
        administradorUpd.setSenha(passwordEncoder.encode(administrador.getSenha()));
        administradorUpd.setTelefone(administrador.getTelefone());
        administradorUpd.setSexoBiologico(administrador.getSexoBiologico());
        administradorUpd.setDtNascimento(administrador.getDtNascimento());
        administradorUpd.setCargo(administrador.getCargo());


        return administradorUpd;
    }


}
