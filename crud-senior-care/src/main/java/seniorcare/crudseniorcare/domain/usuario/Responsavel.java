package seniorcare.crudseniorcare.domain.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @OneToMany(mappedBy = "responsavel")
    private List<Idoso> idosos;
    private List<Favorito> favoritos;


}
