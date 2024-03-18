package seniorcare.crudseniorcare;

public class Caracteristica {
    private int id_caracteristica;
    private String nome;


    public int getId_caracteristica() {
        return id_caracteristica;
    }

    public void setId_caracteristica(int id_caracteristica) {
        this.id_caracteristica = id_caracteristica;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "id_caracteristica=" + id_caracteristica +
                ", nome='" + nome + '\'' +
                '}';
    }
}
