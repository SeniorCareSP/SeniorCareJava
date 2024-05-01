package seniorcare.crudseniorcare.service.newsletter;

import lombok.Data;
import seniorcare.crudseniorcare.domain.assinante.AssinanteEmail;

import java.util.UUID;

@Data
public class AssinanteEmailDto {
    private UUID uuid;
    private String nome;
    private String email;

    public AssinanteEmailDto(AssinanteEmail assinanteEmail) {
        this.uuid = assinanteEmail.getId();
        this.nome = assinanteEmail.getNome();
        this.email = assinanteEmail.getEmail();
    }
}