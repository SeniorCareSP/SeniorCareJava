package seniorcare.crudseniorcare.dto.ajuda;

import jakarta.persistence.*;
import seniorcare.crudseniorcare.model.usuario.Cuidador;

import java.util.List;
import java.util.UUID;

public class AjudaListagemDto {

    private UUID idAjuda;
    private String nome;
    private List<Cuidador> cuidadores;

}
