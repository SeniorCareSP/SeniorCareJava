package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.model.usuario.Usuario;

import java.io.Serializable;
import java.util.UUID;
@Getter @Setter
@Entity
@Table(name="tb_agenda")
public class Agenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAgenda;
    private String diaDaSemana;
    private boolean periodo_manha;
    private boolean periodo_tarde;
    private boolean periodo_noite;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario" )
    private Usuario usuario;

}
