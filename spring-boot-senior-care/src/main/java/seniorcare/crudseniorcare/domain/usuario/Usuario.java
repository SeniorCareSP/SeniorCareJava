package seniorcare.crudseniorcare.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.agenda.Agenda;

import seniorcare.crudseniorcare.domain.denuncia.Denuncia;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.idioma.Idioma;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_usuario", indexes = {@Index(name = "idx_id_responsavel", columnList = "id_usuario")})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String imagemUrl;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private String sexoBiologico;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoDeUsuario;
    private LocalDate dtNascimento;
    private String apresentacao;
    private LocalDate dtCadastro;
    private Boolean status;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Agenda agenda;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Idioma> idiomas;


    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Denuncia> denuncias;

}
