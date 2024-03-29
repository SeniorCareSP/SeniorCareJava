package seniorcare.crudseniorcare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;
@Entity
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idIdioma;
    private String idioma;

    public UUID getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(UUID idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Idioma{" +
                "idIdioma=" + idIdioma +
                ", idioma='" + idioma + '\'' +
                '}';
    }
}
