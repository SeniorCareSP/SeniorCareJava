package seniorcare.crudseniorcare;

import java.util.List;

public interface Comentavel {
    void adicionarComentario(Comentario comentario);
    List<Comentario> listarComentarios();
    void removerComentario(int id);
}