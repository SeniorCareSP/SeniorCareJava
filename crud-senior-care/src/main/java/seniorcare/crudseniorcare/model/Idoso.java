package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_idoso")
public class Idoso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idIdoso;
    private String nome;
    private boolean mobilidade;
    private boolean lucido;
    private String doencasCronicas;
    private Boolean cuidadosMin;
    @ManyToOne
    @JoinColumn(name = "responsavel_id", referencedColumnName = "idResponsavel")
    private Responsavel responsavel;


}
