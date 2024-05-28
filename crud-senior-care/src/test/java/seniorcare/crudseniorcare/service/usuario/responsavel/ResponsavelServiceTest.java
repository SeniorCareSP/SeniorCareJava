package seniorcare.crudseniorcare.service.usuario.responsavel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.TipoUsuario;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResponsavelServiceTest {

    @Mock
    private ResponsavelRepository repository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private ResponsavelService service;

    @Test
    @DisplayName("Listar todos os responsáveis")
    void listarResponsaveis() {
        List<Responsavel> responsaveis = List.of(
                new Responsavel("Nome do Responsável", "responsavel@email.com", "senha123", "123456789", "123.456.789-10", "M", TipoUsuario.RESPONSAVEL, LocalDate.of(1990, 5, 15), "Apresentação do Responsável", LocalDate.now(), List.of()),

        new Responsavel("Nome do Responsável", "responsavel@email.com", "senha123", "123456789", "123.456.789-10", "M", TipoUsuario.RESPONSAVEL, LocalDate.of(1990, 5, 15), "Apresentação do Responsável", LocalDate.now(), List.of())
        );

        when(repository.findAll()).thenReturn(responsaveis);

        List<Responsavel> resultado = service.list();

        assertEquals(responsaveis.size(), resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Buscar responsável por ID")
    void buscarResponsavelPorId() {
        Responsavel responsavel = new Responsavel("Nome do Responsável", "responsavel@email.com", "senha123", "123456789", "123.456.789-10", "M", TipoUsuario.RESPONSAVEL, LocalDate.of(1990, 5, 15), "Apresentação do Responsável", LocalDate.now(), List.of());

        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.of(responsavel));

        Responsavel resultado = service.byId(id);

        assertEquals(responsavel, resultado);
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Criar novo responsável")
    void criarResponsavel() {
        Responsavel novoResponsavel = new Responsavel("Nome do Responsável", "responsavel@email.com", "senha123", "123456789", "123.456.789-10", "M", TipoUsuario.RESPONSAVEL, LocalDate.of(1990, 5, 15), "Apresentação do Responsável", LocalDate.now(), List.of());

        String senhaCriptografada = "senhaCriptografada";

        when(passwordEncoder.encode(novoResponsavel.getSenha())).thenReturn(senhaCriptografada);
        when(usuarioRepository.findByEmail(novoResponsavel.getEmail())).thenReturn(Optional.empty());
        when(repository.save(novoResponsavel)).thenReturn(novoResponsavel);

        Responsavel resultado = service.criar(novoResponsavel);

        assertEquals(senhaCriptografada, resultado.getSenha());
        verify(repository, times(1)).save(novoResponsavel);
    }

    @Test
    @DisplayName("Criar responsável com email existente")
    void criarResponsavelEmailExistente() {
        Responsavel novoResponsavel = new Responsavel("Nome do Responsável", "responsavel@email.com", "senha123", "123456789", "123.456.789-10", "M", TipoUsuario.RESPONSAVEL, LocalDate.of(1990, 5, 15), "Apresentação do Responsável", LocalDate.now(), List.of());

        when(usuarioRepository.findByEmail(novoResponsavel.getEmail())).thenReturn(Optional.of(new Responsavel()));

        assertThrows(ConflitoException.class, () -> service.criar(novoResponsavel));
    }

    @Test
    @DisplayName("Excluir responsável")
    void excluirResponsavel() {
        Responsavel responsavel = new Responsavel("Nome do Responsável", "responsavel@email.com", "senha123", "123456789", "123.456.789-10", "M", TipoUsuario.RESPONSAVEL, LocalDate.of(1990, 5, 15), "Apresentação do Responsável", LocalDate.now(), List.of());
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.of(responsavel));

        service.delete(id);

        verify(repository, times(1)).delete(responsavel);
    }

    @Test
    @DisplayName("Excluir responsável não encontrado")
    void excluirResponsavelNaoEncontrado() {
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> service.delete(id));
    }

    @Test
    @DisplayName("Atualizar responsável")
    void atualizarResponsavel() {
        Responsavel responsavel = new Responsavel("Nome do Responsável", "responsavel@email.com",
                "senha123", "123456789", "123.456.789-10", "M", TipoUsuario.RESPONSAVEL,
                LocalDate.of(1990, 5, 15), "Apresentação do Responsável", LocalDate.now(), List.of());
        responsavel.setIdUsuario(1);

        when(repository.findById(1)).thenReturn(Optional.of(responsavel));

        Responsavel responsavelAtualizado = new Responsavel("Nome do Responsável Atualizado",
                "responsavelatualizado@email.com", "senhaCriptografada", "123456789",
                "123.456.789-10", "M", TipoUsuario.RESPONSAVEL, LocalDate.of(1990, 5, 15),
                "Apresentação do Responsável", LocalDate.now(), List.of());

        Responsavel resposta = service.update(1, responsavelAtualizado);

        verify(repository, times(1)).save(any(Responsavel.class));

        assertEquals(responsavelAtualizado.getNome(), resposta.getNome());
        assertEquals(responsavelAtualizado.getEmail(), resposta.getEmail());
        assertEquals(responsavelAtualizado.getSenha(), resposta.getSenha());
        // Continue para todos os outros atributos...
    }

}
