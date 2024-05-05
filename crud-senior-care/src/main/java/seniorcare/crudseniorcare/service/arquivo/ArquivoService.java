package seniorcare.crudseniorcare.service.arquivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.arquivo.Arquivo;
import seniorcare.crudseniorcare.domain.arquivo.repository.ArquivoRepository;
import seniorcare.crudseniorcare.service.arquivo.dto.ArquivoDto;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ArquivoService {
    @Autowired
    private ArquivoRepository arquivoRepository;
    private final Path diretorioBase = Path.of(Paths.get("").toAbsolutePath().toString());

    public ArquivoDto buscarArquivo(Integer id){
        Optional<Arquivo> arq = this.arquivoRepository.findById(id);

        if (arq.isEmpty()) {
            return null;
        }

        Arquivo arquivoBanco = arq.get();

        File file = this.diretorioBase.resolve(arquivoBanco.getNomeArquivoSalvo()).toFile();

        return new ArquivoDto(arquivoBanco.getNomeArquivoOriginal(), arquivoBanco.getNomeArquivoSalvo(), file);
    }

    public void salvarArquivo(File file) {
        Arquivo arquivo = new Arquivo(file.getName(), file.getName(), LocalDate.now());
        this.arquivoRepository.save(arquivo);
    }
}
