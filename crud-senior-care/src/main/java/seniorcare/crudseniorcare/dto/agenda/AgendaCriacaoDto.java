package seniorcare.crudseniorcare.dto.agenda;

import jakarta.persistence.*;
import seniorcare.crudseniorcare.model.usuario.Usuario;

import java.util.UUID;

public class AgendaCriacaoDto {

    private String diaDaSemana;
    private boolean periodo_manha;
    private boolean periodo_tarde;
    private boolean periodo_noite;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario" )
    private Usuario usuario;


}
