package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.Assinante;
import seniorcare.crudseniorcare.service.EnviadorEmailService;

import java.util.UUID;
@Getter
@Setter
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
}
