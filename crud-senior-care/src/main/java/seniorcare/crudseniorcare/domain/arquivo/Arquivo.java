package seniorcare.crudseniorcare.domain.arquivo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Getter @Setter
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nomeArquivoOriginal;
    private String nomeArquivoSalvo;
    private LocalDate dataUpload;

    public Arquivo(String nomeArquivoOriginal, String nomeArquivoSalvo, LocalDate dataUpload) {
        this.nomeArquivoOriginal = nomeArquivoOriginal;
        this.nomeArquivoSalvo = nomeArquivoSalvo;
        this.dataUpload = dataUpload;
    }

    public Arquivo() {}
}
