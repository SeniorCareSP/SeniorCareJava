package seniorcare.crudseniorcare.service.usuario.dto.cuidador;

import lombok.AllArgsConstructor;
import lombok.Data;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.service.ajuda.dto.AjudaCriacaoDto;
import seniorcare.crudseniorcare.service.caracteristica.dto.CaracteristicaCriacaoDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioCriacaoDto;

import java.util.List;
@Data
public class    UsuarioCriacaoCuidadorDto extends UsuarioCriacaoDto {
    private String experiencia;
    private String faixaEtaria;
    private double precoHora;
    private List<CaracteristicaCriacaoDto> caracteristicas;
    private List<AjudaCriacaoDto> ajuda;


}
