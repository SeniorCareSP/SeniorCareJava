package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_caracteristicas")
public class Caracteristica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCaracteristica;
    private String nome;
    @ManyToMany
    @JoinTable(name = "caracteristica_cuidadores",
            joinColumns = @JoinColumn(name = "idCaracteristica"),
            inverseJoinColumns = @JoinColumn(name = "idCuidador"))
    private List<Cuidador> cuidadores;


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

    public List<Cuidador> getCuidadores() {
        return cuidadores;
    }

    public void setCuidadores(List<Cuidador> cuidadores) {
        this.cuidadores = cuidadores;
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "id_caracteristica=" + idCaracteristica +
                ", nome='" + nome + '\'' +
                '}';
    }
}

