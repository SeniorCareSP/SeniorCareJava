package seniorcare.crudseniorcare.service.usuario.dto.cuidador;

import lombok.Data;
import seniorcare.crudseniorcare.service.ajuda.dto.AjudaListagemDto;
import seniorcare.crudseniorcare.service.caracteristica.dto.CaracteristicaListagemDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioCriacaoDto;

import java.util.List;

@Data
public class CuidadorAtualizacaoDto extends UsuarioCriacaoDto {
    private String experiencia;
    private String faixaEtaria;
    private List<CaracteristicaListagemDto> caracteristicas;
    private List<AjudaListagemDto> ajudas;
}
