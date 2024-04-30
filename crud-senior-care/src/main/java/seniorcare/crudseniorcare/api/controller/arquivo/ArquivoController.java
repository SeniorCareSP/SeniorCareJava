package seniorcare.crudseniorcare.api.controller.arquivo;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import seniorcare.crudseniorcare.domain.arquivo.Arquivo;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.domain.arquivo.repository.ArquivoRepository;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioListagemDto;
import seniorcare.crudseniorcare.utils.GravaArquivoCsv;
import seniorcare.crudseniorcare.utils.ListaObj;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

@RestController
@RequestMapping("/arquivos")
@Data
public class ArquivoController {
    final ArquivoRepository arquivoRepository;
    final UsuarioService usuarioService;


    //  private Path diretorioBase = Path.of(System.getProperty("user.dir") + "/arquivos"); // projeto
    private Path diretorioBase = Path.of(System.getProperty("java.io.tmpdir") + "/arquivos"); // temporario

     @GetMapping(value = "/gravar-arquivo", produces = "text/csv")
     public ResponseEntity<byte[]> gravarArquivo() throws Exception{
         try{
             List<UsuarioListagemDto> usuarios = usuarioService.listarTodos();
             return ResponseEntity.status(HttpStatus.OK).body(GravaArquivoCsv.gravarArquivo(usuarios, "usuarios"));
         }catch (Exception e){
             System.out.println(e);
             throw new Exception(e);
        }
     }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        Optional<Arquivo> arquivoOptional = arquivoRepository.findById(id);

        if (arquivoOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Arquivo arquivoBanco = arquivoOptional.get();

        File file = this.diretorioBase.resolve(arquivoBanco.getNomeArquivoSalvo()).toFile();
        try {
            InputStream fileInputStream = new FileInputStream(file);

            return ResponseEntity.status(200)
                    .header("Content-Disposition",
                            "attachment; filename=" + arquivoBanco.getNomeArquivoOriginal())
                    .body(fileInputStream.readAllBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Diretório não encontrado", null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível converter para byte[]", null);
        }
    }

    @GetMapping("/ler-arquivo")
    public void lerArquivo(ListaObj listaObj){
        GravaArquivoCsv.leArquivoCsv("usuarios");
    }



    private String formatarNomeArquivo(String nomeOriginal) {
        return String.format("%s_%s", UUID.randomUUID(), nomeOriginal);
    }

}
