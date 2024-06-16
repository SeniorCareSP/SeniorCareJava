package seniorcare.crudseniorcare.controller.usuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;
import seniorcare.crudseniorcare.service.usuario.dto.ResponsavelMapper;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;

import java.net.URI;
import java.util.List;

@RequestMapping("/responsaveis")
@RestController
@RequiredArgsConstructor
@Api(tags = "ResponsavelController", description = "Controller da responsável")
public class    ResponsavelController {

    private final ResponsavelService service;

    @GetMapping
    @ApiOperation(value = "Listar todos os responsáveis", notes = "Retorna uma lista de todos os responsáveis")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de responsáveis obtida com sucesso"),
            @ApiResponse(code = 204, message = "Nenhum responsável encontrado")
    })
    public ResponseEntity<List<UsuarioListagemResponsavelDto>> listar() {
        List<Responsavel> responsavels = service.list();
        if (responsavels.isEmpty()) return ResponseEntity.noContent().build();
        List<UsuarioListagemResponsavelDto> dto = ResponsavelMapper.toUsuarioListagemResponsavelDtoList(responsavels);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter responsável por ID", notes = "Retorna um responsável pelo seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Responsável obtido com sucesso"),
            @ApiResponse(code = 404, message = "Responsável não encontrado")
    })
    public ResponseEntity<UsuarioListagemResponsavelDto> porId(@PathVariable Integer id) {
        Responsavel responsavel = service.byId(id);
        UsuarioListagemResponsavelDto dto = ResponsavelMapper.toUsuarioListagemResponsavelDto(responsavel);
        return ResponseEntity.ok(dto);
    }

    @PostMapping

    public ResponseEntity<UsuarioListagemResponsavelDto> criar(@RequestBody UsuarioCriacaoResponsavelDto responsavelDto) {
        Responsavel salvoEntity = ResponsavelMapper.toResponsavel(responsavelDto);

        Responsavel salvo = service.criar(salvoEntity);
        UsuarioListagemResponsavelDto dto = ResponsavelMapper.toUsuarioListagemResponsavelDto(salvo);

        URI uri = URI.create("/responsaveis/" + salvo.getIdUsuario());
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar responsável por ID", notes = "Deleta um responsável pelo seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Responsável deletado com sucesso"),
            @ApiResponse(code = 404, message = "Responsável não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar responsável por ID", notes = "Atualiza um responsável pelo seu ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Responsável atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Dados inválidos para atualização do responsável"),
            @ApiResponse(code = 404, message = "Responsável não encontrado")
    })
    public ResponseEntity<UsuarioListagemResponsavelDto> update(@PathVariable Integer id, @RequestBody Responsavel responsavel) {
        Responsavel uptResponsavel = service.update(id, responsavel);
        UsuarioListagemResponsavelDto dto = ResponsavelMapper.toUsuarioListagemResponsavelDto(uptResponsavel);
        return ResponseEntity.ok(dto);
    }
}
