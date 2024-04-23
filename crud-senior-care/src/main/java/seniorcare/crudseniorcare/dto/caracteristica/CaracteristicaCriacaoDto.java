package seniorcare.crudseniorcare.dto.caracteristica;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.model.usuario.Cuidador;

import java.util.List;
import java.util.UUID;
@Getter
@Setter

public class CaracteristicaCriacaoDto {

    @NotBlank
    private String nome;

    private List<Cuidador> cuidadores;

}
