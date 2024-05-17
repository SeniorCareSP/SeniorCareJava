package seniorcare.crudseniorcare.service.usuario;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuidadorService {
    private final PasswordEncoder passwordEncoder;
    private final CuidadorRepository repository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioListagemCuidadorDto criar(UsuarioCriacaoCuidadorDto usuarioCriacaoCuidadorDto) {
        Cuidador novoUsuario = CuidadorMapper.toCuidador(usuarioCriacaoCuidadorDto);
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);
        Cuidador cuidadorSalvo = repository.save(novoUsuario);
        return CuidadorMapper.toUsuarioListagemCuidadorDto(cuidadorSalvo);
    }


    public boolean emailJaExiste(String email) {
        Optional<Usuario> emailUsuario = usuarioRepository.findByEmail(email);
        return emailUsuario.isPresent();
    }

    public List<Cuidador> list(){ return repository.findAll();}

    public Cuidador byId(UUID id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Cuidador")
        );
    }


    public void delete(UUID id){
        Optional<Cuidador> cuidador = repository.findById(id);
        if (cuidador.isEmpty()){
            throw new NaoEncontradoException("Cuidador");
        }
        repository.delete(cuidador.get());
    }

    public Cuidador update(UUID id, Cuidador cuidador){
        Optional<Cuidador> cuidadadorOpt = repository.findById(id);

        if (cuidadadorOpt.isEmpty()) {
            throw new NaoEncontradoException("Cuidador");
        }
        Cuidador cuidadorUpd = cuidadadorOpt.get();

        cuidadorUpd.setCpf(cuidador.getCpf());
        cuidadorUpd.setEmail(cuidador.getEmail());
        cuidadorUpd.setApresentacao(cuidador.getApresentacao());
        cuidadorUpd.setPrecoHora(cuidador.getPrecoHora());
        cuidadorUpd.setNome(cuidador.getNome());
        cuidadorUpd.setSenha(passwordEncoder.encode(cuidador.getSenha()));
        cuidadorUpd.setTelefone(cuidador.getTelefone());
        cuidadorUpd.setSexoBiologico(cuidador.getSexoBiologico());
        cuidadorUpd.setDtNascimento(cuidador.getDtNascimento());
        cuidadorUpd.setExperiencia(cuidadorUpd.getExperiencia());
        cuidadorUpd.setFaixaEtaria(cuidador.getFaixaEtaria());

        return cuidadorUpd;
    }

    public UsuarioListagemCuidadorDto updateCuidador(UUID cuidadorId, UsuarioCriacaoCuidadorDto cuidador) {
        Optional<Cuidador> cuidadorOptional = repository.findById(cuidadorId);
        if (cuidadorOptional.isEmpty()){
            throw new NaoEncontradoException("Cuidador");
        }

            Cuidador cuidadorUpd = cuidadorOptional.get();

            cuidadorUpd.setCpf(cuidador.getCpf());
            cuidadorUpd.setEmail(cuidador.getEmail());
            cuidadorUpd.setApresentacao(cuidador.getApresentacao());
            cuidadorUpd.setPrecoHora(cuidador.getPrecoHora());
            cuidadorUpd.setNome(cuidador.getNome());
            cuidadorUpd.setSenha(passwordEncoder.encode(cuidador.getSenha()));
            cuidadorUpd.setTelefone(cuidador.getTelefone());
            cuidadorUpd.setSexoBiologico(cuidador.getSexoBiologico());
            cuidadorUpd.setDtNascimento(cuidador.getDtNascimento());
            cuidadorUpd.setExperiencia(cuidadorUpd.getExperiencia());
            cuidadorUpd.setFaixaEtaria(cuidador.getFaixaEtaria());

            UsuarioListagemCuidadorDto dto = CuidadorMapper.toUsuarioListagemCuidadorDto(cuidadorUpd);
            return dto;
    }
}
