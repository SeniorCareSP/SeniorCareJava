package seniorcare.crudseniorcare.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.model.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioMapper;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void criar(UsuarioCriacaoCuidadorDto usuarioCriacaoCuidadorDto){
        final Usuario novoUsuario = UsuarioMapper.INSTANCE.toEntityCuidador(usuarioCriacaoCuidadorDto);
        this.usuarioRepository.save(novoUsuario);
    }

    public void criar(UsuarioCriacaoResponsavelDto usuarioCriacaoResponsavelDto){
        final Usuario novoUsuario = UsuarioMapper.INSTANCE.toEntityResponsavel(usuarioCriacaoResponsavelDto);
        this.usuarioRepository.save(novoUsuario);
    }
}
