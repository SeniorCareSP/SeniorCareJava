package seniorcare.crudseniorcare.service.newsletter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import seniorcare.crudseniorcare.domain.assinante.AssinanteEmail;
import seniorcare.crudseniorcare.domain.newsletter.Newsletter;
import seniorcare.crudseniorcare.domain.assinante.repository.AssinanteEmailRepository;
import seniorcare.crudseniorcare.domain.newsletter.repository.NewsletterRepository;


import java.util.List;
import java.util.UUID;

@Service
public class NewsletterService {

    @Autowired
    private EnviadorEmailService enviadorEmailService;

    @Autowired
    private NewsletterRepository newsletterRepository;

    @Autowired
    private AssinanteEmailRepository assinanteEmailRepository;

    public void publicarNewsletter(UUID id) {
        this.assinanteEmailRepository.findAssinantesByNewsletterId(id)
                .forEach(assinanteEmail -> assinanteEmail.receberNewsletter(this.enviadorEmailService, assinanteEmail.getNewsletter()));
    }

    public List<AssinanteEmail> listarAssinantesPeloId(UUID uuid) {
        return this.assinanteEmailRepository.findAssinantesByNewsletterId(uuid);
    }

    public void adicionarAssinante(UUID idNewsletter, AssinanteEmail assinante) {
        Newsletter newsletter = this.buscarPorIndice(idNewsletter);

        if (newsletter != null) {

            assinante.setNewsletter(newsletter);

            assinanteEmailRepository.save(assinante);
        }
    }

    public void criar(Newsletter newsletter) {
        this.newsletterRepository.save(newsletter);
    }

    public List<Newsletter> listar() {
        return this.newsletterRepository.findAll();
    }

    public Newsletter buscarPorId(UUID idNewsletter) {
        return buscarPorIndice(idNewsletter);
    }

    private Newsletter buscarPorIndice(UUID id) {
        return this.newsletterRepository.findById(id).orElseThrow();
    }

    public EnviadorEmailService getEnviadorEmailService() {
        return enviadorEmailService;
    }

    public void setEnviadorEmailService(EnviadorEmailService enviadorEmailService) {
        this.enviadorEmailService = enviadorEmailService;
    }

    public NewsletterRepository getNewsletterRepository() {
        return newsletterRepository;
    }

    public void setNewsletterRepository(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    public AssinanteEmailRepository getAssinanteEmailRepository() {
        return assinanteEmailRepository;
    }

    public void setAssinanteEmailRepository(AssinanteEmailRepository assinanteEmailRepository) {
        this.assinanteEmailRepository = assinanteEmailRepository;
    }
}
