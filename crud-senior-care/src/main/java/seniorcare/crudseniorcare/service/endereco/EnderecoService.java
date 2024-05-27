package seniorcare.crudseniorcare.service.endereco;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.endereco.repository.EnderecoRepository;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoCriacaoDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;

    public List<Endereco> list(){ return repository.findAll();}

    public Endereco byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Endereco")
        );
    }

    public Endereco create(Endereco novoEndereco){
        return repository.save(novoEndereco);
    }

    public void delete(Integer id){
        Optional<Endereco> endereco = repository.findById(id);
        if (endereco.isEmpty()){
            throw new NaoEncontradoException("Endereco");
        }
        repository.delete(endereco.get());
    }

    public Endereco update(Integer id, Endereco endereco){
        Optional<Endereco> enderecoOpt = repository.findById(id);

        if (enderecoOpt.isEmpty()) {
            throw new NaoEncontradoException("Endereco");
        }
        Endereco uptEndereco = enderecoOpt.get();

        uptEndereco.setNumero(endereco.getNumero());
        uptEndereco.setUsuario(endereco.getUsuario());
        uptEndereco.setCep(endereco.getCep());
        uptEndereco.setCidade(endereco.getCidade());
        uptEndereco.setBairro(endereco.getBairro());
        uptEndereco.setComplemento(endereco.getComplemento());
        uptEndereco.setLogradouro(endereco.getLogradouro());

        return uptEndereco;
    }

}
