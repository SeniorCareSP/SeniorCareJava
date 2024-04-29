package seniorcare.crudseniorcare.domain.idoso;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_idoso")
public class Idoso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idIdoso;
    private String nome;
    private boolean mobilidade;
    private boolean lucido;
    private String doencasCronicas;
    private Boolean cuidadosMin;
    @ManyToOne
    @JoinColumn(name = "responsavel_id", referencedColumnName = "idUsuario")
    private Responsavel responsavel;


}
