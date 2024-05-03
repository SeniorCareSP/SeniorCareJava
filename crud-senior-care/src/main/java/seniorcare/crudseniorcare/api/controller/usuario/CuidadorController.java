package seniorcare.crudseniorcare.api.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;

@RequestMapping("/cuidador")
@RestController
public class CuidadorController {

    @PostMapping
    public ResponseEntity<UsuarioListagemCuidadorDto> criar(){
        return null;
    }
}
