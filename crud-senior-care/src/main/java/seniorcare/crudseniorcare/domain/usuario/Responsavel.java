package seniorcare.crudseniorcare.domain.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.idoso.Idoso;

import java.util.List;

@Getter @Setter
@Entity
@DiscriminatorValue("RESPONSAVEL")
public class Responsavel extends Usuario {
    private double precoHora;

    @OneToMany(mappedBy = "responsavel")
    private List<Idoso> idosos;


}
