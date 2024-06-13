package seniorcare.crudseniorcare.service.idoso;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.idioma.repository.IdiomaRepository;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.domain.idoso.repository.IdosoRepository;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.idoso.dto.IdosoMapper;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdosoService {

    private final IdosoRepository repository;
    private final ResponsavelService responsavelService;

    public List<Idoso> list(){ return repository.findAll();}

    public Idoso byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Idoso")
        );
    }

    public Idoso create(Idoso novaIdoso, Integer idResponsavel){

        if (novaIdoso == null) return null;


        Responsavel usuarioResponsavel = responsavelService.findById(idResponsavel)
                .orElseThrow(() -> new NaoEncontradoException("Responsável favoritando não encontrado"));

        Idoso idoso = new Idoso();
        idoso.setResponsavel(usuarioResponsavel);

        idoso = repository.save(idoso);

        if (usuarioResponsavel.getIdosos() == null){
            usuarioResponsavel.setIdosos(new ArrayList<>());
        }
        usuarioResponsavel.getIdosos().add(idoso);

        responsavelService.update(usuarioResponsavel.getIdUsuario(), usuarioResponsavel);

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
