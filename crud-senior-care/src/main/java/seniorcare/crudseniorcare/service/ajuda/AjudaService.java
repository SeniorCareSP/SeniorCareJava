package seniorcare.crudseniorcare.service.ajuda;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.agenda.repository.AgendaRepository;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.ajuda.repository.AjudaRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class AjudaService {

    private final AjudaRepository repository;

    public List<Ajuda> list(){ return repository.findAll();}

    public Ajuda byId(UUID id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Ajuda")
        );
    }

    public Ajuda create(Ajuda novaAjuda){
        return repository.save(novaAjuda);
    }

    public void delete(UUID id){
        Optional<Ajuda> ajuda = repository.findById(id);
        if (ajuda.isEmpty()){
            throw new NaoEncontradoException("Ajuda");
        }
        repository.delete(ajuda.get());
    }

    public Ajuda update(UUID id, Ajuda ajuda){
        Optional<Ajuda> ajudaOpt = repository.findById(id);

        if (ajudaOpt.isEmpty()) {
            throw new NaoEncontradoException("Ajuda");
        }
        Ajuda uptAjuda = ajudaOpt.get();

        uptAjuda.setNome(ajuda.getNome());
        uptAjuda.setCuidador(ajuda.getCuidador());

        return uptAjuda;
    }
}
