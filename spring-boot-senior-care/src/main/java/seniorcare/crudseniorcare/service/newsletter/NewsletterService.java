package seniorcare.crudseniorcare.service.newsletter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import seniorcare.crudseniorcare.domain.assinante.AssinanteEmail;
import seniorcare.crudseniorcare.domain.newsletter.Newsletter;
import seniorcare.crudseniorcare.domain.assinante.repository.AssinanteEmailRepository;
import seniorcare.crudseniorcare.domain.newsletter.repository.NewsletterRepository;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter @Setter
public class NewsletterService {


    private final EnviadorEmailService enviadorEmailService;
    private final NewsletterRepository newsletterRepository;
    private final AssinanteEmailRepository assinanteEmailRepository;

    public void publicarNewsletter(Integer id) {
        this.assinanteEmailRepository.findAssinantesByNewsletterId(id)
                .forEach(assinanteEmail -> assinanteEmail.receberNewsletter(this.enviadorEmailService, assinanteEmail.getNewsletter()));
    }

    public List<AssinanteEmail> listarAssinantesPeloId(Integer uuid) {
        return this.assinanteEmailRepository.findAssinantesByNewsletterId(uuid);
    }

    public void adicionarAssinante(Integer idNewsletter, AssinanteEmail assinante) {
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

    public Newsletter buscarPorId(Integer idNewsletter) {
        return buscarPorIndice(idNewsletter);
    }

    private Newsletter buscarPorIndice(Integer id) {
        return this.newsletterRepository.findById(id).orElseThrow();
    }


}
