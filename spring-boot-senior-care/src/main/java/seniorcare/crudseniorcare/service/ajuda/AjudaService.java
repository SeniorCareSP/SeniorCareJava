package seniorcare.crudseniorcare.service.ajuda;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.ajuda.repository.AjudaRepository;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AjudaService {

    private final AjudaRepository repository;


    public List<Ajuda> list(){ return repository.findAll();}

    public Ajuda byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Ajuda")
        );
    }

    public Ajuda create(Ajuda novaAjuda){
        if (novaAjuda == null) return null;

        return repository.save(novaAjuda);
    }
    public Map<String, Long> contarAjudasPorNome() {
        List<Object[]> results = repository.countByNomeGroupByNome();
        Map<String, Long> contagemPorNome = new HashMap<>();
        for (Object[] result : results) {
            String nome = (String) result[0];
            Long count = (Long) result[1];
            contagemPorNome.put(nome, count);
        }
        return contagemPorNome;
    }


    public void delete(Integer id){
        Optional<Ajuda> ajuda = repository.findById(id);
        if (ajuda.isEmpty()){
            throw new NaoEncontradoException("Ajuda");
        }
        repository.delete(ajuda.get());
    }

    public Ajuda update(Integer id, Ajuda ajuda){
        Optional<Ajuda> ajudaOpt = repository.findById(id);

        if (ajudaOpt.isEmpty()) {
            throw new NaoEncontradoException("Responsavel");
        }

        ajuda.setIdAjuda(id);

        return repository.save(ajuda);
    }
}
