package seniorcare.crudseniorcare;


import seniorcare.crudseniorcare.domain.newsletter.Newsletter;
import seniorcare.crudseniorcare.service.newsletter.EnviadorEmailService;


public interface Assinante{
    void receberNewsletter(EnviadorEmailService enviadorEmailService, Newsletter newsletter);
}
