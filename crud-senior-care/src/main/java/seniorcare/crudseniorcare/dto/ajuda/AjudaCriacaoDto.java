package seniorcare.crudseniorcare.dto.ajuda;

import jakarta.persistence.*;
import seniorcare.crudseniorcare.model.usuario.Cuidador;

import java.util.List;
import java.util.UUID;

public class AjudaCriacaoDto {


    private String nome;

    @ManyToMany
    @JoinTable(name = "ajuda_cuidadores",
            joinColumns = @JoinColumn(name = "idAjuda"),
            inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<Cuidador> cuidadores;



}
