package seniorcare.crudseniorcare.api.controller.endereco;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.endereco.repository.EnderecoRepository;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoListagemDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;

import java.util.List;

@Controller
@Data
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<Endereco>> listarEndereco(){
        List<Endereco> list = enderecoRepository.findAll();
        return ResponseEntity.status(200).body(list);
    }
}
