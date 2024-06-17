package seniorcare.crudseniorcare.controller.visita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seniorcare.crudseniorcare.service.visita.VisitaService;

@RestController
@RequestMapping("/visitas")
public class VisitaController {

    private final VisitaService visitaService;

    @Autowired
    public VisitaController(VisitaService visitaService) {
        this.visitaService = visitaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrarVisita() {
        visitaService.registrarVisita();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarVisitasHoje() {
        long totalVisitasHoje = visitaService.contarVisitasHoje();
        return ResponseEntity.ok(totalVisitasHoje);
    }
}
