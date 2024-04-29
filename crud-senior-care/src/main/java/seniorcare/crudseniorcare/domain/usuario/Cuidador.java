package seniorcare.crudseniorcare.domain.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;

import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("CUIDADOR")
public class Cuidador extends Usuario{

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
