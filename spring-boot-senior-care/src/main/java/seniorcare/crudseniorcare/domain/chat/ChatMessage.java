package seniorcare.crudseniorcare.domain.chat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String chatId;
    private Integer senderId;
    private Integer recipientId;
    private String content;
    private Date timestamp;
}
