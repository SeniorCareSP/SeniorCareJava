package seniorcare.crudseniorcare.domain.denuncia;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_denuncia")
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private List<String> info;
    private String detalhes;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "usuario_denunciado_id")
    private Usuario usuarioDenunciado;

}
