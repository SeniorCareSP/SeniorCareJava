package seniorcare.crudseniorcare;


import seniorcare.crudseniorcare.model.Newsletter;
import seniorcare.crudseniorcare.service.newsletter.EnviadorEmailService;


public interface Assinante{
    void receberNewsletter(EnviadorEmailService enviadorEmailService, Newsletter newsletter);
}
