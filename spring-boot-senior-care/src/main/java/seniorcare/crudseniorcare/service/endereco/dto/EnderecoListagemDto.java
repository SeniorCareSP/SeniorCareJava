package seniorcare.crudseniorcare.service.endereco.dto;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
//import seniorcare.crudseniorcare.dto.usuario.UsuarioDto;
import java.util.UUID;

@Getter
@Setter
public class EnderecoListagemDto {

    private Integer idEndereco;
    private String cep;
    private String logradouro;
    private String complemento;
    private String numero;
    private String cidade;
    private String bairro;

}