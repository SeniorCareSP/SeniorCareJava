package seniorcare.crudseniorcare.controller.endereco;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.endereco.repository.EnderecoRepository;
import seniorcare.crudseniorcare.service.agenda.AgendaService;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaCriacaoDto;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaListagemDto;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaMapper;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoCriacaoDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoListagemDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Controller
@Data
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService service;
    //solid com responsabilidade unica (clean code). Se tiver um outro endpoint que chama o mesmo metodo, so muda o retorno dele.

    //na controller so tem caminho feliz, ao contrario disso vai pra service. 204 é caminho feliz

    @GetMapping
    public ResponseEntity<List<EnderecoListagemDto>> listar(){
        List<Endereco> enderecos = service.list();

        if (enderecos.isEmpty()) return ResponseEntity.noContent().build();
        List<EnderecoListagemDto> dto = EnderecoMapper.toEnderecosListagemDto(enderecos);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoListagemDto> porId(@PathVariable Integer id){
        Endereco endereco = service.byId(id);
        EnderecoListagemDto dto = EnderecoMapper.toEnderecoListagemDto(endereco);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EnderecoListagemDto> criar (
            @RequestBody EnderecoCriacaoDto enderecoDto){

        Endereco salvoEntity = EnderecoMapper.toEndereco(enderecoDto);

        Endereco salvo = service.create(salvoEntity);
        EnderecoListagemDto dto = EnderecoMapper.toEnderecoListagemDto(salvo);
        URI uri = URI.create("/enderecos/" + salvo.getIdEndereco());

        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoListagemDto> update(@PathVariable Integer id, @RequestBody Endereco endereco){
        Endereco uptEndereco = service.update(id, endereco);
        EnderecoListagemDto dto = EnderecoMapper.toEnderecoListagemDto(uptEndereco);
        return ResponseEntity.ok(dto);
    }
}
