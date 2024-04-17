package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;
import seniorcare.crudseniorcare.model.usuario.Cuidador;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "tb_caracteristicas")
public class Caracteristica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCaracteristica;
    private String nome;
    @ManyToMany
    @JoinTable(name = "caracteristica_cuidadores",
            joinColumns = @JoinColumn(name = "idCaracteristica"),
            inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<Cuidador> cuidadores;

}

