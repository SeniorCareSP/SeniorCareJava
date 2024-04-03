package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_idoso")
public class Idoso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idIdoso;
    private String nome;
    private boolean mobilidade;
    private boolean lucido;
    private String doencasCronicas;
    private Boolean cuidadosMin;
    @ManyToOne
    @JoinColumn(name = "responsavel_id", referencedColumnName = "idResponsavel")
    private Responsavel responsavel;


    public UUID getIdIdoso() {
        return idIdoso;
    }

    public void setIdIdoso(UUID idIdoso) {
        this.idIdoso = idIdoso;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }


    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
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
