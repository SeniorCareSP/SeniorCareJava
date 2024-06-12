package seniorcare.crudseniorcare.service.idoso.dto;

import jakarta.persistence.*;
import lombok.Data;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;

import java.util.UUID;
@Data
public class IdosoCriacaoDto {

    private String nome;
    private String descricao;
    private boolean mobilidade;
    private boolean lucido;
    private String doencasCronicas;
    private Boolean cuidadosMin;
    private String genero;
    private Integer responsavel;
}
