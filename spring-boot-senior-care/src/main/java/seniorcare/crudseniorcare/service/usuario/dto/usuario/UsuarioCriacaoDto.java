package seniorcare.crudseniorcare.service.usuario.dto.usuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.usuario.TipoUsuario;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaCriacaoDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoCriacaoDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoListagemDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoCriacaoDto;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaCriacaoDto;

import java.time.LocalDate;
import java.util.List;
@Data
public class    UsuarioCriacaoDto {
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
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoDeUsuario;
    @Past
    private LocalDate dtNascimento;
    private String apresentacao;
    private LocalDate dtCadastro;
    private String imagemUrl;
    private Boolean status;
    private AgendaCriacaoDto agendas;
    private List<IdiomaCriacaoDto> idiomas;
    private List<FavoritoCriacaoDto> favoritos;
    private String experiencia;
    private EnderecoCriacaoDto endereco;
    private String faixaEtaria;

}
