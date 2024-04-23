package seniorcare.crudseniorcare.dto.usuario.usuario;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.dto.agenda.AgendaListagemDto;
import seniorcare.crudseniorcare.dto.comentario.ComentarioListagemDto;
import seniorcare.crudseniorcare.dto.endereco.EnderecoListagemDto;
import seniorcare.crudseniorcare.dto.idioma.IdiomaListagemDto;
import seniorcare.crudseniorcare.model.Endereco;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UsuarioListagemDto {
    private UUID idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dtNascimento;
    private String apresentacao;
    private LocalDate dtCadastro;
    private List<AgendaListagemDto> agendas;
    private List<IdiomaListagemDto> idiomas;
    private List<EnderecoListagemDto> enderecos;
    private List<ComentarioListagemDto> comentarios;
}

