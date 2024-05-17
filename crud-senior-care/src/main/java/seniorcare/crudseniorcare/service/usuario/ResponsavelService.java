package seniorcare.crudseniorcare.service.usuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.TipoUsuario;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.ResponsavelMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;

import java.time.LocalDate;
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
