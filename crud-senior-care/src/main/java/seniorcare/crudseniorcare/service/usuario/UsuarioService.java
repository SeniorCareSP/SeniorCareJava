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
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioLoginDto;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;
import seniorcare.crudseniorcare.service.usuario.dto.ResponsavelMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;

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

    public void criar(UsuarioCriacaoCuidadorDto usuarioCriacaoCuidadorDto){
        final Usuario novoUsuario = CuidadorMapper.toCuidador(usuarioCriacaoCuidadorDto);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);
    }

    public void criar(UsuarioCriacaoResponsavelDto usuarioCriacaoResponsavelDto){
        final Usuario novoUsuario = ResponsavelMapper.toResponsavel(usuarioCriacaoResponsavelDto);
        this.usuarioRepository.save(novoUsuario);
    }

//    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto){
//        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
//                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());
//
//        final Authentication authentication = this.authenticationManager.authenticate(credentials);
//
//        Usuario usuarioAutenticado =
//                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
//                        .orElseThrow(
//                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
//                        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        final String token = gerenciadorTokenJwt.generateToken(authentication);
//
//        return ResponsavelMapper.of(usuarioAutenticado, token);
//    }
}
