package seniorcare.crudseniorcare.service.usuario.dto.usuario;

import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.idioma.Idioma;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Data
public class UsuarioListagemDto {
    private UUID idUsuario;

    private String nome;

    private String email;

    private String telefone;
    private String cpf;
    private String sexoBiologico;

    private LocalDate dtNascimento;
    private String apresentacao;
    private LocalDate dtCadastro;
    private List<Agenda> agendas;
    private List<Idioma> idiomas;
    private List<Endereco> enderecos;

}