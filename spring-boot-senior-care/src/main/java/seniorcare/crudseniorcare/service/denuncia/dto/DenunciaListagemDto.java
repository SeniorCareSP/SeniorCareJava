package seniorcare.crudseniorcare.service.denuncia.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDenunciaDto;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class DenunciaListagemDto {

    private Integer id;

    private List<String> info;

    private String detalhes;

    private Boolean status;

    private UsuarioListagemDenunciaDto usuarioDenunciador;

    private UsuarioListagemDenunciaDto usuarioDenunciado;

    private LocalDateTime dataDenuncia;
}
