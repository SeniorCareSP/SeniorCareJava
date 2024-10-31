package seniorcare.crudseniorcare.service.usuario.responsavel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.TipoUsuario;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.agenda.AgendaService;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private EnderecoService enderecoService;
    @Mock
    private AgendaService agendaService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private ResponsavelService service;

    @Test
    @DisplayName("Listar todos os responsáveis")
    void listarResponsaveis() {
        List<Responsavel> responsaveis = new ArrayList<>();
        Responsavel responsavel1 = new Responsavel();

        Responsavel responsavel = new Responsavel();
        responsavel.setNome("Nome do Responsavel");
        responsavel.setEmail("responsavel@email.com");
        responsavel.setSenha("senha123");
        responsavel.setTelefone("123456789");
        responsavel.setCpf("123.456.789-10");
        responsavel.setSexoBiologico("M");
        responsavel.setTipoDeUsuario(TipoUsuario.RESPONSAVEL);
        responsavel.setDtNascimento(LocalDate.of(1990, 5, 15));
        responsavel.setApresentacao("Apresentação do Responsável");
        responsavel.setDtCadastro(LocalDate.now());
        responsavel.setIdosos(List.of());

        Responsavel responsavel2 = new Responsavel();
        responsavel2.setNome("Nome do Responsavel 2");
        responsavel2.setEmail("responsavel2@email.com");
        responsavel2.setSenha("senha123");
        responsavel2.setTelefone("123456789");
        responsavel2.setCpf("123.456.789-10");
        responsavel2.setSexoBiologico("M");
        responsavel2.setTipoDeUsuario(TipoUsuario.RESPONSAVEL);
        responsavel2.setDtNascimento(LocalDate.of(1990, 5, 15));
        responsavel2.setApresentacao("Apresentação do Responsável");
        responsavel2.setDtCadastro(LocalDate.now());
        responsavel2.setIdosos(List.of());

        responsaveis.add(responsavel1);
        responsaveis.add(responsavel);

        when(repository.findAll()).thenReturn(responsaveis);

        List<Responsavel> resultado = service.list();

        assertEquals(responsaveis.size(), resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Buscar responsável por ID")
    void buscarResponsavelPorId() {
        Responsavel responsavel = new Responsavel();
        responsavel.setIdUsuario(1);
        responsavel.setNome("Nome do Responsavel");
        responsavel.setEmail("responsavel@email.com");
        responsavel.setSenha("senha123");
        responsavel.setTelefone("123456789");
        responsavel.setCpf("123.456.789-10");
        responsavel.setSexoBiologico("M");
        responsavel.setTipoDeUsuario(TipoUsuario.RESPONSAVEL);
        responsavel.setDtNascimento(LocalDate.of(1990, 5, 15));
        responsavel.setApresentacao("Apresentação do Responsável");
        responsavel.setDtCadastro(LocalDate.now());
        responsavel.setIdosos(List.of());

        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.of(responsavel));

        Responsavel resultado = service.byId(id);

        assertEquals(responsavel, resultado);
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Criar responsável com email existente")
    void criarResponsavelEmailExistente() {
        Responsavel novoResponsavel = new Responsavel();
        novoResponsavel.setNome("Nome do Responsavel");
        novoResponsavel.setEmail("responsavel@email.com");
        novoResponsavel.setSenha("senha123");
        novoResponsavel.setCpf("123.456.789-10");
        novoResponsavel.setSexoBiologico("M");
        novoResponsavel.setTipoDeUsuario(TipoUsuario.RESPONSAVEL);
        novoResponsavel.setDtNascimento(LocalDate.of(1990, 5, 15));
        novoResponsavel.setApresentacao("Apresentação do Responsável");
        novoResponsavel.setDtCadastro(LocalDate.now());
        novoResponsavel.setIdosos(List.of());

        when(usuarioRepository.findByEmailIgnoreCase(novoResponsavel.getEmail())).thenReturn(Optional.of(new Responsavel()));

        assertThrows(ConflitoException.class, () -> service.criar(novoResponsavel));
    }

    @Test
    @DisplayName("Excluir responsável")
    void excluirResponsavel() {
        Responsavel responsavel = new Responsavel();
        responsavel.setNome("Nome do Responsavel");
        responsavel.setEmail("responsavel@email.com");
        responsavel.setSenha("senha123");
        responsavel.setTelefone("123456789");
        responsavel.setCpf("123.456.789-10");
        responsavel.setSexoBiologico("M");
        responsavel.setTipoDeUsuario(TipoUsuario.RESPONSAVEL);
        responsavel.setDtNascimento(LocalDate.of(1990, 5, 15));
        responsavel.setApresentacao("Apresentação do Responsável");
        responsavel.setDtCadastro(LocalDate.now());
        responsavel.setIdosos(List.of());
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

}
