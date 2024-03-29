package seniorcare.crudseniorcare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAgenda;
    private String diaDaSemana;
    private boolean periodo_manha;
    private boolean periodo_tarde;
    private boolean periodo_noite;

    public UUID getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(UUID idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public boolean isPeriodo_manha() {
        return periodo_manha;
    }

    public void setPeriodo_manha(boolean periodo_manha) {
        this.periodo_manha = periodo_manha;
    }

    public boolean isPeriodo_tarde() {
        return periodo_tarde;
    }

    public void setPeriodo_tarde(boolean periodo_tarde) {
        this.periodo_tarde = periodo_tarde;
    }

    public boolean isPeriodo_noite() {
        return periodo_noite;
    }

    public void setPeriodo_noite(boolean periodo_noite) {
        this.periodo_noite = periodo_noite;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "diaDaSemana='" + diaDaSemana + '\'' +
                ", periodo_manha=" + periodo_manha +
                ", periodo_tarde=" + periodo_tarde +
                ", periodo_noite=" + periodo_noite +
                '}';
    }
}
