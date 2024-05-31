package seniorcare.crudseniorcare.service.favorito.dto;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.List;

@Getter @Setter
public class FavoritoCriacaoDto {
    private List<Usuario> usuarios;
}
