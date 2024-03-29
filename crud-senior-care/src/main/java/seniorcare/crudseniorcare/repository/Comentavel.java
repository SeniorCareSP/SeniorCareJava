package seniorcare.crudseniorcare.repository;

import seniorcare.crudseniorcare.model.Comentario;
import seniorcare.crudseniorcare.model.Cuidador;

import java.util.List;

public interface Comentavel {
    void adicionarComentario(Comentario comentario);
    List<Comentario> listarComentarios();
    void removerComentario(int id);

}