package seniorcare.crudseniorcare.service.denuncia.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.List;
@Data
public class DenunciaCriacaoDto {

    private List<String> info;

    private String detalhes;

    private Boolean status;

    private Integer usuarioDenunciador;

    private Integer usuarioDenunciado;

}
