package seniorcare.crudseniorcare.service.newsletter;

import lombok.Data;
import seniorcare.crudseniorcare.domain.assinante.AssinanteEmail;

import java.util.UUID;

@Data
public class AssinanteEmailDTO {
    private Integer uuid;
    private String nome;
    private String email;

    public AssinanteEmailDTO(AssinanteEmail assinanteEmail) {
        this.uuid = assinanteEmail.getId();
        this.nome = assinanteEmail.getNome();
        this.email = assinanteEmail.getEmail();
    }
}