package seniorcare.crudseniorcare.service.usuario.autenticacao;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.Optional;

import static com.mysql.cj.conf.PropertyKey.logger;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final CuidadorRepository cuidadorRepository;
    private final ResponsavelRepository responsavelRepository;

    private final UsuarioRepository usuarioRepository;
    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoService.class);

    //Método da interface implementada
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Tentando autenticar usuário com email: {}", username);
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(username);
<<<<<<< HEAD
        logger.info("\nUsuario Repository email: {}", usuarioOpt.get().getEmail());
=======
>>>>>>> c68b5c006773f888217380294209525caa4ea64b

        if (usuarioOpt.isEmpty()) {

            throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));
        }
        return new UsuarioDetalhesDto(usuarioOpt.get());

    }

}
