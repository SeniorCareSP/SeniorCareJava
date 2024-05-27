package seniorcare.crudseniorcare.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.ResponsavelMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/responsaveis")
@RestController
@RequiredArgsConstructor
public class ResponsavelController {

    private final ResponsavelService service;
    @GetMapping
    public ResponseEntity<List<UsuarioListagemResponsavelDto>> listar(){
        List<Responsavel> responsavels = service.list();

        if (responsavels.isEmpty()) return ResponseEntity.noContent().build();
        List<UsuarioListagemResponsavelDto> dto = ResponsavelMapper.toUsuarioListagemResponsavelDtoList(responsavels);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioListagemResponsavelDto> porId(@PathVariable Integer id){
        Responsavel responsavel = service.byId(id);
        UsuarioListagemResponsavelDto dto = ResponsavelMapper.toUsuarioListagemResponsavelDto(responsavel);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioListagemResponsavelDto> criar (
            @RequestBody UsuarioCriacaoResponsavelDto responsavelDto){

        Responsavel salvoEntity = ResponsavelMapper.toResponsavel(responsavelDto);

        Responsavel salvo = service.criar(salvoEntity);
        UsuarioListagemResponsavelDto dto = ResponsavelMapper.toUsuarioListagemResponsavelDto(salvo);

        URI uri = URI.create("/responsaveis/" + salvo.getIdUsuario());
        return ResponseEntity.created(uri).body(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioListagemResponsavelDto> update(@PathVariable Integer id, @RequestBody Responsavel responsavel){
        Responsavel uptResponsavel = service.update(id, responsavel);
        UsuarioListagemResponsavelDto dto = ResponsavelMapper.toUsuarioListagemResponsavelDto(uptResponsavel);
        return ResponseEntity.ok(dto);
    }

}
