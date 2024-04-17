package seniorcare.crudseniorcare.model.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.model.Agenda;
import seniorcare.crudseniorcare.model.Comentario;
import seniorcare.crudseniorcare.model.Endereco;
import seniorcare.crudseniorcare.model.Idioma;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Entity
@Table(name = "tb_usuario", indexes = {@Index(name = "idx_id_responsavel", columnList = "id_usuario")})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUsuario;

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
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;
}
