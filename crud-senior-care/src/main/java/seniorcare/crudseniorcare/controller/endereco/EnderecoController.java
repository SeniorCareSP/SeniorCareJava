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
    public ResponseEntity<EnderecoListagemDto> porId(@PathVariable UUID id){
        Agenda agenda = service.porId(id);
        AgendaListagemDto dto = AgendaMapper.toListagemDto(agenda);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AgendaListagemDto> criar (
            @RequestBody AgendaCriacaoDto agendaDto){

        Agenda salvoEntity = AgendaMapper.toEntity(agendaDto);

        Agenda salvo = service.create(salvoEntity);
        AgendaListagemDto dto = AgendaMapper.toListagemDto(salvo);
        URI uri = URI.create("/agendas" + salvo.getIdAgenda());

        return ResponseEntity.created(uri).body(dto);
    }
    }
}
