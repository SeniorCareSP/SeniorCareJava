package seniorcare.crudseniorcare.service.agenda.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.UUID;

public class AgendaListagemDto {

        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID idAgenda;
        private String diaDaSemana;
        private boolean periodo_manha;
        private boolean periodo_tarde;
        private boolean periodo_noite;
        @ManyToOne
        @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario" )
        private Usuario usuario;
}
