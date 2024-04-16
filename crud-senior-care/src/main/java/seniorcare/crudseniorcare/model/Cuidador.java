package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@DiscriminatorValue("CUIDADOR")
public class Cuidador extends Usuario implements Serializable {

    private UUID idCuidador;
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
