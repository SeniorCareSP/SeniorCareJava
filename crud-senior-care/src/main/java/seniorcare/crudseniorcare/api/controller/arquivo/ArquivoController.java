package seniorcare.crudseniorcare.api.controller.arquivo;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.repository.arquivo.ArquivoRepository;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

@RestController
@RequestMapping("/arquivos")
@Data
public class ArquivoController {
    final ArquivoRepository arquivoRepository;
    final UsuarioRepository usuarioRepository;

    //  private Path diretorioBase = Path.of(System.getProperty("user.dir") + "/arquivos"); // projeto
    private Path diretorioBase = Path.of(System.getProperty("java.io.tmpdir") + "/arquivos"); // temporario

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(){
        Usuario usuario= new Cuidador();
        usuarioRepository.save(usuario);

        List<Usuario> listaUsuarios = usuarioRepository.findAll();
                String nomeArquivo = "usuarios";

                gravaArquivoCsv(listaUsuarios, nomeArquivo);
                leExibeArquivoCsv("usuarios.csv");

        Path arquivoPath = this.diretorioBase.resolve(nomeArquivo + ".csv");
        File arquivo = arquivoPath.toFile();

        if (!arquivo.exists()) {
            return ResponseEntity.status(404).build();
        }


        try {
            InputStream fileInputStream = new FileInputStream(arquivo);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + arquivo.getName())
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

    //import e export do arquivo csv

    public static void gravaArquivoCsv (List<Usuario> lista, String nomeArq){
        FileWriter arq = null; // arq representa arquivo a ser gravado
        Formatter saida = null; // saida eh o objeto usado para gravar no arquivo (dados entrando no arquivo e saindo do prog)
        Boolean deuRuim = false;

        //acrescenta a extensao .csv ao nome do arquivo
        nomeArq+=".csv";

        // bloco try-catch para abrir o arquivo
        try
        {
            arq = new FileWriter(nomeArq);
            //para gravar td bem o arquivo nao existir
            // se o arquivo ja existe ele vai sobreescrever
            // se o arquivo nao existir, ele cria
            saida = new Formatter(arq);
        }
        catch (IOException erro){
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
            System.exit(1);
        }

        //BLoco try-catch oara gravar o arquivo
        try{
            for (Usuario user : lista){
                saida.format("%s;%s;%s;%.2f;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        user.getIdUsuario().toString(),
                        user.getNome(),
                        user.getEmail(),
                        user.getTelefone(),
                        user.getCpf(),
                        user.getSexoBiologico(),
                        user.getDtNascimento(),
                        user.getApresentacao(),
                        user.getDtCadastro(),
                        user.getAgendas(),
                        user.getIdiomas(),
                        user.getEnderecos());
            }
        } catch (FormatterClosedException erro){
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        }
        finally {
            saida.close();
            try{
                arq.close();
            }catch (IOException  erro){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }

            if (deuRuim){
                System.exit(1);
            }
        }


    }

    public static void leExibeArquivoCsv(String nomeArq){
        FileReader arq = null; // arquivo a ser lido
        Scanner entrada = null; //objeto usado para ler do arquivo
        Boolean deuRuim = false;

        //acrescenta a extensao .csv ao nome do arquivo
        nomeArq +=".csv";

        //Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n" );
        } catch (FileNotFoundException erro){
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
            System.exit(1);
        }

        //bloco try-catch para ler o arquivo
        try{

            System.out.printf("%-36s %-20s %-30s %-15s %-15s %-12s %-15s %-20s %-20s %-20s %-20s %-20s %-20s\n",
                    "idUsuario", "nome", "email", "telefone", "cpf", "sexoBiologico",
                    "dtNascimento", "apresentacao", "dtCadastro", "agendas", "idiomas",
                    "enderecos", "comentarios");
            while (entrada.hasNext()){
                UUID idUsuario = UUID.fromString(entrada.next());
                String nome = entrada.next();
                String email = entrada.next();
                String telefone = entrada.next();
                String cpf = entrada.next();
                String sexoBiologico = entrada.next();
                String dtNascimento = entrada.next();
                String apresentacao = entrada.next();
                String dtCadastro = entrada.next();
                String agendas = entrada.next();
                String idiomas = entrada.next();
                String enderecos = entrada.next();
                String comentarios = entrada.next();

                System.out.printf("%-36s %-20s %-30s %-15s %-15s %-12s %-15s %-20s %-20s %-20s %-20s %-20s %-20s\n",
                        idUsuario.toString(), nome, email, telefone, cpf, sexoBiologico,
                        dtNascimento, apresentacao, dtCadastro, agendas, idiomas,
                        enderecos, comentarios);
            }
        } catch (NoSuchElementException erro){
            System.out.println("Arquivo com problemas");
            erro.printStackTrace();
            deuRuim = true;
        }catch (IllegalStateException erro){
            System.out.println("Erro na leitura do arquivo");
            erro.printStackTrace();
            deuRuim = true;
        } finally {
            entrada.close();
            try{
                arq.close();
            }catch (IOException  erro){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }

            if (deuRuim){
                System.exit(1);
            }
        }

    }
}
