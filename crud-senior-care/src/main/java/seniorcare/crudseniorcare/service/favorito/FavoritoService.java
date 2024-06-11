package seniorcare.crudseniorcare.service.favorito;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.domain.favorito.repository.FavoritoRepository;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;
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
    private final CuidadorService cuidadorService;

    public List<Favorito> listByUsuario(Integer idUsuario) {
        return repository.findByResponsavelIdUsuario(idUsuario);
    }



    public Favorito byIdFavorito(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Favorito não encontrado"));
    }

    public Favorito create(Integer idResponsavel, Integer idCuidador) {
        if (Objects.equals(idResponsavel, idCuidador)){
            throw new RuntimeException("Um usuário não pode favoritar a si mesmo.");
        }

        if(isFavoritoExists(idResponsavel, idCuidador)){
            throw new ConflitoException("Favorito Já Existe");
        }
        Responsavel usuarioFavoritando = responsavelService.findById(idResponsavel)
                .orElseThrow(() -> new NaoEncontradoException("Responsável favoritando não encontrado"));

        Cuidador usuarioFavoritado = cuidadorService.findById(idCuidador)
                .orElseThrow(() -> new NaoEncontradoException("Usuário favoritado não encontrado"));

        Favorito favorito = new Favorito();
        favorito.setCuidador(usuarioFavoritado);
        favorito.setResponsavel(usuarioFavoritando);

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


        public void desfavoritar(Integer idResponsavel, Integer idCuidador) {
            // Verifica se a relação de favorito existe
            boolean favoritoExists = isFavoritoExists(idResponsavel, idCuidador);



            // Se a relação de favorito não existir, lança uma exceção ou retorna uma mensagem, conforme necessário
            if (!favoritoExists) {
                throw new NaoEncontradoException("Esta relação de favorito não existe.");
                // Ou você pode retornar uma mensagem de erro ou simplesmente encerrar a função, dependendo do comportamento desejado
            }

            Favorito favorito = repository.findByUsuarioIdAndUsuarioFavoritadoId(idResponsavel, idCuidador)
                    .orElseThrow(() -> new NaoEncontradoException("Relação de favorito não encontrada"));

            Responsavel responsavel = responsavelService.findById(idResponsavel)
                    .orElseThrow(() -> new NaoEncontradoException("Responsável não encontrado"));
            responsavel.getFavoritos().remove(favorito);

            usuarioService.update(idResponsavel, responsavel);

            repository.delete(favorito);
        }



    public boolean isFavoritoExists(Integer idResponsavel, Integer idCuidador) {
        return repository.existsByResponsavelIdAndCuidadorId(idResponsavel, idCuidador);
    }


    public void delete(Integer id) {
        Favorito favorito = byIdFavorito(id);

        repository.delete(favorito);
    }
//
//    public Favorito update(Integer idUsuario, Integer id, Favorito favorito) {
//        Favorito existingFavorito = byId(idUsuario, id);
//        existingFavorito.setUsuarioFavoritado(favorito.getUsuarioFavoritado());
//        existingFavorito.setUsuario(favorito.getUsuario());
//        return repository.save(existingFavorito);
//    }
}
