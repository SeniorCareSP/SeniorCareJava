package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seniorcare.crudseniorcare.model.Ajuda;
import seniorcare.crudseniorcare.model.Caracteristica;
import seniorcare.crudseniorcare.model.Cuidador;
import seniorcare.crudseniorcare.repository.CaracteristicaRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {
    @Autowired
    CaracteristicaRepository caracteristicaRepository;

    @DeleteMapping("/{idUsuario}/ajuda/{idCaracteristica}")
    public ResponseEntity<Ajuda> removerCaracteristica(@PathVariable UUID idCaracteristica){
        Optional<Caracteristica> caracteristica = caracteristicaRepository.findById(idCaracteristica);

        if (caracteristica.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        caracteristicaRepository.delete(caracteristica.get());
        return ResponseEntity.status(200).build();
    }

}
