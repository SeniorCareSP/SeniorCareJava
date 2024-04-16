package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Getter @Setter
@Entity
@Table(name = "tb_usuario", indexes = {@Index(name = "idx_id_responsavel", columnList = "id_responsavel")})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUsuario;
    @Size(min = 3)
    @NotBlank
    private String nome;
    @Email
    @NotBlank
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    @NotBlank
    private String senha;
    @Pattern(regexp = "^\\([1-9]{2}\\)\\s9?[0-9]{4}-?[0-9]{4}$")
    private String telefone;
    @CPF
    private String cpf;
    private String sexoBiologico;
    @Past
    @NotNull
    private LocalDate dtNascimento;
    private String apresentacao;
    @NotNull
    private LocalDate dtCadastro;
    @OneToMany(mappedBy = "usuario")
    private List<Agenda> agendas;
    @OneToMany(mappedBy = "usuario")
    private List<Idioma> idiomas;
    @OneToMany(mappedBy = "usuario")
    private List<Endereco> enderecos;
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

}
