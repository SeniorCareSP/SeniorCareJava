package seniorcare.crudseniorcare.controller.idoso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.service.idoso.IdosoService;
import seniorcare.crudseniorcare.service.idoso.dto.IdosoCriacaoDto;
import seniorcare.crudseniorcare.service.idoso.dto.IdosoListagemDto;
import seniorcare.crudseniorcare.service.idoso.dto.IdosoMapper;

import java.util.List;

@RestController
@RequestMapping("/idosos")
public class IdosoController {
    private final IdosoService idosoService;

    @Autowired
    public IdosoController(IdosoService idosoService) {
        this.idosoService = idosoService;
    }

    @GetMapping
    public ResponseEntity<List<IdosoListagemDto>> listarIdosos() {
        List<Idoso> idosos = idosoService.list();
        return ResponseEntity.ok(IdosoMapper.toListagemDtoList(idosos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdosoListagemDto> buscarPorId(@PathVariable Integer id) {
        Idoso idoso = idosoService.byId(id);
        return ResponseEntity.ok(IdosoMapper.toIdosoDto(idoso));
    }

    @PostMapping
    public ResponseEntity<IdosoListagemDto> criarIdoso(@RequestBody IdosoCriacaoDto idoso) {
        Idoso novoIdoso = idosoService.create(IdosoMapper.toIdoso(idoso), idoso.getResponsavel());
        return ResponseEntity.status(HttpStatus.CREATED).body(IdosoMapper.toIdosoDto(novoIdoso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdosoListagemDto> atualizarIdoso(@PathVariable Integer id, @RequestBody IdosoCriacaoDto idoso) {
        Idoso idosoAtualizado = idosoService.update(id, IdosoMapper.toIdoso(idoso));
        return ResponseEntity.ok(IdosoMapper.toIdosoDto(idosoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarIdoso(@PathVariable Integer id) {
        idosoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
