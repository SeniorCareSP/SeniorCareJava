package seniorcare.crudseniorcare.domain.favorito;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "tb_favorito")
public class Favorito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    private List<Usuario> usuarios;
}
