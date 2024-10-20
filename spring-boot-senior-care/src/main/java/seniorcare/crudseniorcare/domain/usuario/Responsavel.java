package seniorcare.crudseniorcare.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.domain.idoso.Idoso;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
@Table(name="tb_responsavel")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("RESPONSAVEL")
public class Responsavel extends Usuario {

    @OneToMany(mappedBy = "responsavel", fetch = FetchType.LAZY)
    private List<Idoso> idosos;
    @OneToMany(mappedBy = "responsavel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Favorito> favoritos;


}
