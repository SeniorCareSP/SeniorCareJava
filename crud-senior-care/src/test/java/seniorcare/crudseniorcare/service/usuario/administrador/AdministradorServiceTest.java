package seniorcare.crudseniorcare.service.usuario.administrador;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import seniorcare.crudseniorcare.domain.usuario.Administrador;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.AdministradorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.usuario.AdministradorService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdministradorServiceTest {

    @Mock
    private AdministradorRepository repository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdministradorService service;

    @Test
    @DisplayName("Criar administrador corretamente")
    void criarAdministradorCorreto() {
        // GIVEN
        Administrador administrador = new Administrador();
        administrador.setEmail("admin@example.com");
        administrador.setSenha("senha123");

        when(passwordEncoder.encode(administrador.getSenha())).thenReturn("senhaCriptografada");
        when(usuarioRepository.findByEmail(administrador.getEmail())).thenReturn(Optional.empty());
        when(repository.save(administrador)).thenReturn(administrador);

        // WHEN
        Administrador resposta = service.criar(administrador);

        // THEN
        assertEquals(administrador, resposta);
        verify(repository, times(1)).save(administrador);
    }

    @Test
    @DisplayName("Listar administradores corretamente")
    void listarAdministradores() {
        // GIVEN
        List<Administrador> administradores = List.of(new Administrador(), new Administrador());
        when(repository.findAll()).thenReturn(administradores);

        // WHEN
        List<Administrador> resposta = service.list();

        // THEN
        assertEquals(administradores.size(), resposta.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Buscar administrador por ID")
    void buscarAdministradorPorId() {
        // GIVEN
        Administrador administrador = new Administrador();
        administrador.setIdUsuario(1);
        when(repository.findById(1)).thenReturn(Optional.of(administrador));

        // WHEN
        Administrador resposta = service.byId(1);

        // THEN
        assertEquals(administrador, resposta);
        verify(repository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Atualizar administrador corretamente")
    void atualizarAdministrador() {
        // GIVEN
        Administrador administrador = new Administrador();
        administrador.setIdUsuario(1);
        administrador.setSenha("novaSenha");

        String senhaCriptografada = "senhaCriptografada";

        Administrador administradorAtualizado = new Administrador();
        administradorAtualizado.setIdUsuario(1);
        administradorAtualizado.setSenha(senhaCriptografada);

        when(repository.findById(1)).thenReturn(Optional.of(administrador));
        when(passwordEncoder.encode(administrador.getSenha())).thenReturn(senhaCriptografada);
        when(repository.save(any(Administrador.class))).thenReturn(administradorAtualizado);

        // WHEN
        Administrador resposta = service.update(1, administrador);

        // THEN
        assertEquals(senhaCriptografada, resposta.getSenha());
        verify(repository, times(1)).save(any(Administrador.class));
    }


    @Test
    @DisplayName("Deletar administrador corretamente")
    void deletarAdministrador() {
        // GIVEN
        Administrador administrador = new Administrador();
        administrador.setIdUsuario(1);
        when(repository.findById(1)).thenReturn(Optional.of(administrador));

        // WHEN
        service.delete(1);

        // THEN
        verify(repository, times(1)).delete(administrador);
    }

    @Test
    @DisplayName("Criar administrador com email já existente")
    void criarAdministradorEmailExistente() {
        // GIVEN
        Administrador administrador = new Administrador();
        administrador.setEmail("admin@example.com");
        administrador.setSenha("senha123");

        when(usuarioRepository.findByEmail(administrador.getEmail())).thenReturn(Optional.of(new Administrador()));

        // WHEN/THEN
        assertThrows(ConflitoException.class, () -> service.criar(administrador));
    }


    @Test
    @DisplayName("Excluir administrador")
    void excluirAdministrador() {
        Administrador administrador = new Administrador("Cargo 1");
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.of(administrador));

        service.delete(id);

        verify(repository, times(1)).delete(administrador);
    }

    @Test
    @DisplayName("Excluir administrador não encontrado")
    void excluirAdministradorNaoEncontrado() {
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> service.delete(id));
    }


    @Test
    @DisplayName("Atualizar administrador não encontrado")
    void atualizarAdministradorNaoEncontrado() {
        Administrador atualizado = new Administrador("Cargo 2");
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> service.update(id, atualizado));
    }
}