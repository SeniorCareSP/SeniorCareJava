package seniorcare.crudseniorcare.controller.files;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files")
public class FilesController {add

    private final String UPLOAD_DIR = "spring-boot-senior-care/src/main/resources/uploaded_files/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("filename") String filename) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }

        try {
            String filepath = Paths.get(UPLOAD_DIR, filename).toString();
            Path filePath = Paths.get(filepath);

            // Verifica se o arquivo já existe e, se existir, o remove
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }

            // Salva o novo arquivo
            InputStream inputStream = file.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(filepath);
            int readBytes;
            byte[] buffer = new byte[8192];
            while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, readBytes);
            }
            outputStream.close();
            inputStream.close();

            return ResponseEntity.ok("File uploaded successfully: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @GetMapping("/view/{filename:.+}")
    public ResponseEntity<byte[]> viewFile(@PathVariable String filename) {
        try {
            Path filepath = Paths.get(UPLOAD_DIR, filename);
            byte[] fileBytes = Files.readAllBytes(filepath);
            return ResponseEntity.ok().body(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
