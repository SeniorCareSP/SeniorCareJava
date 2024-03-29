package seniorcare.crudseniorcare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCaracteristica;
    private String nome;


    public UUID getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(UUID idCaracteristicas) {
        this.idCaracteristica = idCaracteristicas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "id_caracteristica=" + idCaracteristica +
                ", nome='" + nome + '\'' +
                '}';
    }
}
