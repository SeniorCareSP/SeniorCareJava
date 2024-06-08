package seniorcare.crudseniorcare.service.denuncia;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.denuncia.Denuncia;
import seniorcare.crudseniorcare.domain.denuncia.repository.DenunciaRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaCriacaoDto;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaListagemDto;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaMapper;

import java.util.List;
import java.util.Optional;

@Service

public class DenunciaService {

    private final DenunciaRepository denunciaRepository;

    @Autowired
    public DenunciaService(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    public DenunciaListagemDto criarDenuncia(DenunciaCriacaoDto denunciaDto) {
        Denuncia denuncia = DenunciaMapper.toEntity(denunciaDto);
        return DenunciaMapper.toListagemDto(denunciaRepository.save(denuncia));
    }

    public List<DenunciaListagemDto> listarDenuncias() {
        List<Denuncia> denuncias = denunciaRepository.findAll();
        return DenunciaMapper.toListagemDtoList(denuncias);
    }

    public DenunciaListagemDto buscarDenunciaPorId(Integer id) {
        Denuncia denuncia = denunciaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Denúncia não encontrada"));
        return DenunciaMapper.toListagemDto(denuncia);
    }

    public void deletarDenuncia(Integer id) {
        if (!denunciaRepository.existsById(id)) {
            throw new NaoEncontradoException("Denúncia não encontrada");
        }
        denunciaRepository.deleteById(id);
    }

    public DenunciaListagemDto atualizarDenuncia(Integer id, DenunciaCriacaoDto denunciaDto) {
        Denuncia denunciaExistente = denunciaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Denúncia não encontrada"));
        Denuncia denunciaAtualizada = DenunciaMapper.toEntity(denunciaDto);
        denunciaAtualizada.setId(id);
        return DenunciaMapper.toListagemDto(denunciaRepository.save(denunciaAtualizada));
    }
}
