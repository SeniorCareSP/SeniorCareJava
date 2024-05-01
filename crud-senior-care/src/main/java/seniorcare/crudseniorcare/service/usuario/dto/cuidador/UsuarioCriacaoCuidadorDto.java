package seniorcare.crudseniorcare.service.usuario.dto.cuidador;

import lombok.Data;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioCriacaoDto;

import java.util.List;
@Data
public class UsuarioCriacaoCuidadorDto extends UsuarioCriacaoDto {
    private String experiencia;
    private String faixaEtaria;
    private double precoHora;
    private List<Caracteristica> caracteristicas;
    private List<Ajuda> ajudas;


}
