package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import seniorcare.crudseniorcare.model.usuario.Cuidador;
import seniorcare.crudseniorcare.model.usuario.Responsavel;

import java.util.UUID;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idChat;

    @ManyToOne
    @JoinColumn(name = "fk_responsavel", referencedColumnName = "idUsuario" )
    private Responsavel responsavel;

    @ManyToOne
    @JoinColumn(name = "fk_cuidador", referencedColumnName = "idUsuario" )
    private Cuidador cuidador;

    public UUID getIdUsuario() {
        return idChat;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idChat = idUsuario;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }
}
