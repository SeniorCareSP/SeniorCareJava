package seniorcare.crudseniorcare.service.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.endereco.repository.EnderecoRepository;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoCriacaoDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;

import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public void salvarEndereco(Usuario usuario, Endereco enderecoDto) {
        Endereco endereco = enderecoDto;
        endereco.setUsuario(usuario);
        enderecoRepository.save(endereco);
    }


}
