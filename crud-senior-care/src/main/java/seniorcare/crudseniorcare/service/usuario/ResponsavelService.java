package seniorcare.crudseniorcare.service.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;

import java.util.*;

@Service
@RequiredArgsConstructor

public class ResponsavelService {
    private final UsuarioRepository usuarioRepository;
    private final ResponsavelRepository repository;
    public final PasswordEncoder passwordEncoder;

    public Responsavel criar(Responsavel novoResponsavel) {

        String senhaCriptografada = passwordEncoder.encode(novoResponsavel.getSenha());
        novoResponsavel.setSenha(senhaCriptografada);
        if (emailJaExiste(novoResponsavel.getEmail())){
            throw new ConflitoException("Email Responsavel");
        }
        return repository.save(novoResponsavel);
    }


    public boolean emailJaExiste(String email) {
        Optional<Usuario> emailUsuario = usuarioRepository.findByEmail(email);
        return emailUsuario.isPresent();
    }

    public List<Responsavel> list(){ return repository.findAll();}

    public Responsavel byId(UUID id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Responsavel")
        );
    }


    public void delete(UUID id){
        Optional<Responsavel> responsavel = repository.findById(id);
        if (responsavel.isEmpty()){
            throw new NaoEncontradoException("Responsavel");
        }
        repository.delete(responsavel.get());
    }

    public Responsavel update(UUID id, Responsavel responsavel){
        Optional<Responsavel> responsavelOpt = repository.findById(id);

        if (responsavelOpt.isEmpty()) {
            throw new NaoEncontradoException("Responsavel");
        }
        Responsavel responsavelUpd = responsavelOpt.get();

        responsavelUpd.setCpf(responsavel.getCpf());
        responsavelUpd.setEmail(responsavelUpd.getEmail());
        responsavelUpd.setApresentacao(responsavel.getApresentacao());
        responsavelUpd.setPrecoHora(responsavel.getPrecoHora());
        responsavelUpd.setNome(responsavel.getNome());
        responsavelUpd.setSenha(passwordEncoder.encode(responsavel.getSenha()));
        responsavelUpd.setTelefone(responsavel.getTelefone());
        responsavelUpd.setSexoBiologico(responsavel.getSexoBiologico());
        responsavelUpd.setDtNascimento(responsavel.getDtNascimento());

        return responsavelUpd;
    }

}
