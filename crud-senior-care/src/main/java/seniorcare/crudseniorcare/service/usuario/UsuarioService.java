package seniorcare.crudseniorcare.service.usuario;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;

import seniorcare.crudseniorcare.api.configuration.security.jwt.GerenciadorTokenJwt;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioLoginDto;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.ResponsavelMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;

import seniorcare.crudseniorcare.service.usuario.dto.UsuarioMapper;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {


    public final PasswordEncoder passwordEncoder;
    private final ResponsavelRepository responsavelRepository;
    private final CuidadorRepository cuidadorRepository;

    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioListagemDto> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.addAll(responsavelRepository.findAll());
        usuarios.addAll(cuidadorRepository.findAll());
        return usuarios.stream()
                .map(usuarioMapper::toUsuarioListagemDto)
                .collect(Collectors.toList());
    }

    public boolean emailJaExiste(String email) {
        Optional<Responsavel> responsavelOptional = responsavelRepository.findByEmail(email);
        Optional<Cuidador> cuidadorOptional = cuidadorRepository.findByEmail(email);

        return responsavelOptional.isPresent() || cuidadorOptional.isPresent();
    }


    public List<UsuarioListagemDto> listarCuidadores() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.addAll(responsavelRepository.findAll());
        return usuarios.stream()
                .map(usuarioMapper::toUsuarioListagemDto)
                .collect(Collectors.toList());
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Optional<Cuidador> cuidadorOptional = cuidadorRepository.findByEmail(usuarioLoginDto.getEmail());
        Optional<Responsavel> responsavelOptional = responsavelRepository.findByEmail(usuarioLoginDto.getEmail());

        Usuario usuarioAutenticado = cuidadorOptional
                .map(cuidador -> (Usuario) cuidador)
                .orElseGet(() -> responsavelOptional
                        .map(responsavel -> (Usuario) responsavel)
                        .orElseThrow(() -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }
}