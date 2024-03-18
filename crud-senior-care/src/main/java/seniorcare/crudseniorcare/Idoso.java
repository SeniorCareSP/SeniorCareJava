package seniorcare.crudseniorcare;

public class Idoso {
    private int id_idoso;
    private String nome;
    private boolean mobilidade;
    private boolean lucido;
    private String doencasCronicas;

    private Boolean cuidadosMin;

    public int getId_idoso() {
        return id_idoso;
    }

    public void setId_idoso(int id_idoso) {
        this.id_idoso = id_idoso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isMobilidade() {
        return mobilidade;
    }

    public void setMobilidade(boolean mobilidade) {
        this.mobilidade = mobilidade;
    }

    public boolean isLucido() {
        return lucido;
    }

    public void setLucido(boolean lucido) {
        this.lucido = lucido;
    }

    public String getDoencasCronicas() {
        return doencasCronicas;
    }

    public void setDoencasCronicas(String doencasCronicas) {
        this.doencasCronicas = doencasCronicas;
    }

    public Boolean getCuidadosMin() {
        return cuidadosMin;
    }

    public void setCuidadosMin(Boolean cuidadosMin) {
        this.cuidadosMin = cuidadosMin;
    }

    @Override
    public String toString() {
        return "Idoso{" +
                "nome='" + nome + '\'' +
                ", mobilidade=" + mobilidade +
                ", lucido=" + lucido +
                ", doencasCronicas='" + doencasCronicas + '\'' +
                ", cuidadosMin=" + cuidadosMin +
                '}';
    }
}
