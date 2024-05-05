package seniorcare.crudseniorcare.service.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;

import seniorcare.crudseniorcare.api.configuration.security.jwt.GerenciadorTokenJwt;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioLoginDto;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioListagemDto;

import seniorcare.crudseniorcare.service.usuario.dto.UsuarioMapper;
import seniorcare.crudseniorcare.utils.ListaObj;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;

    public List<UsuarioListagemDto> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(UsuarioMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void criar(UsuarioCriacaoCuidadorDto usuarioCriacaoCuidadorDto){
        final Usuario novoUsuario = UsuarioMapper.INSTANCE.toEntityCuidador(usuarioCriacaoCuidadorDto);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);
    }

    public void criar(UsuarioCriacaoResponsavelDto usuarioCriacaoResponsavelDto){
        final Usuario novoUsuario = UsuarioMapper.INSTANCE.toEntityResponsavel(usuarioCriacaoResponsavelDto);
        this.usuarioRepository.save(novoUsuario);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }
}
