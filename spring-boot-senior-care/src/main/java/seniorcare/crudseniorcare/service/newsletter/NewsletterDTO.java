package seniorcare.crudseniorcare.service.newsletter;

import lombok.Data;
import seniorcare.crudseniorcare.domain.newsletter.Newsletter;

import java.util.List;
import java.util.UUID;

@Data
public class NewsletterDTO {

    private Integer id;
    private String titulo;
    private String conteudo;
    private List<AssinanteEmailDTO> assinantes;

    public NewsletterDTO(Newsletter newsletter, List<AssinanteEmailDTO> assinantes) {
        this.id = newsletter.getId();
        this.titulo = newsletter.getTitulo();
        this.conteudo = newsletter.getConteudo();
        this.assinantes = assinantes;
    }
}