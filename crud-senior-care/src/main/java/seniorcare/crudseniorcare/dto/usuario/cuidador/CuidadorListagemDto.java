package seniorcare.crudseniorcare.dto.usuario.cuidador;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.dto.usuario.usuario.UsuarioListagemDto;

@Getter
@Setter
public class CuidadorListagemDto extends UsuarioListagemDto {
    private String experiencia;
    private String faixaEtaria;
    private int qtdIdoso;
    private double precoHora;
}