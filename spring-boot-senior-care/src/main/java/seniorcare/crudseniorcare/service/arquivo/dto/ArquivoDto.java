package seniorcare.crudseniorcare.service.arquivo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoDto {
    private String nomeArquivoOriginal;
    private String nomeArquivoSalvo;
    private File arquivo;
}
