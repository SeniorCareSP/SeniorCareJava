package seniorcare.crudseniorcare.service.favorito;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.domain.favorito.repository.FavoritoRepository;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository repository;
    private final UsuarioService usuarioService;
    private final ResponsavelService responsavelService;

    public List<Favorito> listByUsuario(Integer idUsuario) {
        return repository.findByUsuarioIdUsuario(idUsuario);
    }

//    public Favorito byId(Integer idUsuario, Integer id) {
//        return repository.findByUsuarioIdUsuarioAndIdUsuario(idUsuario, id)
//                .orElseThrow(() -> new NaoEncontradoException("Favorito não encontrado"));
//    }

    public Favorito create(Integer idUsuarioFavoritando, Integer idUsuarioFavoritado) {
        if (Objects.equals(idUsuarioFavoritando, idUsuarioFavoritado)){
            throw new RuntimeException("Um usuário não pode favoritar a si mesmo.");
        }
        Usuario usuarioFavoritando = usuarioService.findById(idUsuarioFavoritando)
                .orElseThrow(() -> new NaoEncontradoException("Usuário favoritando não encontrado"));

        Usuario usuarioFavoritado = usuarioService.findById(idUsuarioFavoritado)
                .orElseThrow(() -> new NaoEncontradoException("Usuário favoritado não encontrado"));

        Favorito favorito = new Favorito();
        favorito.setUsuario(usuarioFavoritando);
        favorito.setUsuarioFavoritado(usuarioFavoritado);

        favorito = repository.save(favorito);

        if (usuarioFavoritando.getFavoritos() == null) {
            usuarioFavoritando.setFavoritos(new ArrayList<>());
        }
        usuarioFavoritando.getFavoritos().add(favorito);

        // Atualizar o responsável no banco de dados com a nova lista de favoritos
        usuarioService.update(usuarioFavoritando.getIdUsuario(), usuarioFavoritando);

        // Retornar o objeto Favorito criado
        return favorito;
    }




//    public void delete(Integer idUsuario, Integer id) {
//        Favorito favorito = byId(idUsuario, id);
//        repository.delete(favorito);
//    }
//
//    public Favorito update(Integer idUsuario, Integer id, Favorito favorito) {
//        Favorito existingFavorito = byId(idUsuario, id);
//        existingFavorito.setUsuarioFavoritado(favorito.getUsuarioFavoritado());
//        existingFavorito.setUsuario(favorito.getUsuario());
//        return repository.save(existingFavorito);
//    }
}
