package seniorcare.crudseniorcare.service.idioma;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.idioma.repository.IdiomaRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdiomaService {

    private final IdiomaRepository repository;

    public List<Idioma> list(){ return repository.findAll();}

    public Idioma byId(UUID id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Idioma")
        );
    }

    public Idioma create(Idioma novaIdioma){
        return repository.save(novaIdioma);
    }

    public void delete(UUID id){
        Optional<Idioma> idioma = repository.findById(id);
        if (idioma.isEmpty()){
            throw new NaoEncontradoException("Idioma");
        }
        repository.delete(idioma.get());
    }

    public Idioma update(UUID id, Idioma idioma){
        Optional<Idioma> idiomaOpt = repository.findById(id);

        if (idiomaOpt.isEmpty()) {
            throw new NaoEncontradoException("Idioma");
        }
        Idioma uptIdioma = idiomaOpt.get();

        uptIdioma.setIdioma(idioma.getIdioma());
        uptIdioma.setUsuario(idioma.getUsuario());

        return uptIdioma;
    }
}
