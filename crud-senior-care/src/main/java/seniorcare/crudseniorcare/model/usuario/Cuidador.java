package seniorcare.crudseniorcare.model.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.model.Ajuda;
import seniorcare.crudseniorcare.model.Caracteristica;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@DiscriminatorValue("CUIDADOR")
public class Cuidador implements Serializable {

    private String experiencia;
    private String faixaEtaria;
    private int qtdIdoso;
    private double precoHora;
    @ManyToMany
    @JoinTable(name = "cuidador_caracteristica", joinColumns = @JoinColumn(name = "cuidador_id"), inverseJoinColumns = @JoinColumn(name = "caracteristica_id"))
    private List<Caracteristica> caracteristicas;
    @ManyToMany
    @JoinTable(name = "cuidador_ajuda",
            joinColumns = @JoinColumn(name = "cuidador_id"),
            inverseJoinColumns = @JoinColumn(name = "ajuda_id"))
    private List<Ajuda> ajudas;
}
