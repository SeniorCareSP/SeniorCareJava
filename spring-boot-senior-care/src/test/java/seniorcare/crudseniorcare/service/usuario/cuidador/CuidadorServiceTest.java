package seniorcare.crudseniorcare.service.usuario.cuidador;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.usuario.Administrador;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.agenda.AgendaService;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;

import java.util.ArrayList;
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
    private EnderecoService enderecoService;
    @Mock
    private AgendaService agendaService;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CuidadorService service;

    @Test
    @DisplayName("Listar todos os cuidadores")
    void listarCuidadores() {
        List<Cuidador> cuidadores = new ArrayList<>();
        Cuidador cuidador1 = new Cuidador();
        cuidador1.setNome("Nome do Responsavel");
        cuidador1.setSenha("senhaCriptografada");
        cuidador1.setExperiencia("Experiencia 2");
        cuidador1.setFaixaEtaria("Faixa etaria 2");
        cuidador1.setPrecoHora(30.0);
        cuidador1.setIdiomas(new ArrayList<>());

        Cuidador cuidador2 = new Cuidador();
        cuidador2.setNome("Nome do Responsavel");
        cuidador2.setSenha("senhaCriptografada");
        cuidador2.setExperiencia("Experiencia 2");
        cuidador2.setFaixaEtaria("Faixa etaria 2");
        cuidador2.setPrecoHora(30.0);
        cuidador2.setIdiomas(new ArrayList<>());

        cuidadores.add(cuidador1);
        cuidadores.add(cuidador2);

        when(repository.findAll()).thenReturn(cuidadores);

        List<Cuidador> resultado = service.list();

        assertEquals(cuidadores.size(), resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Buscar cuidador por ID")
    void buscarCuidadorPorId() {
        Cuidador cuidador = new Cuidador();
        cuidador.setIdUsuario(1);
        cuidador.setNome("Nome do Responsavel");
        cuidador.setSenha("senhaCriptografada");
        cuidador.setExperiencia("Experiencia 2");
        cuidador.setFaixaEtaria("Faixa etaria 2");
        cuidador.setPrecoHora(30.0);
        cuidador.setIdiomas(new ArrayList<>());

        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.of(cuidador));

        Cuidador resultado = service.byId(id);

        assertEquals(cuidador, resultado);
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Criar novo cuidador")
    void criarCuidador() {

        var endereco = new Endereco();
        endereco.setIdEndereco(1);
        var enderecoCriado = new Endereco();
        enderecoCriado.setIdEndereco(1);
        var agenda = new Agenda();
        agenda.setIdAgenda(1);
        var agendaCriado = new Agenda();
        agendaCriado.setIdAgenda(1);

        Cuidador cuidadorParaSalvar = new Cuidador(); cuidadorParaSalvar.setNome("Nome do Responsavel");
        cuidadorParaSalvar.setSenha("senhaCriptografada");
        cuidadorParaSalvar.setExperiencia("Experiencia 2");
        cuidadorParaSalvar.setFaixaEtaria("Faixa etaria 2");
        cuidadorParaSalvar.setPrecoHora(30.0);
        cuidadorParaSalvar.setEndereco(endereco);
        cuidadorParaSalvar.setAgenda(agenda);
        cuidadorParaSalvar.setIdiomas(new ArrayList<>());

        Cuidador cuidadorSalvo = new Cuidador();
        cuidadorSalvo.setIdUsuario(1);
        cuidadorSalvo.setNome("Nome do Responsavel");
        cuidadorSalvo.setSenha("senhaCriptografada");
        cuidadorSalvo.setExperiencia("Experiencia 2");
        cuidadorSalvo.setFaixaEtaria("Faixa etaria 2");
        cuidadorSalvo.setPrecoHora(30.0);
        cuidadorSalvo.setEndereco(enderecoCriado);
        cuidadorSalvo.setAgenda(agendaCriado);
        cuidadorSalvo.setIdiomas(new ArrayList<>());

        String senhaCriptografada = "senhaCriptografada";


        when(usuarioRepository.findByEmailIgnoreCase(cuidadorSalvo.getEmail())).thenReturn(Optional.empty());
        when(enderecoService.create(endereco)).thenReturn(enderecoCriado);
        when(agendaService.create(agenda)).thenReturn(agendaCriado);
        when(repository.save(cuidadorParaSalvar)).thenReturn(cuidadorSalvo);
        when(passwordEncoder.encode(cuidadorParaSalvar.getSenha())).thenReturn(senhaCriptografada);

        Cuidador cuidadorResposta = service.criar(cuidadorParaSalvar);

       assertNotNull(cuidadorResposta.getIdUsuario());
        assertNotNull(cuidadorParaSalvar.getNome(), cuidadorResposta.getNome());
        assertEquals(cuidadorParaSalvar.getApresentacao(), cuidadorResposta.getApresentacao());
        assertEquals(cuidadorParaSalvar.getSenha(), cuidadorResposta.getSenha());
    }


    @Test
    @DisplayName("Criar cuidador com email existente")
    void criarCuidadorEmailExistente() {
        Cuidador novoCuidador = new Cuidador();
        novoCuidador.setIdUsuario(1);
        novoCuidador.setNome("Nome do Responsavel");
        novoCuidador.setSenha("senhaCriptografada");
        novoCuidador.setExperiencia("Experiencia 2");
        novoCuidador.setFaixaEtaria("Faixa etaria 2");
        novoCuidador.setPrecoHora(30.0);
        novoCuidador.setIdiomas(new ArrayList<>());
        when(usuarioRepository.findByEmailIgnoreCase(novoCuidador.getEmail())).thenReturn(Optional.of(new Cuidador()));

        assertThrows(ConflitoException.class, () -> service.criar(novoCuidador));
    }

    @Test
    @DisplayName("Excluir cuidador")
    void excluirCuidador() {
        Cuidador cuidador = new Cuidador();
        cuidador.setIdUsuario(1);
        cuidador.setNome("Nome do Responsavel");
        cuidador.setSenha("senhaCriptografada");
        cuidador.setExperiencia("Experiencia 2");
        cuidador.setFaixaEtaria("Faixa etaria 2");
        cuidador.setPrecoHora(30.0);
        cuidador.setIdiomas(new ArrayList<>());
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

        var cuidador = new Cuidador();
        cuidador.setIdUsuario(1);
        cuidador.setNome("A");

        var cuidadorAtualizar = new Cuidador();
        cuidadorAtualizar.setNome("B");

        var cuidadorAtualizado = new Cuidador();
        cuidadorAtualizado.setIdUsuario(1);
        cuidadorAtualizado.setNome("B");

        Integer idInformado = 1;

//        when(repository.existsById(idInformado)).thenReturn(Boolean.TRUE);
        when(repository.findById(idInformado)).thenReturn(Optional.of(cuidador));
        when(repository.save(cuidadorAtualizar)).thenReturn(cuidadorAtualizado);

        Cuidador cuidadorResposta = service.update(idInformado, cuidadorAtualizar);

        assertEquals(cuidadorAtualizado.getIdUsuario(), cuidadorResposta.getIdUsuario());
        assertEquals(cuidadorAtualizado.getNome(), cuidadorResposta.getNome());

        verify(repository, times(1)).findById(idInformado);
        verify(repository, times(0)).findAll();
        verify(repository, times(1)).save(cuidadorAtualizar);

    }

    @Test
    @DisplayName("Atualizar cuidador não encontrado")
    void atualizarCuidadorNaoEncontrado() {
        Cuidador cuidador = new Cuidador();
        cuidador.setIdUsuario(1);
        cuidador.setNome("Nome do Responsavel");
        cuidador.setSenha("senhaCriptografada");
        cuidador.setExperiencia("Experiencia 2");
        cuidador.setFaixaEtaria("Faixa etaria 2");
        cuidador.setPrecoHora(30.0);
        cuidador.setIdiomas(new ArrayList<>());
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> service.update(id, cuidador));
    }

}
