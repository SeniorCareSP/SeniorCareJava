package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

@Entity
public class Mensagem {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private UUID id_mensagem;
    private String mensagem;
    private LocalDateTime dt_envio;
    @ManyToOne
    @JoinColumn(name = "fk_chat", referencedColumnName = "idChat" )
    private Chat chat;

    public UUID getId_mensagem() {
        return id_mensagem;
    }

    public void setId_mensagem(UUID id_mensagem) {
        this.id_mensagem = id_mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDt_envio() {
        return dt_envio;
    }

    public void setDt_envio(LocalDateTime dt_envio) {
        this.dt_envio = dt_envio;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
