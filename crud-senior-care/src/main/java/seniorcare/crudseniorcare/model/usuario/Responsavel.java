package seniorcare.crudseniorcare.model.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.model.Agenda;
import seniorcare.crudseniorcare.model.Idoso;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Entity
@DiscriminatorValue("RESPONSAVEL")
public class Responsavel extends Usuario {
    private double precoHora;

    @OneToMany(mappedBy = "responsavel")
    private List<Idoso> idosos;

    public Responsavel(double precoHora, List<Idoso> idosos) {
        this.precoHora = precoHora;
        this.idosos = idosos;
    }

    public Responsavel() {

    }
}
