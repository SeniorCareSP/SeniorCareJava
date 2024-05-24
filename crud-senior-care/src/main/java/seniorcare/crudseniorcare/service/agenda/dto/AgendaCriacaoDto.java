package seniorcare.crudseniorcare.service.agenda.dto;

import jakarta.persistence.*;
import lombok.Data;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
@Data
public class AgendaCriacaoDto {

    //private String diaDaSemana;
    private boolean[][] disponibilidade;
    private Usuario usuario;


}
