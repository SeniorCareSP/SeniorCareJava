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
import seniorcare.crudseniorcare.service.arquivo.ArquivoService;
import seniorcare.crudseniorcare.service.arquivo.dto.ArquivoDto;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioListagemDto;
import seniorcare.crudseniorcare.utils.GravaArquivoCsv;
import seniorcare.crudseniorcare.utils.ListaObj;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RestController
@RequestMapping("/arquivos")
@Data
public class ArquivoController {

    final ArquivoRepository arquivoRepository;
    final UsuarioService usuarioService;
    final ArquivoService arquivoService;

    //  private Path diretorioBase = Path.of(System.getProperty("user.dir") + "/arquivos"); // projeto
    private Path diretorioBase = Path.of(System.getProperty("java.io.tmpdir") + "/arquivos"); // temporario

     @GetMapping(value = "/gravar-arquivo", produces = "text/csv")
     public ResponseEntity<byte[]> gravarArquivo() throws Exception{
         try{
             List<UsuarioListagemDto> usuarios = usuarioService.listarTodos();
             File arquivo = GravaArquivoCsv.gravarArquivo(usuarios, "usuarios");

             this.arquivoService.salvarArquivo(arquivo);

             byte[] conteudo = Files.readAllBytes(new File(arquivo.getName()).toPath());
             return ResponseEntity.status(HttpStatus.OK).body(conteudo);
         }catch (Exception e){
             System.out.println(e);
             throw new Exception(e);
        }
     }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        ArquivoDto arquivo = this.arquivoService.buscarArquivo(id);

        if (arquivo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            InputStream fileInputStream = new FileInputStream(arquivo.getArquivo());

            return ResponseEntity.status(200)
                    .header("Content-Disposition",
                            "attachment; filename=" + arquivo.getNomeArquivoOriginal())
                    .body(fileInputStream.readAllBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Diretório não encontrado", null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível converter para byte[]", null);
        }
    }


    private String formatarNomeArquivo(String nomeOriginal) {
        return String.format("%s_%s", UUID.randomUUID(), nomeOriginal);
    }

    @GetMapping("/ler-arquivo")
    public void lerArquivo(ListaObj listaObj){
        GravaArquivoCsv.leArquivoCsv("usuarios");
    }


}
