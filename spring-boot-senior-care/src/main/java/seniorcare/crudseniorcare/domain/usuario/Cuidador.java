package seniorcare.crudseniorcare.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.favorito.Favorito;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="tb_cuidador")
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CUIDADOR")
@Builder
public class Cuidador extends Usuario{

    private String experiencia;
    private String faixaEtaria;
    private double precoHora;
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL)
    private List<Caracteristica> caracteristicas;
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL)
    private List<Ajuda> ajudas;


}
