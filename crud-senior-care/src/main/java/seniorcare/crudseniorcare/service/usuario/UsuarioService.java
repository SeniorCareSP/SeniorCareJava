package seniorcare.crudseniorcare.service.usuario;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import seniorcare.crudseniorcare.configuration.security.jwt.GerenciadorTokenJwt;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.AdministradorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioLoginDto;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;

import seniorcare.crudseniorcare.service.usuario.dto.UsuarioMapper;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final CuidadorRepository cuidadorRepository;
    private final ResponsavelRepository responsavelRepository;
    private final AdministradorRepository administradorRepository;
    private final UsuarioRepository usuarioRepository;

    public final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuarioLoginDto.getEmail());


        if (usuarioOptional.isEmpty()){
            throw new NaoEncontradoException("Usuario email AUTENTICAR");
        }

        Usuario usuarioAutenticado = usuarioOptional.get();

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }


    public List<Usuario> list(){
           return usuarioRepository.findAll();
    }

    public Usuario byId(UUID id){
        return usuarioRepository.findByIdUsuario(id).orElseThrow(
                () -> new NaoEncontradoException("Usuario")
        );
    }

    public List<UsuarioListagemDto> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.addAll(responsavelRepository.findAll());
        usuarios.addAll(cuidadorRepository.findAll());
        return usuarios.stream()
                .map(UsuarioMapper::toUsuarioListagemDto)
                .collect(Collectors.toList());
    }

    public void delete(UUID id){
        Optional<Usuario> usuario = usuarioRepository.findByIdUsuario(id);
        if (usuario.isEmpty()){
            throw new NaoEncontradoException("Usuario");
        }
        usuarioRepository.delete(usuario.get());
    }

}

