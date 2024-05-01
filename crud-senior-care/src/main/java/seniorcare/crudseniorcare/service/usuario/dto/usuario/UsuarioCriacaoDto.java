package seniorcare.crudseniorcare.service.usuario.dto.usuario;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.idioma.Idioma;

import java.time.LocalDate;
import java.util.List;
@Data
public class UsuarioCriacaoDto {
    @Size(min = 3, max = 100)
    @NotBlank
    private String nome;
    @Email
    @NotBlank
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    @NotBlank
    private String senha;
    @Pattern(regexp = "^\\([1-9]{2}\\)\\s9?[0-9]{4}-?[0-9]{4}$")
    private String telefone;
    @CPF
    private String cpf;
    private String sexoBiologico;
    @Past
    private LocalDate dtNascimento;
    private String apresentacao;
    private LocalDate dtCadastro;
    private List<Agenda> agendas;
    private List<Idioma> idiomas;
    private List<Endereco> enderecos;
    private String experiencia;
    private String faixaEtaria;
}
