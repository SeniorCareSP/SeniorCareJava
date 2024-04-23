package seniorcare.crudseniorcare.dto.comentario;
import lombok.Getter;
import lombok.Setter;
//import seniorcare.crudseniorcare.dto.usuario.UsuarioDto;
import java.util.UUID;

@Getter
@Setter
public class ComentarioListagemDto {

    private UUID idComentario;
    private String conteudo;
    private double avaliacao;
    //private UsuarioDto usuario;
}
