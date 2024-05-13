package seniorcare.crudseniorcare.domain.assinante;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.Assinante;
import seniorcare.crudseniorcare.domain.newsletter.Newsletter;
import seniorcare.crudseniorcare.service.newsletter.EnviadorEmailService;

import java.util.UUID;
@Getter @Setter
@Entity
@Table(name = "tb_assinante_email")
public class AssinanteEmail implements Assinante {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String nome;
  private String email;

  @ManyToOne
  @JoinColumn(name = "newsletter_id")
  private Newsletter newsletter;

  private static final String EMAIL_EMPRESA = "seniorcarebr@hotmail.com";

  @Override
  public void receberNewsletter(EnviadorEmailService enviadorEmailService, Newsletter newsletter) {
    enviadorEmailService.sendEmail(EMAIL_EMPRESA, this.email, newsletter.getTitulo(), newsletter.getConteudo());


  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Newsletter getNewsletter() {
    return newsletter;
  }

  public void setNewsletter(Newsletter newsletter) {
    this.newsletter = newsletter;
  }
}
