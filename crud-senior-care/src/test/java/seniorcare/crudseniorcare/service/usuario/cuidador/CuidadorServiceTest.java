package seniorcare.crudseniorcare.service.usuario.cuidador;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.usuario.Administrador;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CuidadorServiceTest {

    @Mock
    private CuidadorRepository repository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CuidadorService service;

    @Test
    @DisplayName("Listar todos os cuidadores")
    void listarCuidadores() {
        List<Cuidador> cuidadores = List.of(
                new Cuidador("Experiencia 2", "FaixaEtaria 2", 30.0, null, null),
                new Cuidador("Experiencia 3", "FaixaEtaria 3", 30.0, null, null)

        );

        when(repository.findAll()).thenReturn(cuidadores);

        List<Cuidador> resultado = service.list();

        assertEquals(cuidadores.size(), resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Buscar cuidador por ID")
    void buscarCuidadorPorId() {
        Cuidador cuidador = new Cuidador("Experiencia 2", "FaixaEtaria 2", 30.0, null, null);

        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.of(cuidador));

        Cuidador resultado = service.byId(id);

        assertEquals(cuidador, resultado);
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Criar novo cuidador")
    void criarCuidador() {

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua do Cuidador");
        endereco.setCidade("Cidade do Cuidador");
        endereco.setNumero("14A");
        endereco.setCep("CEP do Cuidador");

        Cuidador novoCuidador = new Cuidador("Experiencia 2", "FaixaEtaria 2", 30.0, null, null, endereco);
        String senhaCriptografada = "senhaCriptografada";


        when(passwordEncoder.encode(novoCuidador.getSenha())).thenReturn(senhaCriptografada);
        when(usuarioRepository.findByEmail(novoCuidador.getEmail())).thenReturn(Optional.empty());
        when(repository.save(novoCuidador)).thenReturn(novoCuidador);

        Cuidador resultado = service.criar(novoCuidador);

        assertEquals(senhaCriptografada, resultado.getSenha());
        verify(repository, times(1)).save(novoCuidador);
    }

    @Test
    @DisplayName("Criar administrador corretamente")
    void criarAdministradorCorreto() {
        // GIVEN
        Cuidador cuidador = new Cuidador();
        cuidador.setEmail("admin@example.com");
        cuidador.setSenha("senha123");

        when(passwordEncoder.encode(cuidador.getSenha())).thenReturn("senhaCriptografada");
        when(usuarioRepository.findByEmail(cuidador.getEmail())).thenReturn(Optional.empty());
        when(repository.save(cuidador)).thenReturn(cuidador);

        // WHEN
        Cuidador resposta = service.criar(cuidador);

        // THEN
    verify(repository, times(1)).save(cuidador);
    }

    @Test
    @DisplayName("Criar cuidador com email existente")
    void criarCuidadorEmailExistente() {
        Cuidador novoCuidador = new Cuidador("Experiencia 2", "FaixaEtaria 2", 30.0, null, null);

        when(usuarioRepository.findByEmail(novoCuidador.getEmail())).thenReturn(Optional.of(new Cuidador()));

        assertThrows(ConflitoException.class, () -> service.criar(novoCuidador));
    }

    @Test
    @DisplayName("Excluir cuidador")
    void excluirCuidador() {
        Cuidador cuidador = new Cuidador("Experiencia 2", "FaixaEtaria 2", 30.0, null, null);
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.of(cuidador));

        service.delete(id);

        verify(repository, times(1)).delete(cuidador);
    }

    @Test
    @DisplayName("Excluir cuidador não encontrado")
    void excluirCuidadorNaoEncontrado() {
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> service.delete(id));
    }

    @Test
    @DisplayName("Atualizar cuidador")
    void atualizarCuidador() {
        Cuidador cuidador = new Cuidador("Experiencia 2", "FaixaEtaria 2", 30.0, null, null);
        Cuidador atualizado = new Cuidador("Experiencia 1", "FaixaEtaria 3", 40.0, null, null);

        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.of(cuidador));
        Cuidador resultado = service.update(id, atualizado);

        assertEquals(atualizado.getExperiencia(), resultado.getExperiencia());
    }

    @Test
    @DisplayName("Atualizar cuidador não encontrado")
    void atualizarCuidadorNaoEncontrado() {
        Cuidador atualizado = new Cuidador("Experiencia 1", "FaixaEtaria 1", 20.0, null, null);

        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> service.update(id, atualizado));
    }
}
