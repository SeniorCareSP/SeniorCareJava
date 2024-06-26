package seniorcare.crudseniorcare.domain.idoso;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_idoso")
public class Idoso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIdoso;
    private String nome;
    private String descricao;
    private boolean mobilidade;
    private boolean lucido;
    private String doencasCronicas;
    private Boolean cuidadosMin;
    private LocalDate DtNascimento;
    private String genero;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", referencedColumnName = "idUsuario")
    private Responsavel responsavel;


}
