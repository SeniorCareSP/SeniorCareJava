package seniorcare.crudseniorcare.controller.favorito;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.service.favorito.FavoritoService;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoCriacaoDto;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoListagemDto;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favoritos")
public class FavoritoController {
        private final FavoritoService service;

        @GetMapping("/{idUsuario}")
        public ResponseEntity<List<FavoritoListagemDto>> listar(@PathVariable Integer idUsuario) {
                List<Favorito> favoritos = service.listByUsuario(idUsuario);
                if (favoritos.isEmpty()) {
                        return ResponseEntity.noContent().build();
                }
                List<FavoritoListagemDto> dto = FavoritoMapper.toListagemDtoList(favoritos);
                return ResponseEntity.ok(dto);
        }

//        @GetMapping("/{idUsuario}/{id}")
//        public ResponseEntity<FavoritoListagemDto> porId(@PathVariable Integer idUsuario, @PathVariable Integer id) {
//                Favorito favorito = service.byId(idUsuario, id);
//
//                if (favorito == null) {
//                        return ResponseEntity.notFound().build();
//                }
//                FavoritoListagemDto dto = FavoritoMapper.toFavoritoDto(favorito);
//                return ResponseEntity.ok(dto);
//        }

        @PostMapping
        public ResponseEntity<FavoritoListagemDto> criar(@RequestBody FavoritoCriacaoDto dto) {

                Favorito favorito = service.create(dto.getIdUsuarioFavoritando(), dto.getIdUsuarioFavoritado());

                return ResponseEntity.ok(FavoritoMapper.toFavoritoDto(favorito));
        }
//
//        @DeleteMapping("/{idUsuario}/{id}")
//        public ResponseEntity<Void> delete(@PathVariable Integer idUsuario, @PathVariable Integer id) {
//                service.delete(idUsuario, id);
//                return ResponseEntity.ok().build();
//        }
//
//        @PutMapping("/{idUsuario}/{id}")
//        public ResponseEntity<FavoritoListagemDto> update(@PathVariable Integer idUsuario, @PathVariable Integer id, @RequestBody Favorito favorito) {
//                Favorito uptFavorito = service.update(idUsuario, id, favorito);
//                if (uptFavorito == null) {
//                        return ResponseEntity.notFound().build();
//                }
//                FavoritoListagemDto dto = FavoritoMapper.toFavoritoDto(uptFavorito);
//                return ResponseEntity.ok(dto);
//        }
}
