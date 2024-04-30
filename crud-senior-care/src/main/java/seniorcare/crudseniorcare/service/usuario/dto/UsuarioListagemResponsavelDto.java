package seniorcare.crudseniorcare.service.usuario.dto;

import jakarta.persistence.OneToMany;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.idoso.Idoso;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UsuarioListagemResponsavelDto extends UsuarioListagemDto{
    private double precoHora;
    @OneToMany(mappedBy = "responsavel")
    private List<Idoso> idosos;

}
