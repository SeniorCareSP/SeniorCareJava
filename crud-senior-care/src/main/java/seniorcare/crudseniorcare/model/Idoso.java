package seniorcare.crudseniorcare.model;

import java.util.UUID;

public class Idoso {
    private UUID idoso;
    private String nome;
    private boolean mobilidade;
    private boolean lucido;
    private String doencasCronicas;

    private Boolean cuidadosMin;

    public UUID getIdoso() {
        return idoso;
    }

    public void setIdoso(UUID idoso) {
        this.idoso = idoso;
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
