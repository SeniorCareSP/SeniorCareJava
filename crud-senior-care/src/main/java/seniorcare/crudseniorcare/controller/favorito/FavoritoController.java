package seniorcare.crudseniorcare.controller.favorito;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.service.favorito.FavoritoService;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoCriacaoDto;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoListagemDto;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoMapper;

import java.net.URI;
import java.util.List;
@Data
@RestController
@RequestMapping("/favoritos")
public class FavoritoController {
        private final FavoritoService service;

        @GetMapping
        public ResponseEntity<List<FavoritoListagemDto>> listar(){
            List<Favorito> favoritos = service.list();

            if (favoritos.isEmpty()) return ResponseEntity.noContent().build();
            List<FavoritoListagemDto> dto = FavoritoMapper.toListagemDtoList(favoritos);
            return ResponseEntity.ok(dto);
        }

        @GetMapping("/{id}")
        public ResponseEntity<FavoritoListagemDto> porId(@PathVariable Integer id){
            Favorito favorito = service.byId(id);
            FavoritoListagemDto dto = FavoritoMapper.toFavoritoDto(favorito);
            return ResponseEntity.ok(dto);
        }

        @PostMapping
        public ResponseEntity<FavoritoListagemDto> criar (
                @RequestBody FavoritoCriacaoDto favoritoCriacaoDto){

            Favorito salvoEntity = FavoritoMapper.toFavorito(favoritoCriacaoDto);

            Favorito salvo = service.create(salvoEntity);
            FavoritoListagemDto dto = FavoritoMapper.toFavoritoDto(salvo);
            URI uri = URI.create("/favoritos/" + salvo.getId());

            return ResponseEntity.created(uri).body(dto);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id){
            service.delete(id);
            return ResponseEntity.ok().build();
        }

        @PutMapping("/{id}")
        public ResponseEntity<FavoritoListagemDto> update(@PathVariable Integer id, @RequestBody Favorito favorito){
            Favorito uptFavorito = service.update(id, favorito);
            FavoritoListagemDto dto = FavoritoMapper.toFavoritoDto(uptFavorito);
            return ResponseEntity.ok(dto);
        }
}
