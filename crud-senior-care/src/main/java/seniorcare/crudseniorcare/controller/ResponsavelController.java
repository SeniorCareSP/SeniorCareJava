package seniorcare.crudseniorcare.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.dto.AgendaRecordDTO;
import seniorcare.crudseniorcare.dto.IdosoRecordDTO;
import seniorcare.crudseniorcare.dto.ResponsavelRecordDTO;
import seniorcare.crudseniorcare.model.Agenda;
import seniorcare.crudseniorcare.model.Idoso;
import seniorcare.crudseniorcare.model.Responsavel;
import seniorcare.crudseniorcare.model.Usuario;
import seniorcare.crudseniorcare.repository.ResponsavelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private ResponsavelRepository responsavelRepository;


    @GetMapping("/responsaveis")
    public ResponseEntity<List<Responsavel>> getResponsaveis() {
        List<Responsavel> responsaveis = responsavelRepository.findAll();

        if (responsaveis.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(responsaveis);
    }



    @PostMapping
    ResponseEntity<Responsavel>cadastrar(@RequestBody @Valid ResponsavelRecordDTO responsavelDTO){
        Responsavel responsavel = new Responsavel();
        BeanUtils.copyProperties(responsavelDTO, responsavel);
        return ResponseEntity.status(201).body(responsavelRepository.save(responsavel));
    }

    //@GetMapping("/{idUsuario}/idoso/{idIdoso}")
    //public ResponseEntity<List<Responsavel>> BuscarIdososPeloResponsavel(@PathVariable UUID idUsuario, @PathVariable int idIdoso) {

      // List<Responsavel> idososEncontrados = responsavelRepository.findByIdByIdoso(idUsuario);

        //if (idososEncontrados.isEmpty()){
          //  return ResponseEntity.status(204).build();
        //}

       //return ResponseEntity.status(200).body(idososEncontrados);
    //}

    @PutMapping("/{idResponsavel}")
    public ResponseEntity<Responsavel> atualizarResponsavel(@RequestBody @Valid ResponsavelRecordDTO novoResponsavel, @PathVariable UUID idResponsavel) {
        Responsavel responsavel = new Responsavel();
        BeanUtils.copyProperties(novoResponsavel, responsavel);
        Optional<Responsavel> responsavelEncontrado = responsavelRepository.findById(idResponsavel);

        if (responsavelEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        responsavel.setIdResponsavel(null);
        return ResponseEntity.status(200).body(responsavelRepository.save(responsavel));
    }

}
