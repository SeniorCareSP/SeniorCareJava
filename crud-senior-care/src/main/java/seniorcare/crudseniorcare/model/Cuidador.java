package seniorcare.crudseniorcare.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("CUIDADOR")
public class Cuidador extends Usuario implements Serializable {

    private UUID idCuidador;
    private String experiencia;
    private String faixaEtaria;
    private int qtdIdoso;
    private double precoHora;
    @ManyToMany
    @JoinTable(name = "cuidador_caracteristica", joinColumns = @JoinColumn (name = "cuidador_id"), inverseJoinColumns = @JoinColumn(name = "caracteristica_id"))
    private List<Caracteristica> caracteristicas;
    @ManyToMany
    @JoinTable(name = "cuidador_ajuda",
            joinColumns = @JoinColumn(name = "cuidador_id"),
            inverseJoinColumns = @JoinColumn(name = "ajuda_id"))
            private List<Ajuda> ajudas;




    public UUID getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(UUID idCuidador) {
        this.idCuidador = idCuidador;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<Ajuda> getAjudas() {
        return ajudas;
    }

    public void setAjudas(List<Ajuda> ajudas) {
        this.ajudas = ajudas;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public int getQtdIdoso() {
        return qtdIdoso;
    }

    public void setQtdIdoso(int qtdIdoso) {
        this.qtdIdoso = qtdIdoso;
    }

    public double getPrecoHora() {
        return precoHora;
    }

    public void setPrecoHora(double precoHora) {
        this.precoHora = precoHora;
    }


    @Override
    public String toString() {
        return "Cuidador{" +
                "experiencia='" + experiencia + '\'' +
                ", faixaEtaria='" + faixaEtaria + '\'' +
                ", qtdIdoso=" + qtdIdoso +
                ", precoHora=" + precoHora +
                ", caracteristicas=" + caracteristicas +
                ", Ajudas=" + ajudas +
                "} " + super.toString();
    }
}
