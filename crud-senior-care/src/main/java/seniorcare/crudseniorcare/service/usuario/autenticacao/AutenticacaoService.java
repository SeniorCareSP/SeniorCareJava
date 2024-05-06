package seniorcare.crudseniorcare.service.usuario.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    //Método da interface implementada
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<Cuidador> cuidador = cuidadorRepository.findByEmail(email);
        if (cuidador.isPresent()) {
            return new UsuarioDetalhesDto(cuidador.get());
        }

        Optional<Responsavel> responsavel = responsavelRepository.findByEmail(email);
        if (responsavel.isPresent()) {
            return new UsuarioDetalhesDto(responsavel.get());
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }

}
