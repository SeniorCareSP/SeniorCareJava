package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "tb_comentarios")
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idComentario;
    private String conteudo;
    private double avaliacao;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario")
    private Usuario usuario;
}
