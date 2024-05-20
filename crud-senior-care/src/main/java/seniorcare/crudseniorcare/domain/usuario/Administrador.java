package seniorcare.crudseniorcare.domain.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_administrador")
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {
        private String cargo;
}
