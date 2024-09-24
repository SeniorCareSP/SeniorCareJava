package seniorcare.crudseniorcare.domain.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
        @Table(name = "tb_administrador")
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {
        private String cargo;
}
