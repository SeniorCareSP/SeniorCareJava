package seniorcare.crudseniorcare.service.caracteristica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;

import java.util.List;

@Getter
@Setter

public class CaracteristicaCriacaoDto {

    @NotBlank
    private String nome;

    private List<Cuidador> cuidadores;

}
