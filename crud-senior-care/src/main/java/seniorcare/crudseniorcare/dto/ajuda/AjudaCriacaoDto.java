package seniorcare.crudseniorcare.dto.ajuda;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.model.usuario.Cuidador;

import java.util.List;
import java.util.UUID;
@Getter
@Setter

public class AjudaCriacaoDto {


    private String nome;

    @ManyToMany
    @JoinTable(name = "ajuda_cuidadores",
            joinColumns = @JoinColumn(name = "idAjuda"),
            inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<Cuidador> cuidadores;



}
