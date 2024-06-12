package seniorcare.crudseniorcare.domain.newsletter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter @Getter
@Entity
@Table(name = "tb_newsletter")
public class Newsletter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String titulo;
  private String conteudo;
}
