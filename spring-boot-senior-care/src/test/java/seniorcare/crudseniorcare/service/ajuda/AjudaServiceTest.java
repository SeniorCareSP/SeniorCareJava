package seniorcare.crudseniorcare.service.ajuda;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.ajuda.repository.AjudaRepository;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AjudaServiceTest {
    @Mock
    private AjudaRepository repository;
    @InjectMocks
    private AjudaService service;
    @Test
    @DisplayName("Listar corretamente")
    void cenarioCorreto1(){
        //GIVEN
        List<Ajuda> ajudasEsperada = List.of(
                new Ajuda(1, "Passar roupa", new Cuidador()),
                new Ajuda(2, "Lavar louça", new Cuidador())
        );

        //WHEN
        when(repository.findAll()).thenReturn(ajudasEsperada);

        //THEN
        List<Ajuda> resposta = service.list();

        //ASSERT
        assertEquals(ajudasEsperada.size(), resposta.size());

        for (int i = 0; i < ajudasEsperada.size(); i++) {
            assertEquals(ajudasEsperada.get(i).getIdAjuda(), resposta.get(i).getIdAjuda());
            assertEquals(ajudasEsperada.get(i).getNome(), resposta.get(i).getNome());
            assertEquals(ajudasEsperada.get(i).getCuidador(), resposta.get(i).getCuidador());
        }

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Buscar por ID")
    void cenarioCorretoPorId() {
        // GIVEN
        Ajuda ajuda = new Ajuda(1, "Passar roupa", new Cuidador());

        Integer idInformado = 1;

        // WHEN
        when(repository.findById(idInformado)).thenReturn(Optional.of(ajuda));

        // THEN
        Ajuda resposta = service.byId(idInformado);

        // ASSERT
        assertEquals(idInformado, resposta.getIdAjuda());
        assertEquals(ajuda.getNome(), resposta.getNome());

        verify(repository, times(1)).findById(idInformado);
        verify(repository, times(0)).findAll();
    }

    @Test
    @DisplayName("Criar corretamente")
    void cenarioCorreto3(){
        //GIVEN
        Ajuda ajuda =
                new Ajuda(1, "Passar roupa", new Cuidador());

        // Configurando o mock para retornar a entidade Ajuda quando findById for chamado
        when(repository.findById(1)).thenReturn(Optional.of(ajuda));

        //WHEN
        Ajuda resposta = service.byId(1);

        //ASSERT
        assertEquals(ajuda, resposta);

        verify(repository, times(1)).findById(1);
    }


    @Test
    @DisplayName("Retorna lista vazia incorretamente")
    void cenarioIncorreto1() {
        // GIVEN
        var produtos = new ArrayList<Ajuda>();

        // WHEN
        when(repository.findAll()).thenReturn(produtos);

        // THEN
        List<Ajuda> resposta = service.list();

        // ASSERT
        assertTrue(resposta.isEmpty());
    }


    @Test
    @DisplayName("ID inexistente")
    void cenarioIncorretoPorId() {

        // WHEN
        when(repository.findById(any())).thenReturn(Optional.empty());

        // THEN/ASSERT - captação de exception
        assertThrows(NaoEncontradoException.class,
                () -> service.byId(any()));

        // ASSERT
        verify(repository, times(1)).findById(any());
        verify(repository, times(0)).findAll();
    }

    @Test
    @DisplayName("Ajuda não encontrada para atualização")
    void cenarioIncorretoAtt(){
        //GIVEN
        Ajuda ajuda = new Ajuda(1, "Passar roupa", new Cuidador());
        int idCadastrado = 1;

        //ASSERT
       assertThrows(NaoEncontradoException.class,
               () -> service.update(any(), ajuda));

    }

    @Test
    @DisplayName("Ajuda não encontrada para deletar")
    void cenarioIncorretoDel(){
        //GIVEN
        Ajuda ajuda = new Ajuda(1, "Passar roupa", new Cuidador());
        repository.save(ajuda);
        //ASSERT
        assertThrows(NaoEncontradoException.class,
                () -> service.delete(any()));

    }






}
