package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_idioma")
public class Idioma implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idIdioma;
    private String idioma;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario")
    private Usuario usuario;
}
