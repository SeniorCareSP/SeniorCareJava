package seniorcare.crudseniorcare.service.endereco.dto;

import lombok.Getter;
import lombok.Setter;
//import seniorcare.crudseniorcare.dto.usuario.UsuarioDto;
import java.util.UUID;

@Getter
@Setter
public class EnderecoListagemDto {

    private UUID idEndereco;
    private String rua;
    private String cep;
    private String logradouro;
    private String complemento;
    private String numero;
    private String cidade;
    private String bairro;
    private UUID usuario;
}