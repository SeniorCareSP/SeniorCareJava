package seniorcare.crudseniorcare.service.usuario;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import seniorcare.crudseniorcare.configuration.security.jwt.GerenciadorTokenJwt;
import seniorcare.crudseniorcare.domain.usuario.Administrador;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.AdministradorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioLoginDto;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;

import seniorcare.crudseniorcare.service.usuario.dto.UsuarioMapper;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;
import seniorcare.crudseniorcare.utils.PilhaObj;

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

    private final PilhaObj<Cuidador> pilhaCuidador = new PilhaObj<>(10);
    private final PilhaObj<Responsavel> pilhaResponsavel = new PilhaObj<>(10);
    private final PilhaObj<Administrador> pilhaAdministrador = new PilhaObj<>(10);
  
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

    public Usuario byId(Integer id){
        return usuarioRepository.findByIdUsuario(id).orElseThrow(
                () -> new NaoEncontradoException("Usuario")
        );
    }

    public Optional<Usuario> findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public List<UsuarioListagemDto> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.addAll(responsavelRepository.findAll());
        usuarios.addAll(administradorRepository.findAll());
        usuarios.addAll(cuidadorRepository.findAll());
        return usuarios.stream()
                .map(UsuarioMapper::toUsuarioListagemDto)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        Usuario usuario = usuarioRepository.findByIdUsuario(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuario"));

        if (usuario instanceof Cuidador) {
            pilhaCuidador.push((Cuidador) usuario);
            cuidadorRepository.delete((Cuidador) usuario);
        } else if (usuario instanceof Responsavel) {
            pilhaResponsavel.push((Responsavel) usuario);
            responsavelRepository.delete((Responsavel) usuario);
        } else if (usuario instanceof Administrador) {
            pilhaAdministrador.push((Administrador) usuario);
            administradorRepository.delete((Administrador) usuario);
        } else {
            usuarioRepository.delete(usuario);
        }
    }

    public Cuidador desfazerExclusaoCuidador() {
        if (!pilhaCuidador.isEmpty()) {
            Cuidador cuidador = pilhaCuidador.pop();
            cuidadorRepository.save(cuidador);
            return cuidador;
        } else {
            throw new IllegalStateException("Não há cuidadores excluídos para desfazer.");
        }
    }

    public Responsavel desfazerExclusaoResponsavel() {
        if (!pilhaResponsavel.isEmpty()) {
            Responsavel responsavel = pilhaResponsavel.pop();
            responsavelRepository.save(responsavel);
            return responsavel;
        } else {
            throw new IllegalStateException("Não há responsáveis excluídos para desfazer.");
        }
    }

    public Administrador desfazerExclusaoAdministrador() {
        if (!pilhaAdministrador.isEmpty()) {
            Administrador administrador = pilhaAdministrador.pop();
            administradorRepository.save(administrador);
            return administrador;
        } else {
            throw new IllegalStateException("Não há administradores excluídos para desfazer.");
        }
    }

    public Usuario update(Integer id, Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            throw new NaoEncontradoException("Usuário não encontrado");
        }

        Usuario usuarioExistente = usuarioOptional.get();

        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuarioExistente.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        // Verifica se a lista de favoritos não é nula antes de atribuí-la
        if (usuario.getFavoritos() != null) {
            // Atualiza a lista de favoritos do usuário existente com a lista fornecida
            usuarioExistente.getFavoritos().addAll(usuario.getFavoritos());
        }

        return usuarioRepository.save(usuarioExistente);
    }


}

