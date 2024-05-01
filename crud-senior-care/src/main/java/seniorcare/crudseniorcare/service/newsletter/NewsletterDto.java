package seniorcare.crudseniorcare.service.newsletter;

import lombok.Data;
import seniorcare.crudseniorcare.domain.newsletter.Newsletter;

import java.util.List;
import java.util.UUID;

@Data
public class NewsletterDto {

    private UUID id;
    private String titulo;
    private String conteudo;
    private List<AssinanteEmailDto> assinantes;

    public NewsletterDto(Newsletter newsletter, List<AssinanteEmailDto> assinantes) {
        this.id = newsletter.getId();
        this.titulo = newsletter.getTitulo();
        this.conteudo = newsletter.getConteudo();
        this.assinantes = assinantes;
    }
}