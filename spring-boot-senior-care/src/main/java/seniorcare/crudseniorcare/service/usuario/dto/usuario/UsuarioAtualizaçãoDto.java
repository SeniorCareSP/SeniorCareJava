package seniorcare.crudseniorcare.service.usuario.dto.usuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import seniorcare.crudseniorcare.domain.usuario.TipoUsuario;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaCriacaoDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoCriacaoDto;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoCriacaoDto;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaCriacaoDto;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioAtualizaçãoDto {
    @Size(min = 3, max = 100)
    @NotBlank
    private String nome;
    @Email
    @NotBlank
    private String email;
    @Pattern(regexp = "^\\([1-9]{2}\\)\\s9?[0-9]{4}-?[0-9]{4}$")
    private String telefone;
    @Past
    private String apresentacao;
    private Boolean status;
    private AgendaCriacaoDto agendas;
    private List<IdiomaCriacaoDto> idiomas;
    private String experiencia;
    private EnderecoCriacaoDto endereco;
}
