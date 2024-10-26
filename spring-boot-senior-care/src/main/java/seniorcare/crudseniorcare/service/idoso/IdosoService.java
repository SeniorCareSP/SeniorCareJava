package seniorcare.crudseniorcare.service.idoso;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.idioma.repository.IdiomaRepository;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.domain.idoso.repository.IdosoRepository;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.idoso.dto.IdosoMapper;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;

import java.util.*;

@Service
public class IdosoService {
    private final IdosoRepository repository;
    private final ResponsavelService responsavelService;

    public IdosoService(IdosoRepository repository, ResponsavelService responsavelService) {
        this.repository = repository;
        this.responsavelService = responsavelService;
    }


    public Map<String, Map<String, Long>> contarIdososPorFaixaEtariaEGenero() {
        List<Object[]> results = repository.countByFaixaEtariaAndGenero();
        Map<String, Map<String, Long>> contagemPorFaixaEtariaEGenero = new HashMap<>();
        for (Object[] result : results) {
            String faixaEtaria = (String) result[0];
            String genero = (String) result[1];
            Long count = (Long) result[2];
            contagemPorFaixaEtariaEGenero
                    .computeIfAbsent(faixaEtaria, k -> new HashMap<>())
                    .put(genero, count);
        }
        return contagemPorFaixaEtariaEGenero;
    }


    public List<Idoso> list(){ return repository.findAll();}

    public Idoso byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Idoso")
        );
    }

    public Idoso create(Idoso novaIdoso, Integer idResponsavel) {

        if (novaIdoso == null) {
            throw new IllegalArgumentException("Novo idoso não pode ser nulo");
        }

        // Busca o responsável associado
        Responsavel usuarioResponsavel = responsavelService.findById(idResponsavel)
                .orElseThrow(() -> new NaoEncontradoException("Responsável não encontrado"));

        Idoso idoso = new Idoso();
        idoso.setNome(novaIdoso.getNome());
        idoso.setDescricao(novaIdoso.getDescricao());
        idoso.setDescricao(novaIdoso.getDescricao());
        idoso.setMobilidade(novaIdoso.isMobilidade());
        idoso.setLucido(novaIdoso.isLucido());
        idoso.setDoencasCronicas(novaIdoso.getDoencasCronicas());
        idoso.setCuidadosMin(novaIdoso.getCuidadosMin());
        idoso.setDtNascimento(novaIdoso.getDtNascimento());
        idoso.setResponsavel(usuarioResponsavel);
        idoso.setGenero(novaIdoso.getGenero());

        // Salva o idoso no repositório
        idoso = repository.save(idoso);

        // Adiciona o idoso à lista de idosos do responsável
        if (usuarioResponsavel.getIdosos() == null) {
            usuarioResponsavel.setIdosos(new ArrayList<>());
        }
        usuarioResponsavel.getIdosos().add(idoso);

        // Atualiza o responsável no serviço
        responsavelService.updateToIdoso(usuarioResponsavel.getIdUsuario(), usuarioResponsavel);

        return idoso;
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
