package seniorcare.crudseniorcare.service.favorito;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.endereco.repository.EnderecoRepository;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.domain.favorito.repository.FavoritoRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;

import java.util.List;
import java.util.Optional;

    @Service
    @RequiredArgsConstructor
public class FavoritoService {

        private final FavoritoRepository repository;

        public List<Favorito> list(){ return repository.findAll();}

        public Favorito byId(Integer id){
            return repository.findById(id).orElseThrow(
                    () -> new NaoEncontradoException("Favorito")
            );
        }

        public Favorito create(Favorito novoFavorito){
            return repository.save(novoFavorito);
        }

        public void delete(Integer id){
            Optional<Favorito> favorito = repository.findById(id);
            if (favorito.isEmpty()){
                throw new NaoEncontradoException("Favorito");
            }
            repository.delete(favorito.get());
        }

        public Favorito update(Integer id, Favorito favorito){
            Optional<Favorito> favoritoOptional = repository.findById(id);

            if (favoritoOptional.isEmpty()) {
                throw new NaoEncontradoException("Favorito");
            }

            favorito.setId(id);

            return repository.save(favorito);
        }

    }


