package seniorcare.crudseniorcare.service.usuario.autenticacao;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final CuidadorRepository cuidadorRepository;
    private final ResponsavelRepository responsavelRepository;

    private final UsuarioRepository usuarioRepository;

    //Método da interface implementada
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) throw new UsernameNotFoundException("Usuário não encontrado");
        return new UsuarioDetalhesDto(usuario.get());

    }

}
