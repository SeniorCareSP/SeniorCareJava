package seniorcare.crudseniorcare.domain.chat;

import jakarta.persistence.*;
import lombok.*;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // Adiciona esta anotação
@Builder
@Entity
@Table(name = "tb_chat_room")

public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
