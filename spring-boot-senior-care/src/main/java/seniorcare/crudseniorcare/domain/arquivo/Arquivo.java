package seniorcare.crudseniorcare.domain.arquivo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name="tb_arquivo")
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
