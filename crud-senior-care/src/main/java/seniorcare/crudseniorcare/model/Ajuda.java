package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
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

}
