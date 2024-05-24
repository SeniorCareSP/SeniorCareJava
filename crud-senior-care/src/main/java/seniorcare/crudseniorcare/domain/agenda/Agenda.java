package seniorcare.crudseniorcare.domain.agenda;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.utils.ListaObj;

import java.io.Serializable;
import java.util.UUID;
@Getter @Setter
@Entity
@Table(name="tb_agenda")
public class Agenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAgenda;
    private boolean[][] disponibilidade = new boolean[7][3];
    @ManyToOne
    private Usuario usuario;

}   