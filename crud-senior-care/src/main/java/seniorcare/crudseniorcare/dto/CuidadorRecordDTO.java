package seniorcare.crudseniorcare.dto;

import seniorcare.crudseniorcare.model.Ajuda;
import seniorcare.crudseniorcare.model.Caracteristica;

import java.util.List;
import java.util.UUID;

public record CuidadorRecordDTO(UUID idCuidador, String experiencia, String faixaEtaria, int qtdIdoso, double precoHora) {
}
