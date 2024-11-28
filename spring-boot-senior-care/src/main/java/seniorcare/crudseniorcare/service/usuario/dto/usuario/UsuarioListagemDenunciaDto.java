package seniorcare.crudseniorcare.service.usuario.dto.usuario;

import jakarta.persistence.*;
import lombok.Data;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.denuncia.Denuncia;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.usuario.TipoUsuario;

import java.time.LocalDate;
import java.util.List;
@Data
public class UsuarioListagemDenunciaDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nome;
    private String email;
    private String imagemUrl;;
    private String telefone;
    private String sexoBiologico;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoDeUsuario;
    private LocalDate dtNascimento;


}
