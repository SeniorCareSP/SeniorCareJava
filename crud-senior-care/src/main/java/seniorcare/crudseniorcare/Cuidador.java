package seniorcare.crudseniorcare;

import java.nio.DoubleBuffer;
import java.util.List;

public class Cuidador extends Usuario{

    private String experiencia;
    private String faixaEtaria;
    private int qtdIdoso;
    private double precoHora;
    private List<Caracteristica> caracteristicas;
    private List<Ajuda> Ajudas;



    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<Ajuda> getAjudas() {
        return Ajudas;
    }

    public void setAjudas(List<Ajuda> ajudas) {
        Ajudas = ajudas;
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
                ", Ajudas=" + Ajudas +
                "} " + super.toString();
    }
}
