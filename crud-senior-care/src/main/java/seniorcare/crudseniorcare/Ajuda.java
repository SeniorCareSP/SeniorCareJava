package seniorcare.crudseniorcare;

public class Ajuda {
    private int id_ajuda;
    private String nome;

    public int getId_ajuda() {
        return id_ajuda;
    }

    public void setId_ajuda(int id_ajuda) {
        this.id_ajuda = id_ajuda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Ajuda{" +
                "id_ajuda=" + id_ajuda +
                ", nome='" + nome + '\'' +
                '}';
    }
}
