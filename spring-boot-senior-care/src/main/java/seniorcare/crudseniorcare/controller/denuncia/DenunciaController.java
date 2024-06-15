package seniorcare.crudseniorcare.controller.denuncia;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.denuncia.Denuncia;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.denuncia.DenunciaService;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaCriacaoDto;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaListagemDto;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaMapper;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoCriacaoDto;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoListagemDto;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoMapper;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioMapper;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;

@RestController
    @RequestMapping("/denuncias")
@RequiredArgsConstructor
public class DenunciaController {

    private final DenunciaService denunciaService;
    private final UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<DenunciaListagemDto>> listarDenuncias() {
        List<Denuncia> denuncias = denunciaService.listarDenuncias();
        return ResponseEntity.ok(DenunciaMapper.toListagemDtoList(denuncias));
    }



    @GetMapping("/{id}")
    public ResponseEntity<DenunciaListagemDto> buscarDenunciaPorId(@PathVariable Integer id) {
        DenunciaListagemDto denuncia = denunciaService.buscarDenunciaPorId(id);
        return ResponseEntity.ok(denuncia);
    }


    @PostMapping
    public ResponseEntity<DenunciaListagemDto> criar(@RequestBody DenunciaCriacaoDto dto) {

        Denuncia denuncia = denunciaService.criarDenuncia(  dto);

        return ResponseEntity.ok(DenunciaMapper.toListagemDto(denuncia));
    }

    @PostMapping("/bloquear/{idDenunciado}")
    public ResponseEntity<UsuarioListagemDto> bloquearUsuario(@PathVariable Integer idDenunciado) {

        Usuario usuario = usuarioService.bloquearUsuario(idDenunciado);

        return ResponseEntity.ok(UsuarioMapper.toUsuarioListagemDto(usuario));
    }


    @PostMapping("/resolverDenuncia/{idDenuncia}")
    public ResponseEntity<DenunciaListagemDto> resolverDenuncia(@PathVariable Integer idDenuncia) {

        Denuncia denuncia = denunciaService.resolverDenuncia(idDenuncia);

        return ResponseEntity.ok(DenunciaMapper.toListagemDto(denuncia));
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
