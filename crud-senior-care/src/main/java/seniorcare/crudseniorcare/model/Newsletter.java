package seniorcare.crudseniorcare.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "tb_newsletter")
public class Newsletter {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String titulo;
  private String conteudo;
}
