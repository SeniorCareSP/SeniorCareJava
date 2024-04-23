package seniorcare.crudseniorcare.dto.usuario.usuario;
// UsuarioCriacaoDto.java

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.dto.agenda.AgendaListagemDto;
import seniorcare.crudseniorcare.dto.comentario.ComentarioListagemDto;
import seniorcare.crudseniorcare.dto.endereco.EnderecoListagemDto;
import seniorcare.crudseniorcare.dto.idioma.IdiomaListagemDto;
import seniorcare.crudseniorcare.model.Endereco;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UsuarioCriacaoDto {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private String sexoBiologico;
    private LocalDate dtNascimento;
    private String apresentacao;
    private LocalDate dtCadastro;
    private List<AgendaListagemDto> agendas;
    private List<IdiomaListagemDto> idiomas;
    private List<EnderecoListagemDto> enderecos;
    private List<ComentarioListagemDto> comentarios;
}
