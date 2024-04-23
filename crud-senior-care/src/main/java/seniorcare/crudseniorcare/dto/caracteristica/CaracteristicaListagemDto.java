package seniorcare.crudseniorcare.dto.caracteristica;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.model.usuario.Cuidador;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CaracteristicaListagemDto {

        private UUID idCaracteristica;
        private String nome;
        private List<Cuidador> cuidadores;



}
