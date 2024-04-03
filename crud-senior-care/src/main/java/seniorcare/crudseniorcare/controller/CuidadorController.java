package seniorcare.crudseniorcare.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.dto.ComentarioRecordDTO;
import seniorcare.crudseniorcare.dto.CuidadorRecordDTO;
import seniorcare.crudseniorcare.model.*;
import seniorcare.crudseniorcare.repository.CuidadorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cuidadores")
public class CuidadorController {
    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private CuidadorRepository cuidadorRepository;


    @GetMapping
    public ResponseEntity<List<Cuidador>> getCuidadores() {

        List<Cuidador> cuidadores = cuidadorRepository.findAll();
        if (cuidadores.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cuidadores);
    }


    @PostMapping
    ResponseEntity<Cuidador>cadastrar(@RequestBody @Valid CuidadorRecordDTO cuidadorRecordDTO){
        Cuidador cuidador = new Cuidador();
        BeanUtils.copyProperties(cuidadorRecordDTO, cuidador);
        return ResponseEntity.status(201).body(cuidadorRepository.save(cuidador));
    }

    @PutMapping("/{idCuidador}")
    public ResponseEntity<Cuidador> atualizarCuidador(@RequestBody @Valid ComentarioRecordDTO novoCuidador, @PathVariable UUID idCuidador) {
        Cuidador cuidador = new Cuidador();
        BeanUtils.copyProperties(novoCuidador, cuidador);
        Optional<Cuidador> cuidadorEncontrado = cuidadorRepository.findById(idCuidador);

        if (cuidadorEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        cuidador.setIdCuidador(null);
        return ResponseEntity.status(200).body(cuidadorRepository.save(cuidador));
    }


}
