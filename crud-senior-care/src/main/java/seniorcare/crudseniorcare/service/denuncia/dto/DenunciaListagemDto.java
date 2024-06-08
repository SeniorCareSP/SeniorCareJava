package seniorcare.crudseniorcare.service.denuncia.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.List;
@Data
public class DenunciaListagemDto {

    private Integer id;

    private List<String> info;

    private String detalhes;

    private Boolean status;

    private Usuario usuarioDenunciador;

    private Usuario usuarioDenunciado;

}
