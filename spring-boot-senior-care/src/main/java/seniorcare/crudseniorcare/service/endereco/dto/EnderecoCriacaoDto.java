package seniorcare.crudseniorcare.service.endereco.dto;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.UUID;

@Getter
@Setter
public class EnderecoCriacaoDto {


    private String cep;
    private String logradouro;
    private String complemento;
    private String numero;
    private String cidade;
    private String bairro;
    private Usuario usuario;
}