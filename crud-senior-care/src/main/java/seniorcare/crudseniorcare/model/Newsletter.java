package seniorcare.crudseniorcare.model;


import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "tb_newsletter")
public class Newsletter {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String titulo;
  private String conteudo;

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }

  public UUID getId() {
    return id;
  }
}
