package seniorcare.crudseniorcare;

import java.util.List;

public class Responsavel extends Usuario{

    private double precoHora;

    private List<Idoso> idosos;

    public Responsavel(double precoHora, List<Idoso> idosos) {
        this.precoHora = precoHora;
        this.idosos = idosos;
    }

    public Responsavel() {

    }

    public double getPrecoHora() {
        return precoHora;
    }

    public void setPrecoHora(double precoHora) {
        this.precoHora = precoHora;
    }

    public List<Idoso> getIdosos() {
        return idosos;
    }

    public void setIdosos(List<Idoso> idosos) {
        this.idosos = idosos;
    }

    @Override
    public String toString() {
        return "Responsavel{" +
                "precoHora=" + precoHora +
                ", idosos=" + idosos +
                "} " + super.toString();
    }
}
