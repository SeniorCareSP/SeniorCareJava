package seniorcare.crudseniorcare.domain.ajuda;

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
@Table(name = "tb_ajuda")
public class Ajuda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAjuda;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "cuidador_id", referencedColumnName = "idUsuario")
    private Cuidador cuidador;
}
