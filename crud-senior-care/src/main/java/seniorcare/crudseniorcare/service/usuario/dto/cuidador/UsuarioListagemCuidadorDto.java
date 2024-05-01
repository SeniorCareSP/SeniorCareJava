package seniorcare.crudseniorcare.service.usuario.dto.cuidador;

import lombok.Data;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;

@Data
public class UsuarioListagemCuidadorDto extends UsuarioListagemDto {

    private double precoHora;
    private List<Caracteristica> caracteristicas;
    private List<Ajuda> ajudas;


}
