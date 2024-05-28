package seniorcare.crudseniorcare.domain.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import seniorcare.crudseniorcare.domain.idoso.Idoso;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
@Table(name="tb_responsavel")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("RESPONSAVEL")
public class Responsavel extends Usuario {
    public Responsavel(String nome, String email, String senha, String telefone, String cpf, String sexoBiologico, TipoUsuario tipoDeUsuario, LocalDate dtNascimento, String apresentacao, LocalDate dtCadastro, List<Idoso> idosos) {
        super(nome, email, senha, telefone, cpf, sexoBiologico, tipoDeUsuario, dtNascimento, apresentacao, dtCadastro);
        this.idosos = idosos;
    }


    @OneToMany(mappedBy = "responsavel")
    private List<Idoso> idosos;


}
