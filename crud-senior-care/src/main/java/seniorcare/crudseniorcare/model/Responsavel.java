package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Getter @Setter
@Entity
@DiscriminatorValue("RESPONSAVEL")
public class Responsavel extends Usuario implements Serializable {

    private UUID idResponsavel;

    private double precoHora;

    @OneToMany(mappedBy = "responsavel")
    private List<Idoso> idosos;

    @OneToMany(mappedBy = "usuario")
    private List<Agenda> agendas;

    public Responsavel(double precoHora, List<Idoso> idosos) {
        this.precoHora = precoHora;
        this.idosos = idosos;
    }

    public Responsavel() {

    }

    public UUID getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(UUID idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public double getPrecoHora() {
        return precoHora;
    }

    public void setPrecoHora(double precoHora) {
        this.precoHora = precoHora;
    }

    public List<Idoso> getIdosos() {
        return idosos;
    }

    public void setIdosos(List<Idoso> idosos) {
        this.idosos = idosos;
    }


    @Override
    public List<Agenda> getAgendas() {
        return agendas;
    }

    @Override
    public void setAgendas(List<Agenda> agendas) {
        this.agendas = agendas;
    }

    @Override
    public String toString() {
        return "Responsavel{" +
                "precoHora=" + precoHora +
                ", idosos=" + idosos +
                "} " + super.toString();
    }
}
