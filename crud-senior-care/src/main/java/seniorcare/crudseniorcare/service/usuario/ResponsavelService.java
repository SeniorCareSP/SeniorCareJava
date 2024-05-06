package seniorcare.crudseniorcare.service.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.ResponsavelMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;
    public final PasswordEncoder passwordEncoder;

    public UsuarioListagemResponsavelDto criar(UsuarioCriacaoResponsavelDto usuarioCriacaoResponsavelDto){
        final Responsavel novoUsuario = ResponsavelMapper.toResponsavel(usuarioCriacaoResponsavelDto);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        return ResponsavelMapper.toUsuarioListagemResponsavelDto( this.responsavelRepository.save(novoUsuario));
    }

    public List<UsuarioListagemResponsavelDto> listarTodos() {
        List<Responsavel> responsaveis = responsavelRepository.findAll();
        List<UsuarioListagemResponsavelDto> listagemResponsavelDtos = new ArrayList<>();

        for (Responsavel responsavel : responsaveis) {
            listagemResponsavelDtos.add(ResponsavelMapper.toUsuarioListagemResponsavelDto(responsavel));
        }

        return listagemResponsavelDtos;
    }

    public void deleteResponsavelById(UUID responsavelId) {
        Optional<Responsavel> responsavelOptional = responsavelRepository.findById(responsavelId);
        if (responsavelOptional.isPresent()) {
            Responsavel responsavel = responsavelOptional.get();
            responsavelRepository.delete(responsavel);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsável não encontrado com o ID: " + responsavelId);
        }
    }

    public UsuarioListagemResponsavelDto updateResponsavel(UUID responsavelId, UsuarioCriacaoResponsavelDto usuarioAtualizado) {
        Optional<Responsavel> responsavelOptional = responsavelRepository.findById(responsavelId);
        if (responsavelOptional.isPresent()) {
            Responsavel responsavel = responsavelOptional.get();
            // Atualiza os dados do responsável com os novos dados
            responsavel.setNome(usuarioAtualizado.getNome());
            responsavel.setEmail(usuarioAtualizado.getEmail());
            responsavel.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
            // Outros campos que podem precisar de atualização

            // Salva e retorna o responsável atualizado
            return ResponsavelMapper.toUsuarioListagemResponsavelDto(responsavelRepository.save(responsavel));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsável não encontrado com o ID: " + responsavelId);
        }
    }

}
