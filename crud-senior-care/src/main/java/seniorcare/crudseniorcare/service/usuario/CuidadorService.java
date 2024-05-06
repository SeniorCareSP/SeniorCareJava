package seniorcare.crudseniorcare.service.usuario;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;

import java.util.ArrayList;
import java.util.List;
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

}
