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


        @PostMapping("/{idResponsavel}/{idCuidador}")
        public ResponseEntity<FavoritoListagemDto> criar(@PathVariable Integer idResponsavel,  @PathVariable Integer idCuidador) {

                Favorito favorito = service.create(idResponsavel, idCuidador);

                return ResponseEntity.ok(FavoritoMapper.toFavoritoDto(favorito));
        }

        @GetMapping("/exists/{idResponsavel}/{idCuidador}")
        public ResponseEntity<Boolean> exists(@PathVariable Integer idResponsavel, @PathVariable Integer idCuidador) {
                boolean exists = service.isFavoritoExists(idResponsavel, idCuidador);
                return ResponseEntity.ok(exists);
        }
        @DeleteMapping("/{idResponsavel}/{idCuidador}")
        public ResponseEntity<Void> desfavoritar(@PathVariable Integer idResponsavel, @PathVariable Integer idCuidador) {
                service.desfavoritar(idResponsavel, idCuidador);
                return ResponseEntity.ok().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id) {
                service.delete( id);
                return ResponseEntity.ok().build();
        }
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
