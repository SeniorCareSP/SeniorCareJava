package seniorcare.crudseniorcare.service.caracteristica.dto;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CaracteristicaListagemDto {

        private UUID idCaracteristica;
        private String nome;
        private List<Cuidador> cuidadores;



}
