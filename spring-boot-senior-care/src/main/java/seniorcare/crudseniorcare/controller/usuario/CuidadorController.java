package seniorcare.crudseniorcare.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.exception.EnderecoInvalidoException;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaCriacaoDto;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaListagemDto;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaMapper;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;
import seniorcare.crudseniorcare.service.geolocalizacao.CoordenadaService;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.CuidadorAtualizacaoDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;
import seniorcare.crudseniorcare.utils.Coordenadas;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/cuidadores")
@RestController
@RequiredArgsConstructor
public class CuidadorController {

    private final CuidadorService service;
    @GetMapping
    public ResponseEntity<List<UsuarioListagemCuidadorDto>> listar(){
        List<Cuidador> cuidadors = service.list();

        if (cuidadors.isEmpty()) return ResponseEntity.noContent().build();
        List<UsuarioListagemCuidadorDto> dto = CuidadorMapper.toUsuarioListagemCuidadorDtoList(cuidadors);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioListagemCuidadorDto> porId(@PathVariable Integer id){
        Cuidador cuidador = service.byId(id);
        UsuarioListagemCuidadorDto dto = CuidadorMapper.toUsuarioListagemCuidadorDto(cuidador);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> criar (@RequestBody UsuarioCriacaoCuidadorDto cuidadorDto) throws IOException {
        try{
        Cuidador salvoEntity = CuidadorMapper.toCuidador(cuidadorDto);

        Cuidador salvo = service.criar(salvoEntity);
        UsuarioListagemCuidadorDto dto = CuidadorMapper.toUsuarioListagemCuidadorDto(salvo);

        URI uri = URI.create("/cuidadores/" + salvo.getIdUsuario());
        return ResponseEntity.created(uri).body(dto);}
        catch (EnderecoInvalidoException e) {
            return ResponseEntity.badRequest().body("Endereço inválido");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioListagemCuidadorDto> update(@PathVariable Integer id, @RequestBody CuidadorAtualizacaoDto cuidadorAtualizacaoDto) throws IOException {

        Cuidador uptCuidador = service.update(id, cuidadorAtualizacaoDto);
        UsuarioListagemCuidadorDto dto = CuidadorMapper.toUsuarioListagemCuidadorDto(uptCuidador);

        return ResponseEntity.ok(dto);
    }

}
