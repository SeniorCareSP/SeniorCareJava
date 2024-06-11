package seniorcare.crudseniorcare.service.usuario.autenticacao.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Data
public class UsuarioLoginDto {

    private String email;
    private String senha;

}
