package seniorcare.crudseniorcare.service.ajuda.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;

import java.util.List;

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
