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
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL)
    private List<Caracteristica> caracteristicas;
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL)
    private List<Ajuda> ajudas;
}
