package seniorcare.crudseniorcare;


import jakarta.persistence.Embeddable;
import seniorcare.crudseniorcare.model.Newsletter;
import seniorcare.crudseniorcare.service.EnviadorEmailService;


public interface Assinante{
    void receberNewsletter(EnviadorEmailService enviadorEmailService, Newsletter newsletter);
}
