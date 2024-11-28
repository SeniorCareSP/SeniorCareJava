package seniorcare.crudseniorcare.controller.files;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.ResponsavelRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/files")
public class FilesController {

    private final Cloudinary cloudinary;
    private final UsuarioRepository usuarioRepository;
    private final ResponsavelRepository responsavelRepository;
    private final CuidadorRepository cuidadorRepository;

    public FilesController(UsuarioRepository usuarioRepository, ResponsavelRepository responsavelRepository, CuidadorRepository cuidadorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.responsavelRepository = responsavelRepository;
        this.cuidadorRepository = cuidadorRepository;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dzmebshlz",
                "api_key", "748152826583596",
                "api_secret", "qNK8lZInYzr6iid0D9ccz9bDLrg"
        ));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Integer idUsuario) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Por favor, selecione um arquivo para upload.");
        }
        try {
            File tempFile = convertMultipartFileToFile(file);

            Map uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());

            tempFile.delete();
            String url = uploadResult.get("url").toString();

            if (Objects.nonNull(idUsuario)) {
                Optional<Usuario> usuario = usuarioRepository.findByIdUsuario(idUsuario);
                if (usuario.isPresent()) {
                    switch (usuario.get().getTipoDeUsuario().toString()) {
                        case "CUIDADOR":
                            Optional<Cuidador> byId = cuidadorRepository.findById(usuario.get().getIdUsuario());
                            byId.get().setImagemUrl(url);
                            cuidadorRepository.save(byId.get());
                            break;
                        case "RESPONSAVEL":
                            Optional<Responsavel> byId1 = responsavelRepository.findById(usuario.get().getIdUsuario());
                            byId1.get().setImagemUrl(url);
                            responsavelRepository.save(byId1.get());
                            break;
                        default:
                            return ResponseEntity.badRequest().body("Tipo de usuário não reconhecido.");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
                }
            }

            return ResponseEntity.ok(url);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Falha ao fazer upload do arquivo.");
        }
    }


    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile("temp", multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }
}
