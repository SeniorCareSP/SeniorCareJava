package seniorcare.crudseniorcare.service.denuncia.dto;

import lombok.Data;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDenunciaDto;

import java.util.List;
@Data
public class DenunciaListagemUsuarioDto {


    private Integer id;

    private List<String> info;

    private String detalhes;

    private Boolean status;

    private UsuarioListagemDenunciaDto usuarioDenunciado;
}
