package seniorcare.crudseniorcare.model;

import java.util.List;

public interface Comentavel {
    void adicionarComentario(Comentario comentario);
    List<Comentario> listarComentarios();
    void removerComentario(int id);
}