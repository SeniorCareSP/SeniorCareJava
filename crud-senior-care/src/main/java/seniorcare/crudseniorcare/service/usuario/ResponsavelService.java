package seniorcare.crudseniorcare.service.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.agenda.AgendaService;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.idioma.IdiomaService;
import seniorcare.crudseniorcare.service.idoso.IdosoService;

import java.util.*;

@Service
@RequiredArgsConstructor

public class ResponsavelService {
    private final UsuarioRepository usuarioRepository;
    private final ResponsavelRepository repository;
    public final PasswordEncoder passwordEncoder;

    private final EnderecoService enderecoService;
    private final AgendaService agendaService;
    private final IdiomaService idiomaService;
    private final IdosoService idosoService;


    public Responsavel criar(Responsavel novoResponsavel) {

        String senhaCriptografada = passwordEncoder.encode(novoResponsavel.getSenha());
        novoResponsavel.setSenha(senhaCriptografada);
        if (emailJaExiste(novoResponsavel.getEmail())){
            throw new ConflitoException("Email Responsavel");
        }
        Responsavel usuarioSalvo = repository.save(novoResponsavel);

        usuarioSalvo.getEndereco().setUsuario(usuarioSalvo);
        enderecoService.create(usuarioSalvo.getEndereco());

        usuarioSalvo.getAgenda().setUsuario(usuarioSalvo);
        agendaService.create(usuarioSalvo.getAgenda());

        for (Idioma idioma : usuarioSalvo.getIdiomas()){
            idioma.setUsuario(usuarioSalvo);
            idiomaService.create(idioma);
        }
        for (Idoso idoso : usuarioSalvo.getIdosos()){
            idoso.setResponsavel(usuarioSalvo);
            idosoService.create(idoso);
        }

        return usuarioSalvo;

    }

    public boolean emailJaExiste(String email) {
        Optional<Usuario> emailUsuario = usuarioRepository.findByEmail(email);
        return emailUsuario.isPresent();
    }

    public List<Responsavel> list(){ return repository.findAll();}

    public Responsavel byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Responsavel")
        );
    }


    public void delete(Integer id){
        Optional<Responsavel> responsavel = repository.findById(id);
        if (responsavel.isEmpty()){
            throw new NaoEncontradoException("Responsavel");
        }
        repository.delete(responsavel.get());
    }

    public Responsavel update(Integer id, Responsavel responsavel){
        Optional<Responsavel> responsavelOpt = repository.findById(id);

        if (responsavelOpt.isEmpty()) {
            throw new NaoEncontradoException("Responsavel");
        }
        Responsavel responsavelUpd = responsavelOpt.get();

        responsavelUpd.setCpf(responsavel.getCpf());
        responsavelUpd.setEmail(responsavel.getEmail());
        responsavelUpd.setApresentacao(responsavel.getApresentacao());
        responsavelUpd.setNome(responsavel.getNome());
        responsavelUpd.setSenha(passwordEncoder.encode(responsavel.getSenha()));
        responsavelUpd.setTelefone(responsavel.getTelefone());
        responsavelUpd.setSexoBiologico(responsavel.getSexoBiologico());
        responsavelUpd.setDtNascimento(responsavel.getDtNascimento());

        repository.save(responsavelUpd);
        //return repository.save(responsavelUpd);


        return responsavelUpd;
    }

}
