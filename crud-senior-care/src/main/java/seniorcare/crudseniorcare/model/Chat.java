package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.model.usuario.Cuidador;
import seniorcare.crudseniorcare.model.usuario.Responsavel;

import java.util.UUID;
@Getter @Setter

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idChat;

    @ManyToOne
    @JoinColumn(name = "fk_responsavel", referencedColumnName = "idUsuario" )
    private Responsavel responsavel;

    @ManyToOne
    @JoinColumn(name = "fk_cuidador", referencedColumnName = "idUsuario" )
    private Cuidador cuidador;


    @ManyToOne
    @JoinColumn(name = "fk_mensagem", referencedColumnName = "id_mensagem" )
    private Mensagem mensagem;
}
