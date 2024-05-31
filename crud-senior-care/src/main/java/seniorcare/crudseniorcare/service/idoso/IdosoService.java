package seniorcare.crudseniorcare.service.idoso;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.idioma.repository.IdiomaRepository;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.domain.idoso.repository.IdosoRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.idoso.dto.IdosoMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdosoService {

    private final IdosoRepository repository;

    public List<Idoso> list(){ return repository.findAll();}

    public Idoso byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Idoso")
        );
    }

    public Idoso create(Idoso novaIdoso){

        if (novaIdoso == null) return null;

        return repository.save(novaIdoso);
    }

    public void delete(Integer id){
        Optional<Idoso> idoso = repository.findById(id);
        if (idoso.isEmpty()){
            throw new NaoEncontradoException("Idoso");
        }
        repository.delete(idoso.get());
    }

    public Idoso update(Integer id, Idoso idoso){
        Optional<Idoso> idosoOpt = repository.findById(id);

        if (idosoOpt.isEmpty()) {
            throw new NaoEncontradoException("Idioma");
        }

        idoso.setIdIdoso(id);

        return repository.save(idoso);
    }
}
