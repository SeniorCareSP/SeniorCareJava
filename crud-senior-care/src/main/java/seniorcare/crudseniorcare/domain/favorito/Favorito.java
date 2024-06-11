package seniorcare.crudseniorcare.domain.favorito;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Responsavel responsavel;

    @ManyToOne
    @JoinColumn(name = "usuario_favoritado_id")
    private Cuidador cuidador;



}
