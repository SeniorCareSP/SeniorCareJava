package seniorcare.crudseniorcare.service.usuario;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
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
    private final CuidadorRepository cuidadorRepository;
    private final CuidadorMapper cuidadorMapper;
    private final EnderecoService enderecoService;

    public UsuarioListagemCuidadorDto criar(UsuarioCriacaoCuidadorDto usuarioCriacaoCuidadorDto) {
        Cuidador novoUsuario = CuidadorMapper.toCuidador(usuarioCriacaoCuidadorDto);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        Cuidador cuidadorSalvo = cuidadorRepository.save(novoUsuario);

        return CuidadorMapper.toUsuarioListagemCuidadorDto(cuidadorSalvo);
    }



    public List<UsuarioListagemCuidadorDto> listarTodos() {
        List<Cuidador> cuidadores = cuidadorRepository.findAll();
        List<UsuarioListagemCuidadorDto> listaCuidadoresDto = new ArrayList<>();

        for (Cuidador cuidador : cuidadores) {
            listaCuidadoresDto.add(CuidadorMapper.toUsuarioListagemCuidadorDto(cuidador));
        }

        return listaCuidadoresDto;
    }

    public void deleteCuidadorById(UUID cuidadorId) {
        Optional<Cuidador> cuidadorOptional = cuidadorRepository.findById(cuidadorId);
        if (cuidadorOptional.isPresent()) {
            Cuidador cuidador = cuidadorOptional.get();
            cuidadorRepository.delete(cuidador);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuidador não encontrado com o ID: " + cuidadorId);
        }
    }


    public UsuarioListagemCuidadorDto updateCuidador(UUID cuidadorId, UsuarioCriacaoCuidadorDto usuarioAtualizado) {
        Optional<Cuidador> cuidadorOptional = cuidadorRepository.findById(cuidadorId);
        if (cuidadorOptional.isPresent()) {
            Cuidador cuidador = cuidadorOptional.get();
            // Atualiza os dados do cuidador com os novos dados
            cuidador.setNome(usuarioAtualizado.getNome());
            cuidador.setEmail(usuarioAtualizado.getEmail());
            cuidador.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
            // Outros campos que podem precisar de atualização

            // Salva e retorna o cuidador atualizado
            return CuidadorMapper.toUsuarioListagemCuidadorDto(cuidadorRepository.save(cuidador));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuidador não encontrado com o ID: " + cuidadorId);
        }
    }
}
