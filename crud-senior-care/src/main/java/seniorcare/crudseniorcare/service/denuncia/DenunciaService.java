package seniorcare.crudseniorcare.service.denuncia;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.denuncia.Denuncia;
import seniorcare.crudseniorcare.domain.denuncia.repository.DenunciaRepository;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaCriacaoDto;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaListagemDto;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaMapper;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;
    private final UsuarioService usuarioService;

    public Denuncia criarDenuncia(DenunciaCriacaoDto denunciaDto) {
        Usuario usuarioDenunciador = usuarioService.findById(denunciaDto.getUsuarioDenunciador())
                .orElseThrow(() -> new NaoEncontradoException("Usuário favoritando não encontrado"));

        Usuario usuarioDenunciado = usuarioService.findById(denunciaDto.getUsuarioDenunciado())
                .orElseThrow(() -> new NaoEncontradoException("Usuário favoritado não encontrado"));

        Denuncia denuncia = new Denuncia();
        denuncia.setDetalhes(denunciaDto.getDetalhes());
        denuncia.setUsuario(usuarioDenunciador);
        denuncia.setUsuarioDenunciado(usuarioDenunciado);
        denuncia.setInfo(denunciaDto.getInfo());
        denuncia.setStatus(false);

        denuncia = denunciaRepository.save(denuncia);

        if (usuarioDenunciador.getDenuncias() == null) {
            usuarioDenunciador.setDenuncias(new ArrayList<>());
        }
        usuarioDenunciador.getDenuncias().add(denuncia);

        usuarioService.update(usuarioDenunciado.getIdUsuario(), usuarioDenunciador);

        return denuncia;
    }

    public List<Denuncia> listarDenuncias() {
        List<Denuncia> denuncias = denunciaRepository.findAll();
        return denuncias;
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
