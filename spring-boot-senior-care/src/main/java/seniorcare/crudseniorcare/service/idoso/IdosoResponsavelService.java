package seniorcare.crudseniorcare.service.idoso;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.domain.idoso.repository.IdosoRepository;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdosoResponsavelService {
    private final IdosoRepository repository;
    public List<Idoso> buscarPorResponsavel(Responsavel responsavelId) {
        List<Idoso> byResponsavelId = repository.findByResponsavel(responsavelId);
        return byResponsavelId;
    }
}
