package seniorcare.crudseniorcare.service.agenda.dto;

import jakarta.persistence.*;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

public class AgendaCriacaoDto {

    private String diaDaSemana;
    private boolean periodo_manha;
    private boolean periodo_tarde;
    private boolean periodo_noite;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario" )
    private Usuario usuario;


}
