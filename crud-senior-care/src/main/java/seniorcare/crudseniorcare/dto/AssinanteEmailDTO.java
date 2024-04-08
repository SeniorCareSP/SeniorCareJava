package seniorcare.crudseniorcare.dto;

import lombok.Data;
import seniorcare.crudseniorcare.model.AssinanteEmail;

import java.util.UUID;

@Data
public class AssinanteEmailDTO {
    private UUID uuid;
    private String nome;
    private String email;

    public AssinanteEmailDTO(AssinanteEmail assinanteEmail) {
        this.uuid = assinanteEmail.getId();
        this.nome = assinanteEmail.getNome();
        this.email = assinanteEmail.getEmail();
    }
}
