package seniorcare.crudseniorcare.controller.files;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/files")
public class FilesController {

    private final String UPLOAD_DIR = "src/main/resources/uploaded_files/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Verifica se o diretório de upload existe, caso não exista, cria
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Salva o arquivo no diretório de upload
            File destFile = new File(uploadDir.getAbsolutePath() + "/" + file.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(destFile)) {
                fos.write(file.getBytes());
            }

            // Retorna o caminho completo do arquivo salvo
            String fileUrl = destFile.getAbsolutePath();
            return ResponseEntity.ok("Arquivo salvo em: " + fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao salvar o arquivo: " + e.getMessage());
        }
    }
}
