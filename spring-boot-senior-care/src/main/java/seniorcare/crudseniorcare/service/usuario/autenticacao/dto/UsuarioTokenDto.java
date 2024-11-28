package seniorcare.crudseniorcare.service.usuario.autenticacao.dto;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.TipoUsuario;

import java.util.UUID;
@Getter @Setter
public class   UsuarioTokenDto {

    private Integer userId;
    private String nome;
    private String email;
    private String imagemUrl;
    private TipoUsuario tipoUsuario;
    private String token;
    private Boolean status;
}
