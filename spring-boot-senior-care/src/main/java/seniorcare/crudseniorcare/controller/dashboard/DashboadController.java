package seniorcare.crudseniorcare.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seniorcare.crudseniorcare.service.denuncia.DenunciaService;

@RestController
@RequestMapping("/dasboard")
public class DashboadController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping("/quantidade-denuncia-aberta")
    public ResponseEntity<Integer> getCountByStatusFalse() {
        Integer count = denunciaService.getCountByStatusFalse();
        return ResponseEntity.ok(count);
    }

}
