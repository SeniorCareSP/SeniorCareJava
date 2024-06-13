package seniorcare.crudseniorcare.service.caracteristica;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.ajuda.repository.AjudaRepository;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.domain.caracteristica.repository.CaracteristicaRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CaracteristicaService {

    private final CaracteristicaRepository repository;

    public List<Caracteristica> list(){ return repository.findAll();}

    public Caracteristica byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Caracteristica")
        );
    }

    public Caracteristica create(Caracteristica novaCaracteristica){
        return repository.save(novaCaracteristica);
    }

    public void delete(Integer id){
        Optional<Caracteristica> caracteristica = repository.findById(id);
        if (caracteristica.isEmpty()){
            throw new NaoEncontradoException("Caracteristica");
        }
        repository.delete(caracteristica.get());
    }

    public Caracteristica update(Integer id, Caracteristica caracteristica){
        Optional<Caracteristica> caraOpt = repository.findById(id);

        if (caraOpt.isEmpty()) {
            throw new NaoEncontradoException("Caracteristica");
        }
        Caracteristica uptCaracteristica = caraOpt.get();

        uptCaracteristica.setNome(caracteristica.getNome());
        uptCaracteristica.setCuidador(caracteristica.getCuidador());

        return uptCaracteristica;
    }
}
