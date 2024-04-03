package seniorcare.crudseniorcare.dto;

import java.util.UUID;

public record EnderecoRecordDTO(UUID idEndereco, String rua, String cep, String logradouro, String complemento, String numero, String cidade, String bairro) {
}
