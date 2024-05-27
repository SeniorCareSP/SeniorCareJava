package seniorcare.crudseniorcare.service.usuario.dto.usuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.usuario.TipoUsuario;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaListagemDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoListagemDto;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaListagemDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class UsuarioListagemDto {
    private Integer idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String sexoBiologico;
    private LocalDate dtNascimento;
    private String apresentacao;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoDeUsuario;
    private LocalDate dtCadastro;
    private AgendaListagemDto agenda;
    private List<IdiomaListagemDto> idiomas;
    private EnderecoListagemDto endereco;
}
