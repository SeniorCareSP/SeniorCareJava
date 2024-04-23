package seniorcare.crudseniorcare.dto.ajuda;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.model.usuario.Cuidador;

import java.util.List;
import java.util.UUID;
@Getter
@Setter

public class AjudaListagemDto {

    private UUID idAjuda;
    private String nome;
    private List<Cuidador> cuidadores;

}
