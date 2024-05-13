package seniorcare.crudseniorcare.domain.caracteristica;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "tb_caracteristicas")
public class Caracteristica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCaracteristica;
    private String nome;
    @OneToMany
    @JoinTable(name = "caracteristica_cuidadores",
            joinColumns = @JoinColumn(name = "idCaracteristica"),
            inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<Cuidador> cuidadores;

}

