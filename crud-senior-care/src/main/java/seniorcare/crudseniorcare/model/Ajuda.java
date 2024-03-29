package seniorcare.crudseniorcare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;
@Entity
public class Ajuda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAjuda;
    private String nome;

    public UUID getIdAjuda() {
        return idAjuda;
    }

    public void setIdAjuda(UUID idAjuda) {
        this.idAjuda = idAjuda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Ajuda{" +
                "idAjuda=" + idAjuda +
                ", nome='" + nome + '\'' +
                '}';
    }
}
