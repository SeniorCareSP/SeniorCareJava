package seniorcare.crudseniorcare.dto.usuario;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import seniorcare.crudseniorcare.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
