package seniorcare.crudseniorcare.service.usuario;


import lombok.RequiredArgsConstructor;
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
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {


    public final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioListagemDto> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(usuarioMapper::toUsuarioListagemDto)
                .collect(Collectors.toList());
    }

//    public List<UsuarioListagemCuidadorDto> listarCuidador() {
//            List<Usuario> usuarios = usuarioRepository.findAll();
//        List<Usuario>  aux = null;
//        for (Usuario user : usuarios) {
//            if (user instanceof Cuidador){
//                aux.add(user);
//            }
//        }
//        return null;
//    }

    public UsuarioListagemCuidadorDto criarCuidador(UsuarioCriacaoCuidadorDto usuarioCriacaoCuidadorDto){
        final Cuidador novoUsuario = CuidadorMapper.toCuidador(usuarioCriacaoCuidadorDto);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        return CuidadorMapper.toUsuarioListagemCuidadorDto( this.usuarioRepository.save(novoUsuario));
    }

    public UsuarioListagemResponsavelDto criarResponsavel(UsuarioCriacaoResponsavelDto usuarioCriacaoResponsavelDto){
        final Responsavel novoUsuario = ResponsavelMapper.toResponsavel(usuarioCriacaoResponsavelDto);


        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);
        return ResponsavelMapper.toUsuarioListagemResponsavelDto(this.usuarioRepository.save(novoUsuario));

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