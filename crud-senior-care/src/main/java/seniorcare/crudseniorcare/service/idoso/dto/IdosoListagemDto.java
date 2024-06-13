package seniorcare.crudseniorcare.service.idoso.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;

import java.time.LocalDate;
import java.util.UUID;
@Data
public class IdosoListagemDto {

    private Integer idIdoso;
    private String nome;
    private String descricao;
    private boolean mobilidade;
    private boolean lucido;
    private String doencasCronicas;
    private Boolean cuidadosMin;
    private String genero;
    private LocalDate dtNasc;

}
