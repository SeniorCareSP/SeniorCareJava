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
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String titulo;
  private String conteudo;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }
}