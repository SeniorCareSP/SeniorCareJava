package seniorcare.crudseniorcare.service.ajuda;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.ajuda.repository.AjudaRepository;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AjudaCuidadorService {
    private final AjudaRepository repository;

    public List<Ajuda> buscarPorResponsavel(Cuidador cuidador) {
        List<Ajuda> ajudas = repository.findByCuidador(cuidador);
        return ajudas;
    }
}
