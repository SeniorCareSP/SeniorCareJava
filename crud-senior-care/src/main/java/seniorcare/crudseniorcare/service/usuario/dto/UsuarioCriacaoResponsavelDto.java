package seniorcare.crudseniorcare.service.usuario.dto;

import jakarta.persistence.OneToMany;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.idoso.Idoso;

import java.time.LocalDate;
import java.util.List;

public class UsuarioCriacaoResponsavelDto {

    private String nome;

    private String email;

    private String senha;
    private String telefone;
    private String cpf;
    private String sexoBiologico;

    private LocalDate dtNascimento;
    private String apresentacao;
    private LocalDate dtCadastro;
    @OneToMany(mappedBy = "usuario")
    private List<Agenda> agendas;
    @OneToMany(mappedBy = "usuario")
    private List<Idioma> idiomas;
    @OneToMany(mappedBy = "usuario")
    private List<Endereco> enderecos;
    private double precoHora;

    @OneToMany(mappedBy = "responsavel")
    private List<Idoso> idosos;


}
