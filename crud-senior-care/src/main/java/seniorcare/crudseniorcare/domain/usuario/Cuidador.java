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
@Table(name="tb_cuidador")

@DiscriminatorValue("CUIDADOR")
public class Cuidador extends Usuario{

    private String experiencia;
    private String faixaEtaria;
    private double precoHora;
    @OneToMany
    @JoinTable(name = "cuidador_caracteristica", joinColumns = @JoinColumn(name = "cuidador_id"), inverseJoinColumns = @JoinColumn(name = "caracteristica_id"))
    private List<Caracteristica> caracteristicas;
    @OneToMany
    @JoinTable(name = "cuidador_ajuda",
            joinColumns = @JoinColumn(name = "cuidador_id"),
            inverseJoinColumns = @JoinColumn(name = "ajuda_id"))
    private List<Ajuda> ajudas;
}
