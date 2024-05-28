package seniorcare.crudseniorcare.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.domain.endereco.Endereco;

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

    public Cuidador(String experiencia, String faixaEtaria, double precoHora, List<Caracteristica> caracteristicas, List<Ajuda> ajudas, Endereco endereco) {
        super();
        this.experiencia = experiencia;
        this.faixaEtaria = faixaEtaria;
        this.precoHora = precoHora;
        this.caracteristicas = caracteristicas;
        this.ajudas = ajudas;
        this.endereco = endereco;
    }


    private String experiencia;
    private String faixaEtaria;
    private double precoHora;
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL)
    private List<Caracteristica> caracteristicas;
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL)
    private List<Ajuda> ajudas;


}
