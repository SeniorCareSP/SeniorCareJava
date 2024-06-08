package seniorcare.crudseniorcare.controller.denuncia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.service.denuncia.DenunciaService;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaCriacaoDto;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaListagemDto;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    private final DenunciaService denunciaService;

    @Autowired
    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @PostMapping
    public ResponseEntity<DenunciaListagemDto> criarDenuncia(@RequestBody DenunciaCriacaoDto denunciaDto) {
        DenunciaListagemDto novaDenuncia = denunciaService.criarDenuncia(denunciaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDenuncia);
    }

    @GetMapping
    public ResponseEntity<List<DenunciaListagemDto>> listarDenuncias() {
        List<DenunciaListagemDto> denuncias = denunciaService.listarDenuncias();
        return ResponseEntity.ok(denuncias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DenunciaListagemDto> buscarDenunciaPorId(@PathVariable Integer id) {
        DenunciaListagemDto denuncia = denunciaService.buscarDenunciaPorId(id);
        return ResponseEntity.ok(denuncia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDenuncia(@PathVariable Integer id) {
        denunciaService.deletarDenuncia(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DenunciaListagemDto> atualizarDenuncia(@PathVariable Integer id,
                                                                 @RequestBody DenunciaCriacaoDto denunciaDto) {
        DenunciaListagemDto denunciaAtualizada = denunciaService.atualizarDenuncia(id, denunciaDto);
        return ResponseEntity.ok(denunciaAtualizada);
    }
}
