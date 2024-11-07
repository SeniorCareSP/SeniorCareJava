package seniorcare.crudseniorcare.service.usuario;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.TipoUsuario;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.agenda.AgendaService;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaMapper;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;
import seniorcare.crudseniorcare.service.geolocalizacao.CoordenadaService;
import seniorcare.crudseniorcare.service.idioma.IdiomaService;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaMapper;
import seniorcare.crudseniorcare.service.idoso.IdosoResponsavelService;
import seniorcare.crudseniorcare.service.idoso.IdosoService;
import seniorcare.crudseniorcare.service.usuario.dto.ResponsavelMapper;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.ResponsavelAtualizacaoDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;

import java.io.IOException;
import java.time.LocalDate;
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
    private final IdosoResponsavelService idosoService;
    private final CoordenadaService coordenadaService;

    @Transactional
    public Responsavel criar(Responsavel novoResponsavel) throws IOException {

        String senhaCriptografada = passwordEncoder.encode(novoResponsavel.getSenha());
        novoResponsavel.setSenha(senhaCriptografada);

        if (emailJaExiste(novoResponsavel.getEmail())){
            throw new ConflitoException("Email Responsavel");
        }
        Endereco endereco = (novoResponsavel.getEndereco());
        Endereco enderecoCompleto = coordenadaService.pegarPosicoes(endereco);
        Responsavel usuarioSalvo = repository.save(novoResponsavel);


        enderecoCompleto.setUsuario(usuarioSalvo);
        enderecoService.create(enderecoCompleto);

        Agenda agenda = usuarioSalvo.getAgenda();
        agenda.setUsuario(usuarioSalvo);
        agendaService.create(agenda);

        for (Idioma idioma : usuarioSalvo.getIdiomas()){
            idioma.setUsuario(usuarioSalvo);
            idiomaService.create(idioma);
        }

        return usuarioSalvo;

    }




    public boolean emailJaExiste(String email) {
        Optional<Usuario> emailUsuario = usuarioRepository.findByEmailIgnoreCase(email);
        return emailUsuario.isPresent();
    }

    public List<Responsavel> list(){ return repository.findAll();}

    public Optional<Responsavel> findById(Integer idUsuario) {
        return repository.findById(idUsuario);
    }

    public Responsavel byId(Integer id) {

            Responsavel responsavel = repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Responsavel")
        );
        List<Idoso> idosos = idosoService.buscarPorResponsavel(responsavel);

        responsavel.setIdosos(idosos);

        return responsavel;
    }


    public void delete(Integer id){
        Optional<Responsavel> responsavel = repository.findById(id);
        if (responsavel.isEmpty()){
            throw new NaoEncontradoException("Responsavel");
        }
        repository.delete(responsavel.get());
    }

    public Responsavel update(Integer id, ResponsavelAtualizacaoDto responsavelDto) throws IOException {
        Optional<Responsavel> responsavelOpt = repository.findById(id);
        System.out.println(responsavelDto);
        if (responsavelOpt.isEmpty()) {
            throw new NaoEncontradoException("Responsavel");
        }

        Responsavel responsavel = responsavelOpt.get();

        if (responsavelDto.getNome() != null) {
            responsavel.setNome(responsavelDto.getNome());
        }
        if (responsavelDto.getEmail() != null) {
            responsavel.setEmail(responsavelDto.getEmail());
        }
        if (responsavelDto.getTelefone() != null) {
            responsavel.setTelefone(responsavelDto.getTelefone());
        }
        if (responsavelDto.getApresentacao() != null) {
            responsavel.setApresentacao(responsavelDto.getApresentacao());
        }
        if (responsavelDto.getStatus() != null) {
            responsavel.setStatus(responsavelDto.getStatus());
        }
        if (responsavelDto.getAgendas() != null) {
            Agenda novaAgenda = AgendaMapper.toEntity(responsavelDto.getAgendas());
            novaAgenda.setUsuario(responsavel);
            if (responsavel.getAgenda() != null && !novaAgenda.equals(responsavel.getAgenda())) {
                agendaService.update(responsavel.getAgenda().getIdAgenda(), novaAgenda);
            } else {
                responsavel.setAgenda(novaAgenda);
            }
        }
        if (responsavelDto.getIdiomas() != null) {
            responsavel.setIdiomas(IdiomaMapper.toListagemIdioma(responsavelDto.getIdiomas()));
        }
        if (responsavelDto.getEndereco() != null) {
            Endereco novoEndereco = EnderecoMapper.toEndereco(responsavelDto.getEndereco());
            novoEndereco.setUsuario(responsavel);
            Endereco endereco = coordenadaService.pegarPosicoes(novoEndereco);
            novoEndereco.setLatidude(endereco.getLatidude());
            novoEndereco.setLongitude(endereco.getLongitude());
            if (responsavel.getEndereco() != null && !novoEndereco.equals(responsavel.getEndereco())) {
                enderecoService.update(responsavel.getEndereco().getIdEndereco(), novoEndereco);
            } else {
                responsavel.setEndereco(novoEndereco);
            }
        }

        return repository.save(responsavel);
    }


    public Responsavel updateToIdoso(Integer id, Responsavel responsavel){
        Optional<Responsavel> responsavelOpt = repository.findById(id);

        if (responsavelOpt.isEmpty()) {
            throw new NaoEncontradoException("Responsavel");
        }

        responsavel.setIdUsuario(id);
        return repository.save(responsavel);
    }
}
