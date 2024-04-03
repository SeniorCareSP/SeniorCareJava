package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_ajuda")
public class Ajuda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAjuda;
    private String nome;

    @ManyToMany
    @JoinTable(name = "ajuda_cuidadores",
            joinColumns = @JoinColumn(name = "idAjuda"),
            inverseJoinColumns = @JoinColumn(name = "idCuidador"))
    private List<Cuidador> cuidadores;

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

    public List<Cuidador> getCuidadores() {
        return cuidadores;
    }

    public void setCuidadores(List<Cuidador> cuidadores) {
        this.cuidadores = cuidadores;
    }

    @Override
    public String toString() {
        return "Ajuda{" +
                "idAjuda=" + idAjuda +
                ", nome='" + nome + '\'' +
                '}';
    }
}
